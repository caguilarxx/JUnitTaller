package com.awi.pocs.JUnit;

import com.awi.pocs.model.Card;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class CardsImpl {

  public Predicate<List<Card>> hasActiveCard() {
    return cards -> cards.stream()
        .anyMatch(Card::getActive);
  }

  public Function<Card, String> getCardBin() {
    return card -> card.getNumber().substring(0, 6);
  }
}
