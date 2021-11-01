package com.github.thiagogarbazza.expressionresolver;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(of = {"value"})
@EqualsAndHashCode(of = {"value"})
public class Result {

  @Getter
  private final Object value;

  public Result(final Object value) {
    this.value = value;
  }
}
