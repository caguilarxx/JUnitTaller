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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WithdrawalController.class) //load only bean
public class WithdrawalControllerMVCTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CardBusinessService service;

  @Test
  public void helloWorldTest() throws Exception {

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/withdrawal")
        .accept(MediaType.APPLICATION_JSON);

    MvcResult mvcResult = mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(content().string("hello world"))
        .andReturn();

    assertEquals("hello world", mvcResult.getResponse().getContentAsString());
  }

  @Test
  public void getCardTest() throws Exception {

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/withdrawal/card")
        .accept(MediaType.APPLICATION_JSON);

    MvcResult mvcResult = mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())  //assert1
        .andExpect(content().json("{id: 10}", false))
        .andReturn();

  }

  @Test
  public void getCardsTest() throws Exception {

    when(service.getCards()).thenReturn(Arrays.asList(new Card(10, "4059102010101010", true, null),
        new Card(20, "4059102020202020", true, null)));

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/withdrawal/cards")
        .accept(MediaType.APPLICATION_JSON);

    MvcResult mvcResult = mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())  //assert1
        .andExpect(content().json("[{ id: 10},{ id: 20}]", false))
        .andReturn();

  }

}
