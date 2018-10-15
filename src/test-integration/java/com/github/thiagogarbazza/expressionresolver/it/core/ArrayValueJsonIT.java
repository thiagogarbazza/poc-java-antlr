package com.github.thiagogarbazza.expressionresolver.it.core;

import com.github.thiagogarbazza.expressionresolver.domain.Expression;
import com.github.thiagogarbazza.expressionresolver.domain.Result;
import com.github.thiagogarbazza.expressionresolver.it.AbstractIT;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.TreeMap;

import static java.util.Arrays.asList;

public class ArrayValueJsonIT extends AbstractIT {

  @Test
  public void verifyArrayValueJson() {
    final TreeMap<String, Object> item0 = new TreeMap<String, Object>() {{
      put("key-0", "value0");
      put("key-1", true);
    }};
    final TreeMap<String, Object> item1 = new TreeMap<String, Object>() {{
      put("key", asList(new BigDecimal("1"), new BigDecimal("3"), new BigDecimal("5")));
    }};
    final TreeMap<String, Object> item2 = new TreeMap<String, Object>() {{
      put("key-0", "value0");
      put("key-1", new BigDecimal("7"));
    }};
    final TreeMap<String, Object> item3 = new TreeMap<String, Object>() {{
      put("key-0", new TreeMap<String, Object>() {{ put("sub-key", new BigDecimal("5")); }});
    }};

    assertExpression(
      new Expression("return [ {'key-0':'value0', 'key-1':true}, {'key':[1,3,5]}, {'key-0':'value0', 'key-1':7}, {'key-0': {'sub-key': 5}} ];"),
      new Result(asList(item0, item1, item2, item3)));
  }

  @Test
  public void verifyArrayValueJsonEmpty() {
    assertExpression(
      new Expression("return [{ }];"),
      new Result(asList(new TreeMap<String, Object>())));
  }
}
