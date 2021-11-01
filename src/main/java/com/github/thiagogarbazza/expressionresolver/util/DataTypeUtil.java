package com.github.thiagogarbazza.expressionresolver.util;

import lombok.experimental.UtilityClass;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.ClassUtils.isAssignable;

@UtilityClass
public class DataTypeUtil {

  public static boolean isSameType(final Object left, final Object right, final Class<?> tipo) {
    return (isNull(left) || isAssignable(left.getClass(), tipo))
      && (isNull(right) || isAssignable(right.getClass(), tipo));
  }
}
