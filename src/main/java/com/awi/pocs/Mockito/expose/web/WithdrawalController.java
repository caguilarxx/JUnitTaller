package com.awi.pocs.Mockito.expose.web;

import com.awi.pocs.Mockito.business.CardBusinessService;
import com.awi.pocs.model.Card;
import com.awi.pocs.model.api.DemoRequest;
import io.reactivex.Observable;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/withdrawal")
public class WithdrawalController {

  private final CardBusinessService service;

  @GetMapping("/cards")
  public List<Card> getCards() {
    return service.getCards();
  }

  @GetMapping(
      value = "/cardsfiltered",
      produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  private Observable<Card> getCardsFiltered() {
    return service.filterActiveCardsObs(new DemoRequest());
  }
}