package com.awi.pocs.JUnit;

import com.awi.pocs.model.Card;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertsTest {

  private CardsImpl cardsImpl = new CardsImpl();

  @Test
  public void testHasActiveCards() {

    /* Data */
    List<Card> cards = Arrays.asList(
        new Card(1001, "4557885801554491", true),
        new Card(1002, "4557885801554492", true),
        new Card(1003, "4557885801554493", false),
        new Card(1004, "4557885801554494", false)
    );

    /* Test */
    boolean resultado = cardsImpl.hasActiveCard().test(cards);

    /* Assert */
    assertTrue(resultado);
  }

  @Test
  public void testGetCardBin() {

    /* Data */
    Card card = new Card(1002, "4557885801554492", true);

    /* Test */
    String resultado = cardsImpl.getCardBin().apply(card);

    /* Assert */
    assertEquals("455788", resultado);
  }

  @Test
  public void testCardsSizeHamcrest() {

    /* Data */
    List<Card> cards = Arrays.asList(
        new Card(1001, "4557885801554491", true),
        new Card(1002, "4557885801554492", true),
        new Card(1003, "4557885801554493", false),
        new Card(1004, "4557885801554494", false)
    );

    /* Assert */
    assertThat(cards, hasSize(4));
    assertThat(cards, isA(List.class));
  }
}