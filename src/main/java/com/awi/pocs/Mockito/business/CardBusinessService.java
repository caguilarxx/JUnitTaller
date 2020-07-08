package com.awi.pocs.Mockito.business;

import com.awi.pocs.model.Card;
import com.awi.pocs.model.api.DemoRequest;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

public interface CardBusinessService {

  List<Card> getCards();

  List<Card> getCardsFromDatabase();

  Single<List<Card>> filterActiveCards(DemoRequest request);

  Observable<Card> filterActiveCardsObs(DemoRequest request);

}
