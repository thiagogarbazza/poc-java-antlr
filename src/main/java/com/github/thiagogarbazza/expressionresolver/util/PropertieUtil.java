package com.github.thiagogarbazza.expressionresolver.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

@UtilityClass
public class PropertieUtil {

  private static final ResourceBundle APPLICATION_BUNDLE = getBundle("expression-resolver");
  private static final String PROPERTY_FILE_MESSAGE = "expression-resolver-messages.properties";

  public static String getPropertie(final String key, final Object... arguments) {
    if (isEmpty(arguments)) {
      return getPropertie(key);
    }

    return format(getPropertie(key), arguments);
  }

  public static String getPropertie(final String key) {

    return trimToEmpty(APPLICATION_BUNDLE.getString(key));
  }

  public static String messageProperty(final String key) {
    return property(PROPERTY_FILE_MESSAGE, key);
  }

  public static String messageProperty(final String key, final Object... args) {
    return property(PROPERTY_FILE_MESSAGE, key, args);
  }

  private static InputStream getResource(final String filename) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
  }

  private static String property(final String filename, final String key) {
    Properties properties = new Properties();

    try (InputStream input = getResource(filename)) {
      if (input == null) {
        throw new IllegalArgumentException(format("Configuration file {0} not found.", filename));
      }

      properties.load(input);
      return properties.getProperty(key);
    } catch (IOException e) {
      throw new IllegalArgumentException(format("Error reading configuration file {0}.", filename));
    }
  }

  private static String property(final String filename, final String key, final Object... args) {
    if (args == null) {
      return property(filename, key);
    }

    return format(property(filename, key), args);
  }
}
