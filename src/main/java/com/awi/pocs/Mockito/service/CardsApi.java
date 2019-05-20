package com.awi.pocs.Mockito.service;

import com.awi.pocs.model.Card;
import com.awi.pocs.model.api.CardApi;
import io.reactivex.Single;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CardsApi {

  public Single<List<CardApi>> getCards(String documentNumber);

}
