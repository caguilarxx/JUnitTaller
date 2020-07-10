package com.awi.pocs.Mockito.business.impl;

import com.awi.pocs.Mockito.business.CardBusinessService;
import com.awi.pocs.Mockito.service.CardApi;
import com.awi.pocs.Mockito.service.HazelcastApi;
import com.awi.pocs.model.Card;
import com.awi.pocs.model.Session;
import com.awi.pocs.model.api.DemoRequest;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.reactivex.schedulers.Schedulers.io;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CardBusinessServiceImpl implements CardBusinessService {

  private final CardApi cardApi;
  private final HazelcastApi hazelcast;

  @Override
  public List<Card> getCards() {
    return asList(
        new Card(10, "4059102010101010", true),
        new Card(20, "4059102020202020", true));
  }

  @Override
  public Single<List<Card>> filterActiveCards(DemoRequest request) {

    Session session = hazelcast.getFromCache(request.getSessionId());

    /* Demo Exception */
    if (session.isBlacklisted()) {
      return Single.error(new Exception("Person is Blacklisted"));
    }

    return cardApi.getCards(session.getDocumentNumber())
        .map(cards -> cards.stream()
            .filter(Card::getActive)
            .collect(toList()));
  }

  @Override
  public Observable<Card> filterActiveCardsObs(DemoRequest request) {

    Session session = hazelcast.getFromCache(request.getSessionId());

    return buildCardApiData()
        .map(cards -> cards.stream()
            .filter(Card::getActive)
            .collect(toList()))
        .toObservable()
        .flatMapIterable(cards -> cards)
        .concatMap(i-> Observable.just(i).delay(4, TimeUnit.SECONDS))
        .subscribeOn(io());
  }

  private Single<List<Card>> buildCardApiData() {
    return Single.just(Arrays.asList(
        new Card(1001, "4557885801554491", true),
        new Card(1002, "4557885801554492", true),
        new Card(1003, "4557885801554493", true),
        new Card(1004, "4557885801554494", false)
    ));
  }
}

