package com.awi.pocs.Mockito.service;

import com.awi.pocs.model.Card;
import io.reactivex.Single;

import java.util.List;

public interface CardApi {

  Single<List<Card>> getCards(String documentNumber);

}
