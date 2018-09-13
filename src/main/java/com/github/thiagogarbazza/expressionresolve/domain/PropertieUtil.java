package com.github.thiagogarbazza.expressionresolve.domain;

import java.util.ResourceBundle;

import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

public class PropertieUtil {

  private static final ResourceBundle APPLICATION_BUNDLE = getBundle("expression-resolver");

  public static String getPropertie(String key, Object... arguments) {
    if (isEmpty(arguments)) {
      return getPropertie(key);
    }
    return format(getPropertie(key), arguments);
  }

  public static String getPropertie(String key) {
    return trimToEmpty(APPLICATION_BUNDLE.getString(key));
  }
}
