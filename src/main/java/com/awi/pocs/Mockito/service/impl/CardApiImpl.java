package com.awi.pocs.Mockito.service.impl;

import com.awi.pocs.Mockito.service.CardApi;
import com.awi.pocs.model.Card;
import io.reactivex.Single;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CardApiImpl implements CardApi {

  @Override
  public Single<List<Card>> getCards(String documentNumber) {
    return null;
  }
}
