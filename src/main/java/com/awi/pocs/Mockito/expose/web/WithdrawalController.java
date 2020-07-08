package com.awi.pocs.Mockito.expose.web;

import com.awi.pocs.Mockito.business.CardBusinessService;
import com.awi.pocs.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/withdrawal")
public class WithdrawalController {

  @Autowired
  private CardBusinessService service;

  @GetMapping
  public String helloWorld() {
    return "hello world";
  }

  @GetMapping("/card")
  public Card getCard() {
    return new Card(10, "4059102010101010", true, null);
  }

  @GetMapping("/cards")
  public List<Card> getCards() {
    return service.getCards();
  }

  @GetMapping("/cardsdb")
  public List<Card> getCardsFromDb() {
    return service.getCardsFromDatabase();
  }

}
