package com.github.thiagogarbazza.expressionresolver;

import com.github.thiagogarbazza.expressionresolver.util.PropertieUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static org.apache.commons.lang3.Validate.notBlank;

@ToString(of = {"value"})
@EqualsAndHashCode(of = {"value"})
public class Expression {

  @Getter
  private final String value;

  public Expression(final String value) {
    notBlank(value, PropertieUtil.messageProperty("validation.expression.not-be-null-or-empty"));
    this.value = value;
  }
}
