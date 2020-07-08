package com.awi.pocs.JUnit;

import com.awi.pocs.model.Card;
import com.awi.pocs.model.Product;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssertsTest {

  private Asserts asserts = new Asserts();

  @Test
  public void testHasActiveCardsWithProducts() {

    List<Card> cards = Arrays.asList(new Card(1001, "4557885801554491", true, null),
        new Card(1002, "4557885801554492", true, Arrays.asList(new Product(11, "19315032768013005 "))),
        new Card(1003, "4557885801554493", false, Arrays.asList(new Product(12, "19315032768013006 "))),
        new Card(1004, "4557885801554494", false, Arrays.asList(new Product(15, "19315032768013007 "))));

    assertTrue(asserts.hasActiveCardsWithProducts().test(cards));
  }

  @Test
  public void testHasNonActiveCardsWithProducts() {

    List<Card> cards = Arrays.asList(new Card(1001, "4557885801554491", true, null),
        new Card(1002, "4557885801554492", false, Arrays.asList(new Product(11, "19315032768013005"))),
        new Card(1003, "4557885801554493", false, Arrays.asList(new Product(12, "19315032768013006"))),
        new Card(1004, "4557885801554494", false, Arrays.asList(new Product(15, "19315032768013007"))));

    assertFalse(asserts.hasActiveCardsWithProducts().test(cards));
  }

  @Test
  public void testGetCardSubBin() {

    Card card = new Card(1002, "4557885801554492", true, Arrays.asList(new Product(11, "19315032768013005 ")));

    assertEquals("58", asserts.getCardSubBin().apply(card));
  }

}
