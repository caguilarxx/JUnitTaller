package com.awi.pocs.Mockito.business.impl;

import static io.reactivex.schedulers.Schedulers.io;
import static java.util.stream.Collectors.toList;

import com.awi.pocs.Mockito.business.DemoBusinessService;
import com.awi.pocs.Mockito.service.CardsApi;
import com.awi.pocs.Mockito.service.HazelcastApi;
import com.awi.pocs.model.Card;
import com.awi.pocs.model.Session;
import com.awi.pocs.model.api.CardApi;
import com.awi.pocs.model.api.DemoRequest;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoBusinessServiceImpl implements DemoBusinessService {

  @Autowired
  private CardsApi cardsApi;

  @Autowired
  private HazelcastApi hazelcast;

  @Override
  public Single<List<Card>> filterActiveCards(DemoRequest request) {

    Session session = hazelcast.getFromCache(request.getSessionId());

    //Demo Exc
    if (session.isBlacklisted()) {
      return Single.error(new RuntimeException("Person is Blacklisted"));
    }

    Single<List<CardApi>> cardsFromApi = cardsApi.getCards(session.getDocumentNumber());

    return cardsFromApi.map(cardApis -> cardApis.stream().filter(CardApi::getActive))
        .map(cardApiStream -> cardApiStream.map(
            cardApi -> new Card(cardApi.getId(), cardApi.getNumber(), cardApi.getActive(), null)).collect(toList()));
  }

  @Override
  public Observable<Card> filterActiveCardsObs(DemoRequest request) {
    Session session = hazelcast.getFromCache(request.getSessionId());
    Single<List<CardApi>> cardsFromApi = cardsApi.getCards(session.getDocumentNumber());

    return cardsFromApi.map(cardApis -> cardApis.stream().filter(CardApi::getActive))
        .map(cardApiStream -> cardApiStream.map(
            cardApi -> new Card(cardApi.getId(), cardApi.getNumber(), cardApi.getActive(), null)).collect(toList()))
        .toObservable()
        .flatMapIterable(x -> x).subscribeOn(io());
  }
}

