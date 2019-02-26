package com.github.thiagogarbazza.expressionresolver.logging;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

@UtilityClass
public class AspectUtils {

  public static final String MSG_LOG_ENDING = "{0} Finishing {1} and return \"{2}\" in {3}";
  public static final String MSG_LOG_ERROR = "{0} Error executing {1} by reason \"{2}\" after {3}.";
  public static final String MSG_LOG_START = "{0} Starting {1}";
  public static final String MSG_SYMBOL_ADAPTER = "<!>>";
  public static final String MSG_SYMBOL_API = "<!>>>>>>>>>>>>";
  public static final String MSG_SYMBOL_RESOLVER = "<!>>>>>>>>";
  public static final String MSG_SYMBOL_SERVICE = "<!>>>>>";

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
