package com.github.thiagogarbazza.expressionresolver;

import com.github.thiagogarbazza.expressionresolver.exception.RunnableExpressionException;
import com.github.thiagogarbazza.expressionresolver.exception.SyntaxExpressionException;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import static com.github.thiagogarbazza.expressionresolver.util.PropertieUtil.messageProperty;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

@ToString(of = {"variables"})
@EqualsAndHashCode(of = {"variables"})
public class ExpressionContext {

  private static final Pattern PATTERN_VARIABLE = Pattern.compile("^\\$[a-zA-Z][a-zA-Z0-9_]*$");
  private static final String VAR_CURRENT_DATE = "$CURRENT_DATE";

  private final Map<String, Object> variables;

  public ExpressionContext() {
    this.variables = new TreeMap<>();
    this.variables.put(VAR_CURRENT_DATE, LocalDate.now());
  }

  public Object get(final String variable) {
    validationVariable(variable);

    if (!isVariablePresent(variable)) {
      throw new RunnableExpressionException(messageProperty("validation.context.variable.not-present", variable));
    }

    return variables.get(variable);
  }

  public boolean isVariablePresent(final String variable) {
    final String key = trimToEmpty(variable);

    return this.variables.containsKey(key);
  }

  public ExpressionContext set(final String variable, final Object value) {
    validationVariable(variable);
    variables.put(variable, value);

    return this;
  }

  private void validationVariable(final String variable) {
    if (isBlank(variable) || !PATTERN_VARIABLE.matcher(variable).find()) {
      throw new SyntaxExpressionException(messageProperty("validation.context.variable.incorrect-name", variable));
    }
  }
}
