package com.awi.pocs.Controller;

import com.awi.pocs.Mockito.business.CardBusinessService;
import com.awi.pocs.Mockito.expose.web.WithdrawalController;
import com.awi.pocs.model.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WithdrawalControllerMockitoTest {

  @InjectMocks
  private WithdrawalController controller;

  @Mock
  private CardBusinessService service;

  @Test
  public void getCardsTest() {

    /* Mock */
    when(service.getCards()).thenReturn(asList(
        new Card(10, "4059102010101010", true),
        new Card(20, "4059102020202020", true)));

    /* Test */
    List<Card> cards = controller.getCards();

    /* Asserts */
    assertEquals(2, cards.size());
  }
}