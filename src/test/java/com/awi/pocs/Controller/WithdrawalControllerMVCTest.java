package com.awi.pocs.Controller;

import com.awi.pocs.Mockito.business.CardBusinessService;
import com.awi.pocs.Mockito.expose.web.WithdrawalController;
import com.awi.pocs.model.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WithdrawalController.class)
public class WithdrawalControllerMVCTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CardBusinessService service;

  @Test
  public void getCardsTest() throws Exception {

    /* Mock */
    when(service.getCards()).thenReturn(asList(
        new Card(10, "4059102010101010", true),
        new Card(20, "4059102020202020", true)));

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/cards")
        .accept(MediaType.APPLICATION_JSON);

    /* Test & Asserts */
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().json("[{ id: 10},{ id: 20}]", false))
        .andReturn();
  }

  @Test
  public void getCardsTestExc() throws Exception {

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/cardsx")
        .accept(MediaType.APPLICATION_JSON);

    /* Test & Asserts */
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isNotFound())
        .andReturn();
  }
}