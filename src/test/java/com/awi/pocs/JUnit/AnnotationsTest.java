package com.awi.pocs.JUnit;

import com.awi.pocs.model.Card;
import com.awi.pocs.model.Product;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@SpringBootTest
public class AnnotationsTest {

  @Autowired
  private Asserts asserts;

  private List<Card> cards;

  @BeforeAll
  public static void init() {
    log.info("===> @BeforeClass init");
  }

  @BeforeEach
  public void setUp() {
    log.info("===> @Before setUp");

    cards = Arrays.asList(
        new Card(1001, "4557885801554491", true, null),
        new Card(1002, "4557885901554492", true, Arrays.asList(new Product(11, "19315032768013005 "))),
        new Card(1003, "4557885801554493", false, Arrays.asList(new Product(12, "19315032768013006 "))),
        new Card(1004, "4557885801554494", false, Arrays.asList(new Product(15, "19315032768013007 "))));
  }

  @AfterEach
  public void finalize() {
    log.info("===> @After finalize");
  }

  @AfterAll
  public static void end() {
    log.info("===> @AfterClass end");
  }

  @Test
  public void testHasActiveCardsWithProducts() {

    log.info("===> @Test testHasActiveCardsWithProducts");
    assertTrue(asserts.hasActiveCardsWithProducts().test(cards));
    cards = null;
  }

  @Test
  public void testGetCardSubBin() {

    log.info("===> @Test testGetCardSubBin");
    assertEquals("58", asserts.getCardSubBin().apply(cards.get(0)));
  }

  @Test
  @Ignore
  public void testGetCardSubBin2() {

    log.info("===> @Test testGetCardSubBin2");
    assertEquals("59", asserts.getCardSubBin().apply(cards.get(1)));
  }

}
