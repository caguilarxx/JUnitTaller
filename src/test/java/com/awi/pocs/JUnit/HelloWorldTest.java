package com.awi.pocs.JUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {

  private HelloWorld helloWorld = new HelloWorld();

  @Test
  public void testHelloWorld() {

    //expected - actual
    assertEquals("Hello World", helloWorld.saludo());
  }
}
