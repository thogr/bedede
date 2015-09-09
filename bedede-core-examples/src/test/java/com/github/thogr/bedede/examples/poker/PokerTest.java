package com.github.thogr.bedede.examples.poker;

import static com.github.thogr.bedede.CoreExpressions.a;
import static com.github.thogr.bedede.CoreExpressions.given;
import static com.github.thogr.bedede.CoreExpressions.it;
import static com.github.thogr.bedede.CoreExpressions.the;
import static com.github.thogr.bedede.CoreExpressions.transforming;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import java.util.stream.Stream;

import org.junit.Test;

public class PokerTest {

  @Test
  public void shouldSortCardsAccordingToRank() {
      given(a(Stream.of("10H", "1H", "KD", "QS")))
      .when(transforming(it->it.map(Card::new).sorted()))
      .when(transforming(it->it.map(Card::toString).collect(toList())))
      .then(it(), is(equalTo(asList("1H", "10H", "QS", "KD"))));
  }

  @Test
  public void shouldBeGreaterByRank() throws Exception {
      given(a(new Card("10H")))
      .then(it -> it.compareTo(new Card("1S")), is(greaterThan(0)));
  }

  @Test
  public void shouldBeGreaterByRank2() throws Exception {
      given(a(new Card("10H"))).and(a(new Card("1S")))
      .then(the((first,  second) -> first.compareTo(second)), is(greaterThan(0)));
  }

  @Test
  public void shouldBeGreaterByRank3() throws Exception {
      given(a(new Card("10H"))).and(a(new Card("1S")))
      .then(the(Card::compareTo), is(greaterThan(0)));
  }

  @Test
  public void shouldBeGreaterByRank4() throws Exception {
      given(a(new Card("10H"))).and(a(new Card("1S")))
      .then(Card::compareTo, is(greaterThan(0)));
  }

  @Test
  public void shouldConvertToStringWithTheTheSyntax() throws Exception {
      given(a(new Card("10H")))
      .then(the(Card::toString), is("10H"));
  }

  @Test
  public void shouldConvertToStringWithFunction() throws Exception {
      given(a(new Card("10H")))
      .then(Card::toString, is("10H"));
  }

  @Test
  public void shouldConvertToStringWithLambda() throws Exception {
      given(a(new Card("10H")))
      .then(it -> it.toString(), is("10H"));
  }
}
