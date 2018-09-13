package com.github.thiagogarbazza.expressionresolve.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Result {

  private final Object value;

  public Result(final Object value) {
    this.value = value;
  }
}
