package com.awi.pocs.JUnit;

import com.awi.pocs.model.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
@Component
public class Asserts {

  public Predicate<List<Card>> hasActiveCard() {
    return cards -> cards.stream()
        .anyMatch(Card::getActive);
  }

  public Function<Card, String> getCardBin() {
    return card -> card.getNumber().substring(0, 6);
  }
}
