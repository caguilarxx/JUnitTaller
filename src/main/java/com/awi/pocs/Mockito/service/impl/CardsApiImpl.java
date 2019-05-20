package com.awi.pocs.Mockito.service.impl;

import com.awi.pocs.Mockito.service.CardsApi;
import com.awi.pocs.model.api.CardApi;
import io.reactivex.Single;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CardsApiImpl implements CardsApi {
  @Override
  public Single<List<CardApi>> getCards(String documentNumber) {
    return null;
  }
}
