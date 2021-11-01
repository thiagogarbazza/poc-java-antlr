package com.github.thiagogarbazza.expressionresolver.util.json;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.github.thiagogarbazza.expressionresolver.util.NumberUtil.toBigDecimal;
import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilsTest {

  @Nested
  class TypeBoolean {

    @Test
    void verifyDeserializer() {
      assertEquals(true, JsonUtils.stringToObject("true", Object.class));
      assertEquals(false, JsonUtils.stringToObject("false", Object.class));
    }

    @Test
    void verifyDeserializerCollection() {
      assertEquals(asList(true), JsonUtils.stringToObject("[true]", Object.class));
      assertEquals(asList(true, true, true), JsonUtils.stringToObject("[true, true, true]", Object.class));
      assertEquals(asList(false), JsonUtils.stringToObject("[false]", Object.class));
      assertEquals(asList(false, false, false), JsonUtils.stringToObject("[false, false, false]", Object.class));
    }

    @Test
    void verifySerializer() {
      assertEquals("true", JsonUtils.objectToString(true));
      assertEquals("false", JsonUtils.objectToString(false));
    }

    @Test
    void verifySerializerCollection() {
      assertEquals("[true]", JsonUtils.objectToString(asList(true)));
      assertEquals("[true,true,true]", JsonUtils.objectToString(asList(true, true, true)));

      assertEquals("[false]", JsonUtils.objectToString(asList(false)));
      assertEquals("[false,false,false]", JsonUtils.objectToString(asList(false, false, false)));
    }
  }

  @Nested
  class TypeDate {

    @Test
    void verifyDeserializer() {
      assertEquals(LocalDate.of(2018, 12, 31), JsonUtils.stringToObject("\"2018-12-31\"", Object.class));
      assertEquals(LocalDate.of(2019, 1, 1), JsonUtils.stringToObject("\"2019-01-01\"", Object.class));
    }

    @Test
    void verifyDeserializerCollection() {
      assertEquals(asList(LocalDate.of(2018, 12, 31)), JsonUtils.stringToObject("[\"2018-12-31\"]", Object.class));
      assertEquals(asList(LocalDate.of(2018, 12, 31), LocalDate.of(2019, 1, 1)), JsonUtils.stringToObject("[\"2018-12-31\", \"2019-01-01\"]", Object.class));
    }

    @Test
    void verifySerializer() {
      assertEquals("\"2018-12-31\"", JsonUtils.objectToString(LocalDate.of(2018, 12, 31)));
      assertEquals("\"2019-01-01\"", JsonUtils.objectToString(LocalDate.of(2019, 1, 1)));
    }

    @Test
    void verifySerializerCollection() {
      assertEquals("[\"2018-12-31\"]", JsonUtils.objectToString(asList(LocalDate.of(2018, 12, 31))));
      assertEquals("[\"2018-12-31\",\"2019-01-01\"]", JsonUtils.objectToString(asList(LocalDate.of(2018, 12, 31), LocalDate.of(2019, 1, 1))));
    }
  }

  @Nested
  class TypeDateTime {

    @Test
    void verifyDeserializer() {
      assertEquals(LocalDateTime.of(2018, 12, 31, 23, 59, 59, 0), JsonUtils.stringToObject("\"2018-12-31T23:59:59.000\"", Object.class));
      assertEquals(LocalDateTime.of(2019, 1, 1, 23, 59, 59, 0), JsonUtils.stringToObject("\"2019-01-01T23:59:59.000\"", Object.class));
    }

    @Test
    void verifyDeserializerCollection() {
      assertEquals(asList(LocalDateTime.of(2018, 12, 31, 23, 59, 59, 0)), JsonUtils.stringToObject("[\"2018-12-31T23:59:59.000\"]", Object.class));
      assertEquals(asList(LocalDateTime.of(2018, 12, 31, 23, 59, 59, 0), LocalDateTime.of(2019, 1, 1, 23, 59, 59)),
        JsonUtils.stringToObject("[\"2018-12-31T23:59:59.000\", \"2019-01-01T23:59:59.000\"]", Object.class));
    }

    @Test
    void verifySerializer() {
      assertEquals("\"2018-12-31T23:59:59.000000009\"", JsonUtils.objectToString(LocalDateTime.of(2018, 12, 31, 23, 59, 59, 9)));
      assertEquals("\"2019-01-01T23:59:59.000000009\"", JsonUtils.objectToString(LocalDateTime.of(2019, 1, 1, 23, 59, 59, 9)));
    }

    @Test
    void verifySerializerCollection() {
      assertEquals("[\"2018-12-31T23:59:59.000000009\"]", JsonUtils.objectToString(asList(LocalDateTime.of(2018, 12, 31, 23, 59, 59, 9))));
      assertEquals("[\"2018-12-31T23:59:59.000000009\",\"2019-01-01T23:59:59.000000009\"]",
        JsonUtils.objectToString(asList(LocalDateTime.of(2018, 12, 31, 23, 59, 59, 9), LocalDateTime.of(2019, 1, 1, 23, 59, 59, 9))));
    }
  }

  @Nested
  class TypeNumber {

    @Test
    void verifyDeserializer() {
      assertEquals(toBigDecimal("1"), JsonUtils.stringToObject("1", Object.class));
      assertEquals(toBigDecimal("1.1"), JsonUtils.stringToObject("1.1", Object.class));
    }

    @Test
    void verifyDeserializerCollection() {
      assertEquals(asList(toBigDecimal("1")), JsonUtils.stringToObject("[1]", Object.class));
      assertEquals(asList(toBigDecimal("1"), toBigDecimal("2"), toBigDecimal("3")), JsonUtils.stringToObject("[1, 2, 3]", Object.class));

      assertEquals(asList(toBigDecimal("1.1")), JsonUtils.stringToObject("[1.1]", Object.class));
      assertEquals(asList(toBigDecimal("1.1"), toBigDecimal("2.12"), toBigDecimal("3.123")), JsonUtils.stringToObject("[1.1, 2.12, 3.123]", Object.class));
    }

    @Test
    void verifySerializer() {
      assertEquals("1.0000000000000000000000000000000000", JsonUtils.objectToString(toBigDecimal("1")));
      assertEquals("1.1000000000000000000000000000000000", JsonUtils.objectToString(toBigDecimal("1.1")));
    }

    @Test
    void verifySerializerCollection() {
      assertEquals("[1.0000000000000000000000000000000000]", JsonUtils.objectToString(asList(toBigDecimal("1"))));
      assertEquals("[1.0000000000000000000000000000000000,2.0000000000000000000000000000000000,3.0000000000000000000000000000000000]",
        JsonUtils.objectToString(asList(toBigDecimal("1"), toBigDecimal("2"), toBigDecimal("3"))));

      assertEquals("[1.1000000000000000000000000000000000]", JsonUtils.objectToString(asList(toBigDecimal("1.1"))));
      assertEquals("[1.1000000000000000000000000000000000,2.1200000000000000000000000000000000,3.1230000000000000000000000000000000]",
        JsonUtils.objectToString(asList(toBigDecimal("1.1"), toBigDecimal("2.12"), toBigDecimal("3.123"))));
    }
  }

  @Nested
  class TypeObject {

    @Test
    void verifyDeserializer() {
      assertEquals(new HashMap<>(), JsonUtils.stringToObject("{}", Object.class));
      assertEquals(toMap("any-key", "any-value"), JsonUtils.stringToObject("{\"any-key\": \"any-value\"}", Object.class));
      assertEquals(toMap("any-key", toMap("any-key", "any-value")), JsonUtils.stringToObject("{\"any-key\": {\"any-key\": \"any-value\"}}", Object.class));
    }

    @Test
    void verifyDeserializerCollection() {
      assertEquals(asList(new HashMap<>()), JsonUtils.stringToObject("[{}]", Object.class));
      assertEquals(asList(toMap("any-key", "any-value")), JsonUtils.stringToObject("[{\"any-key\": \"any-value\"}]", Object.class));
      assertEquals(asList(toMap("key01", "v01"), toMap("key01", true)), JsonUtils.stringToObject("[{\"key01\": \"v01\"}, {\"key01\": true}]", Object.class));
    }

    @Test
    void verifySerializer() {
      assertEquals("{}", JsonUtils.objectToString(new HashMap<>()));
      assertEquals("{\"any-key\":\"any-value\"}", JsonUtils.objectToString(toMap("any-key", "any-value")));
      assertEquals("{\"any-key\":{\"any-key\":\"any-value\"}}", JsonUtils.objectToString(toMap("any-key", toMap("any-key", "any-value"))));
    }

    @Test
    void verifySerializerCollection() {
      assertEquals("[{}]", JsonUtils.objectToString(singleton(new HashMap<>())));
      assertEquals("[{\"any-key\":\"any-value\"}]", JsonUtils.objectToString(singleton(toMap("any-key", "any-value"))));
      assertEquals("[{\"key01\":\"v01\"},{\"key01\":true}]", JsonUtils.objectToString(asList(toMap("key01", "v01"), toMap("key01", true))));
    }

    private Map<String, Object> toMap(final String key, final Object value) {
      Map<String, Object> map = new HashMap<>();
      map.put(key, value);

      return map;
    }
  }

  @Nested
  class TypeText {

    @Test
    void verifyDeserializer() {
      assertEquals("any-text", JsonUtils.stringToObject("\"any-text\"", Object.class));
    }

    @Test
    void verifyDeserializerCollection() {
      assertEquals(asList("any-text-1"), JsonUtils.stringToObject("[\"any-text-1\"]", Object.class));
      assertEquals(asList("any-text-1", "any-text-2"), JsonUtils.stringToObject("[\"any-text-1\", \"any-text-2\"]", Object.class));
    }

    @Test
    void verifySerializer() {
      assertEquals("\"any-text\"", JsonUtils.objectToString("any-text"));
    }

    @Test
    void verifySerializerCollection() {
      assertEquals("[\"any-text-1\"]", JsonUtils.objectToString(asList("any-text-1")));
      assertEquals("[\"any-text-1\",\"any-text-2\"]", JsonUtils.objectToString(asList("any-text-1", "any-text-2")));
    }
  }
}
