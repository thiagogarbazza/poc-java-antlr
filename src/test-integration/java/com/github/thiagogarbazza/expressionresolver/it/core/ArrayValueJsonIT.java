package com.github.thiagogarbazza.expressionresolver.it.core;

import com.github.thiagogarbazza.expressionresolver.domain.Expression;
import com.github.thiagogarbazza.expressionresolver.domain.Result;
import com.github.thiagogarbazza.expressionresolver.it.AbstractIT;
import org.junit.Test;

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
      put("key", "value1");
    }};
    final TreeMap<String, Object> item2 = new TreeMap<String, Object>() {{
      put("key-0", "value0");
      put("key-1", "value1");
    }};

    assertExpression(new Expression("return [ {'key-0':'value0', 'key-1':true}, {'key':'value1'}, {'key-0':'value0', 'key-1':'value1'} ];"),
      new Result(asList(item0, item1, item2)));
  }

  @Test
  public void verifyArrayValueJsonEmpty() {
    assertExpression(new Expression("return [{ }];"), new Result(asList(new TreeMap<String, Object>())));
  }
}
