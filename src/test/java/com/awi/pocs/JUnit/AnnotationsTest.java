package com.awi.pocs.JUnit;

import com.awi.pocs.model.Card;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
public class AnnotationsTest {

  private CardsImpl cardsImpl = new CardsImpl();
  private List<Card> cards;

  @BeforeAll
  public static void init() {
    log.info("===> @BeforeAll init");
  }

  @BeforeEach
  public void setUp() {
    log.info("===> @BeforeEach setUp");

    cards = Arrays.asList(
        new Card(1001, "4557885801554491", true),
        new Card(1002, "4557885901554492", true),
        new Card(1003, "4557885801554493", false),
        new Card(1004, "4557885801554494", false));
  }

  @AfterEach
  public void finalize() {
    log.info("===> @AfterEach finalize");
  }

  @AfterAll
  public static void end() {
    log.info("===> @AfterAll end");
  }

  @Test
  public void testHasActiveCard() {

    log.info("===> @Test testHasActiveCard");
    assertTrue(cardsImpl.hasActiveCard().test(cards));
    cards = null;
  }

  @Test
  public void testGetCardBin() {

    log.info("===> @Test testGetCardBin");
    assertEquals("455788", cardsImpl.getCardBin().apply(cards.get(0)));
  }

  @Test
  @Disabled
  public void testGetCardBin2() {

    log.info("===> @Test testGetCardBin2");
    assertEquals("455788", cardsImpl.getCardBin().apply(cards.get(1)));
  }
}