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

  private Asserts asserts = new Asserts();

  @Test
  public void testHasActiveCards() {

    List<Card> cards = Arrays.asList(
        new Card(1001, "4557885801554491", true),
        new Card(1002, "4557885801554492", true),
        new Card(1003, "4557885801554493", false),
        new Card(1004, "4557885801554494", false)
    );

    assertTrue(asserts.hasActiveCard().test(cards));
  }

  @Test
  public void testGetCardBin() {

    Card card = new Card(1002, "4557885801554492", true);

    assertEquals("455788", asserts.getCardBin().apply(card));
  }

  @Test
  public void testCardsSizeHamcrest() {

    List<Card> cards = Arrays.asList(
        new Card(1001, "4557885801554491", true),
        new Card(1002, "4557885801554492", true),
        new Card(1003, "4557885801554493", false),
        new Card(1004, "4557885801554494", false)
    );

    assertEquals(4, cards.size());
//    assertThat(cards, hasSize(4));
//    assertThat(cards, isA(List.class));
  }
}