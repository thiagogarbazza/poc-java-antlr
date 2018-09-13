package com.github.thiagogarbazza.expressionresolve.domain;

import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static com.github.thiagogarbazza.expressionresolve.domain.PropertieUtil.getPropertie;
import static org.apache.commons.lang3.StringUtils.trimToNull;
import static org.apache.commons.lang3.Validate.notNull;

public class Expression {

  @Getter
  private final String value;

  public Expression(final String value) {
    notNull(trimToNull(value), getPropertie("expression.not-be-null-or-empty"));
    this.value = value;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(value).toHashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof Expression) {
      Expression rhs = (Expression) obj;
      return new EqualsBuilder().append(value, rhs.value).isEquals();
    }
    return super.equals(obj);
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
