package com.awi.pocs.Mockito.business;

import com.awi.pocs.model.Card;
import com.awi.pocs.model.api.DemoRequest;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

public interface DemoBusinessService {

  public Single<List<Card>> filterActiveCards(DemoRequest request);

  public Observable<Card> filterActiveCardsObs(DemoRequest request);

}
