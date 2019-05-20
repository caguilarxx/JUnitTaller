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

  public Predicate<List<Card>> hasActiveCardsWithProducts() {

    return cards -> cards.stream()
                .filter(card -> card.getProducts() != null && !card.getProducts().isEmpty())
                .anyMatch(card -> card.getActive());
  }

  public Function<Card, String> getCardSubBin(){
    return card -> card.getNumber().substring(6, 8);
  }

}
