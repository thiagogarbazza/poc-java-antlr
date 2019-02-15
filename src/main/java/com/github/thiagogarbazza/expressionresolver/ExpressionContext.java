package com.github.thiagogarbazza.expressionresolver;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import static com.github.thiagogarbazza.expressionresolver.util.PropertieUtil.messageProperty;
import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

@ToString(includeFieldNames = true)
@EqualsAndHashCode(of = {"variables"})
public class ExpressionContext {

  private static final Pattern PATTERN_VARIABLE = Pattern.compile("^\\$[a-zA-Z][a-zA-Z0-9_]*$");
  private static final String VAR_CURRENT_DATE = "$CURRENT_DATE";

  private final Map<String, Object> variables;

  public ExpressionContext() {
    this.variables = new TreeMap<>();
    setCurrentDate(LocalDate.now());
  }

  public Object get(final String variable) {
    validationVariable(variable);
    final Object value = variables.get(variable);
    notNull(value, messageProperty("validation.context.variable.not-present", variable));

    return value;
  }

  public ExpressionContext set(final String variable, final Object value) {
    validationVariable(variable);
    variables.put(variable, value);

    return this;
  }

  private void validationVariable(final String variable) {
    notBlank(variable, messageProperty("validation.context.variable.incorrect-name", variable));

    if (!PATTERN_VARIABLE.matcher(variable).find()) {
      throw new IllegalArgumentException(messageProperty("validation.context.variable.incorrect-name", variable));
    }
  }

  public LocalDate getCurrentDate() {
    final LocalDate currentDate = (LocalDate) this.variables.get(VAR_CURRENT_DATE);
    notNull(currentDate, messageProperty("validation.context.variable.not-present", VAR_CURRENT_DATE));

    return currentDate;
  }

  public ExpressionContext setCurrentDate(final LocalDate currentDate) {
    this.variables.put(VAR_CURRENT_DATE, currentDate);

    return this;
  }
}
