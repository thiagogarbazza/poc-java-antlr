package com.github.thiagogarbazza.expressionresolver.it.core;

import com.github.thiagogarbazza.expressionresolver.Expression;
import com.github.thiagogarbazza.expressionresolver.domain.Result;
import com.github.thiagogarbazza.expressionresolver.it.AbstractIT;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.TreeMap;

import static java.util.Arrays.asList;

public class ArrayPushIT extends AbstractIT {

  @Test
  public void verifyArrayPushBoolean() {
    assertExpression(
      new Expression("$a = [true, false]; arrayPush($a, true); return $a;"),
      new Result(asList(true, false, true)));
  }

  @Test
  public void verifyArrayPushDate() {
    assertExpression(
      new Expression("$a = [2018/01/31, 2018/02/28]; arrayPush($a, 2018/03/31); return $a;"),
      new Result(asList(LocalDate.of(2018, 1, 31), LocalDate.of(2018, 2, 28), LocalDate.of(2018, 3, 31))));
  }

  @Test
  public void verifyArrayPushJson() {
    final TreeMap<String, Object> item0 = new TreeMap<String, Object>() {{put("key-0", new BigDecimal("3"));}};
    final TreeMap<String, Object> item1 = new TreeMap<String, Object>() {{put("key-1", new BigDecimal("5"));}};
    final TreeMap<String, Object> item2 = new TreeMap<String, Object>() {{put("key-2", new BigDecimal("7"));}};

    assertExpression(
      new Expression("$a = [{'key-0':3}, {'key-1':5}]; arrayPush($a, {'key-2':7}); return $a;"),
      new Result(asList(item0, item1, item2)));
  }

  @Test
  public void verifyArrayPushNumber() {
    assertExpression(
      new Expression("$a = [1, 3]; arrayPush($a, 5); return $a;"),
      new Result(asList(new BigDecimal("1"), new BigDecimal("3"), new BigDecimal("5"))));
  }

  @Test
  public void verifyArrayPushString() {
    assertExpression(
      new Expression("$a = ['any-string-0', 'any-string-1']; arrayPush($a, 'any-string-2'); return $a;"),
      new Result(asList("any-string-0", "any-string-1", "any-string-2")));
  }
}
