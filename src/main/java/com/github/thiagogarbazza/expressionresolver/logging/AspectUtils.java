package com.github.thiagogarbazza.expressionresolver.logging;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

@UtilityClass
public class AspectUtils {

  public static final String MSG_LOG_ENDING = "<> Finishing {0} and return {1} in {2}";
  public static final String MSG_LOG_ERROR = "<> Error executing {0} by reason \"{1}\" after {2}.";
  public static final String MSG_LOG_START = "<> Starting {0}";

  public static String toStringArgumentsBuild(Object value) {
    return value == null ? "null" : value.toString();
  }

  public static String toStringArgumentsBuild(JoinPoint joinPoint) {
    Object[] arguments = joinPoint.getArgs();

    if (ArrayUtils.isEmpty(arguments)) {
      return "void";
    }

    StringBuilder response = new StringBuilder();
    for (final Object arg : arguments) {
      if (response.length() != 0) {
        response.append(", ");
      }

      response.append(toStringArgumentsBuild(arg));
    }

    return response.toString();
  }

  public static String toStringSignatureBuilder(JoinPoint joinPoint) {
    Signature signature = joinPoint.getSignature();
    String clazz = signature.getDeclaringType().getSimpleName();
    String method = signature.getName();

    return new StringBuilder()
      .append(clazz)
      .append(".")
      .append(method)
      .append("(")
      .append(toStringArgumentsBuild(joinPoint))
      .append(")")
      .toString();
  }
}
