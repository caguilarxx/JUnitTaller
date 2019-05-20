package com.awi.pocs.JUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelloWorldTest {

  private HelloWorld helloWorld = new HelloWorld();

  @Test
  public void testHelloWorld() {

    assertEquals("Hello World", helloWorld.saludo());

  }
}
