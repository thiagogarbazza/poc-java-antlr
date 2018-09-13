package com.github.thiagogarbazza.expressionresolve.domain;

import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Result {

  @Getter
  private final Object value;

  public Result(final Object value) {
    this.value = value;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(value).toHashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof Result) {
      Result rhs = (Result) obj;
      return new EqualsBuilder().append(value, rhs.value).isEquals();
    }
    return super.equals(obj);
  }

  @Override
  public String toString() {
    if (value != null) {
      return value.toString();
    }
    return "null";
  }
}
