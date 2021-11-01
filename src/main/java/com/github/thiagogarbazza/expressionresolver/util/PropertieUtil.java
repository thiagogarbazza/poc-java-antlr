package com.github.thiagogarbazza.expressionresolver.util;

import com.github.thiagogarbazza.expressionresolver.exception.RunnableExpressionException;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.text.MessageFormat.format;

@UtilityClass
public class PropertieUtil {

  private static final String PROPERTY_FILE_MESSAGE = "expression-resolver-messages.properties";

  public static String messageProperty(final String key) {
    return property(PROPERTY_FILE_MESSAGE, key);
  }

  public static String messageProperty(final String key, final Object... args) {
    return property(PROPERTY_FILE_MESSAGE, key, args);
  }

  @lombok.Generated
  static String property(final String filename, final String key) {
    Properties properties = new Properties();

    try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)) {
      properties.load(input);
      return properties.getProperty(key);
    } catch (IOException e) {
      throw new RunnableExpressionException(format("Error reading configuration file {0}.", filename), e);
    }
  }

  static String property(final String filename, final String key, final Object... args) {
    return format(property(filename, key), args);
  }
}
