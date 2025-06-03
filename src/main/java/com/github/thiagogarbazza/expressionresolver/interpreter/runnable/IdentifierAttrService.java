package com.github.thiagogarbazza.expressionresolver.interpreter.runnable;

import com.github.thiagogarbazza.expressionresolver.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.exception.RunnableExpressionException;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.TreeMap;

import static com.github.thiagogarbazza.expressionresolver.exception.RunnableExpressionException.isDataTypeOfObject;
import static com.github.thiagogarbazza.expressionresolver.util.PropertieUtil.messageProperty;
import static lombok.AccessLevel.PROTECTED;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@NoArgsConstructor(access = PROTECTED, onConstructor_ = {@Inject})
class IdentifierAttrService {

  Object getter(final ExpressionContext expressionContext, final String variablePath) {
    final Variable variable = Variable.valueOf(variablePath);
    final Object value = expressionContext.get(variable.getName());

    isDataTypeOfObject(value, variable.getName());

    return getter(value, variablePath, variable.getSubPath());
  }

  void setter(final ExpressionContext expressionContext, final String variablePath, final Object newValue) {
    final Variable variable = Variable.valueOf(variablePath);

    if (!expressionContext.isVariablePresent(variable.getName()) || !(expressionContext.get(variable.getName()) instanceof Map)) {
      expressionContext.set(variable.getName(), new TreeMap<String, Object>());
    }

    setter(expressionContext.get(variable.getName()), variable.getSubPath(), newValue);
  }

  private Object getter(final Object objectToSearch, final String variablePath, final String attrPath) {
    final Variable variable = Variable.valueOf(attrPath);
    final Map<String, Object> objectToSearchMap = (Map<String, Object>) objectToSearch;

    if (!objectToSearchMap.containsKey(variable.getName())) {
      throw new RunnableExpressionException(messageProperty("validation.context.variable.not-present", variablePath));
    }

    final Object value = objectToSearchMap.get(variable.getName());
    if (isBlank(variable.getSubPath())) {
      return value;
    } else {
      isDataTypeOfObject(value, variablePath);

      return getter(value, variablePath, variable.getSubPath());
    }
  }

  private void setter(final Object objectToUpdate, final String variablePath, final Object newValue) {
    final Variable variable = Variable.valueOf(variablePath);
    final Map<String, Object> objectToUpdateMap = (Map<String, Object>) objectToUpdate;

    if (isBlank(variable.getSubPath())) {
      objectToUpdateMap.put(variable.getName(), newValue);
    } else {
      if (!objectToUpdateMap.containsKey(variable.getName()) || !(objectToUpdateMap.get(variable.getName()) instanceof Map)) {
        objectToUpdateMap.put(variable.getName(), new TreeMap<String, Object>());
      }

      setter(objectToUpdateMap.get(variable.getName()), variable.getSubPath(), newValue);
    }
  }

  @Getter
  @Builder
  @EqualsAndHashCode
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
  private static class Variable {

    private final String name;
    private final String subPath;

    public static Variable valueOf(final String variable) {
      final String[] paths = variable.split("\\.", 2);

      return Variable.builder()
        .name(paths[0])
        .subPath(paths.length > 1 ? paths[1] : EMPTY)
        .build();
    }
  }
}
