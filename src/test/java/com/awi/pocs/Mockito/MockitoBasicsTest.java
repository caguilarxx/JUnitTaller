package com.awi.pocs.Mockito;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class MockitoBasicsTest {

  @Mock
  private List<String> lstNames;

  @Test
  public void testHasActiveCards() {

    when(lstNames.get(0)).thenReturn("Carlos");
    when(lstNames.size()).thenReturn(5);

    /* Test */
    String name1 = lstNames.get(0);
    int size = lstNames.size();

    /* Assert */
    assertEquals("Carlos", name1);
    assertEquals(5, size);
  }
}
