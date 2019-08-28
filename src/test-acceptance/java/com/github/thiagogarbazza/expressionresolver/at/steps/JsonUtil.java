package com.github.thiagogarbazza.expressionresolver.at.steps;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

class JsonUtil {

  public static Map<String, Object> stringToMap(String value) {

    ObjectMapper mapper = ObjectMapperBuilder.build();
    try {
      final Map map = mapper.readValue(value, Map.class);

      return map;
    } catch (IOException e) {
      throw new RuntimeException("Error read json.", e);
    }
  }
}
