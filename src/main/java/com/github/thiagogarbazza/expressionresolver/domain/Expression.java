package com.github.thiagogarbazza.expressionresolver.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.github.thiagogarbazza.expressionresolver.domain.PropertieUtil.getPropertie;
import static org.apache.commons.lang3.StringUtils.trimToNull;
import static org.apache.commons.lang3.Validate.notNull;

@Getter
@ToString
@EqualsAndHashCode
public class Expression {

  @Getter
  private final String value;

  public Expression(final String value) {
    notNull(trimToNull(value), getPropertie("expression.not-be-null-or-empty"));
    this.value = value;
  }
}
