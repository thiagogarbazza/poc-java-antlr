package com.github.thiagogarbazza.expressionresolver;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.github.thiagogarbazza.expressionresolver.util.PropertieUtil.getPropertie;
import static org.apache.commons.lang3.StringUtils.trimToNull;
import static org.apache.commons.lang3.Validate.isInstanceOf;
import static org.apache.commons.lang3.Validate.notNull;

@Getter
@ToString
@EqualsAndHashCode
public class ExpressionContext {

  private final Map<String, Object> variables;

  public ExpressionContext() {
    variables = new HashMap<String, Object>();
    set("today", LocalDate.now());
  }

  public Object get(final String variable) {
    final String key = buildKey(variable);
    final Object value = variables.get(key);
    notNull(value, getPropertie("context.variable.not-present"), key);

    return value;
  }

  public <T> T get(final String variable, Class<T> type) {
    final String key = buildKey(variable);
    final Object value = variables.get(key);
    notNull(value, getPropertie("context.variable.not-present"), key);
    isInstanceOf(type, value, getPropertie("context.variable.not-instance-valid"), key, type);
    return (T) value;
  }

  public ExpressionContext set(final String variable, final Object value) {
    final String key = buildKey(variable);
    variables.put(key, value);
    return this;
  }

  private String buildKey(final String variable) {
    final String key = trimToNull(variable);
    notNull(key, getPropertie("context.variable.name-not-be-null-or-empty"));
    return key;
  }
}
