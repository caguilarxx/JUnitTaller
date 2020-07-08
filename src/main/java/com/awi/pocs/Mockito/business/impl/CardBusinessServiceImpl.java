package com.awi.pocs.Mockito.business.impl;

import com.awi.pocs.Mockito.business.CardBusinessService;
import com.awi.pocs.Mockito.repository.CardRepository;
import com.awi.pocs.Mockito.service.CardApi;
import com.awi.pocs.Mockito.service.HazelcastApi;
import com.awi.pocs.model.Card;
import com.awi.pocs.model.Product;
import com.awi.pocs.model.Session;
import com.awi.pocs.model.api.DemoRequest;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.reactivex.schedulers.Schedulers.io;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Service
public class CardBusinessServiceImpl implements CardBusinessService {

  @Autowired
  private CardApi cardApi;

  @Autowired
  private HazelcastApi hazelcast;

  @Autowired
  private CardRepository repository;

  @Override
  public List<Card> getCards() {
    return asList(
        new Card(10, "4059102010101010", true, null),
        new Card(20, "4059102020202020", true, null));
  }

  @Override
  public List<Card> getCardsFromDatabase() {
    List<Card> cards = repository.findAll();
    cards.forEach(card -> card.setProducts(asList(new Product(1,"11"))));
    return cards;
  }

  @Override
  public Single<List<Card>> filterActiveCards(DemoRequest request) {

    Session session = hazelcast.getFromCache(request.getSessionId());

    //Demo Exc
    if (session.isBlacklisted()) {
      return Single.error(new RuntimeException("Person is Blacklisted"));
    }

    Single<List<Card>> cardsFromApi = cardApi.getCards(session.getDocumentNumber());

    return cardsFromApi.map(cardApis -> cardApis.stream().filter(Card::getActive))
        .map(cardApiStream -> cardApiStream.map(
            cardApi -> new Card(cardApi.getId(), cardApi.getNumber(), cardApi.getActive(), null)).collect(toList()));
  }

  @Override
  public Observable<Card> filterActiveCardsObs(DemoRequest request) {
    Session session = hazelcast.getFromCache(request.getSessionId());
    Single<List<Card>> cardsFromApi = cardApi.getCards(session.getDocumentNumber());

    return cardsFromApi.map(cardApis -> cardApis.stream().filter(Card::getActive))
        .map(cardApiStream -> cardApiStream.map(cardApi -> new Card(cardApi.getId(), cardApi.getNumber(), cardApi.getActive(), null))
            .collect(toList()))
        .toObservable()
        .flatMapIterable(x -> x)
        .subscribeOn(io());
  }
}

