package com.github.thiagogarbazza.expressionresolver.util.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thiagogarbazza.expressionresolver.exception.ExpressionException;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class JsonUtils {

  public Collection<Map<String, Object>> objectToCollectionsOfMap(final Collection<Object> objects) {
    return CollectionUtils.emptyIfNull(objects).stream()
      .map(JsonUtils::objectToMap)
      .collect(toList());
  }

  public static JsonNode objectToJsonNode(final Object value) {
    try {
      final ObjectMapper objectMapper = ObjectMapperBuilder.build();
      final String content = objectMapper.writeValueAsString(value);

      return objectMapper.readTree(content);
    } catch (Exception e) {
      throw new ExpressionException("", e);
    }
  }

  public Map<String, Object> objectToMap(final Object object) {
    final Map<String, Object> map = ObjectMapperBuilder.build().convertValue(object, Map.class);

    return Collections.unmodifiableMap(map);
  }

  public static String objectToString(final Object value) {
    try {
      return ObjectMapperBuilder.build().writeValueAsString(value);
    } catch (Exception e) {
      throw new ExpressionException("", e);
    }
  }

  public static <T> T stringToObject(final String value, Class<T> type) {
    try {
      return ObjectMapperBuilder.build().readValue(value, type);
    } catch (Exception e) {
      throw new ExpressionException("", e);
    }
  }
}
