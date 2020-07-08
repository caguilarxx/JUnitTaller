package com.awi.pocs.Repository;

import com.awi.pocs.Mockito.repository.CardRepository;
import com.awi.pocs.model.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CardRepositoryTest {

  @Autowired
  private CardRepository repository;

  @Test
  public void testFindAll() {
    List<Card> cards = repository.findAll();
    assertEquals(2, cards.size());
  }
}
