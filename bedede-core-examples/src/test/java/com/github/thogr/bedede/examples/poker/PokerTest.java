package com.github.thogr.bedede.examples.poker;

import org.junit.Test;

import java.util.stream.Stream;

import static com.github.thogr.bedede.Bedede.*;
import static org.hamcrest.Matchers.*;
import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

public class PokerTest {

  @Test
  public void shouldSortCardsAccordingToRank() {
      given(Stream.of("10H", "1H", "KD", "QS"))
      .when(transforming(it->it.map(Card::new).sorted()))
      .when(transforming(it->it.map(Card::toString).collect(toList())))
      .then(it(), is(equalTo(asList("1H", "10H", "QS", "KD"))));
  }

  @Test
  public void shouldBeGreaterByRank() throws Exception {
      given(new Card("10H"))
      .then(it -> it.compareTo(new Card("1S")), is(greaterThan(0)));
  }

  @Test
  public void shouldBeGreaterByRank2() throws Exception {
      given(new Card("10H")).and(new Card("1S"))
      .then(the((first,  second) -> first.compareTo(second)), is(greaterThan(0)));
  }

  @Test
  public void shouldBeGreaterByRank3() throws Exception {
      given(new Card("10H")).and(new Card("1S"))
      .then(the(Card::compareTo), is(greaterThan(0)));
  }

  @Test
  public void shouldBeGreaterByRank4() throws Exception {
      given(new Card("10H")).and(new Card("1S"))
      .then(Card::compareTo, is(greaterThan(0)));
  }

  @Test
  public void shouldConvertToStringWithTheTheSyntax() throws Exception {
      given(new Card("10H"))
      .then(the(Card::toString), is("10H"));
  }

  @Test
  public void shouldConvertToStringWithFunction() throws Exception {
      given(new Card("10H"))
      .then(Card::toString, is("10H"));
  }

  @Test
  public void shouldConvertToStringWithFunctionName() throws Exception {
      given(new Card("10H"))
      .then(the("toString"), is("10H"));
  }

  @Test
  public void shouldConvertToStringWithLambda() throws Exception {
      given(new Card("10H"))
      .then(it -> it.toString(), is("10H"));
  }
}
