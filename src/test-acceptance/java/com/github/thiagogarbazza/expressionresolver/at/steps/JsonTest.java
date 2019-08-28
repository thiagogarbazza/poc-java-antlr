package com.github.thiagogarbazza.expressionresolver.at.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.toBigDecimal;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class JsonTest {

  @Test
  public void verifyBigDecimal() throws IOException {
    ObjectMapper mapper = ObjectMapperBuilder.build();

    assertEquals(toBigDecimal("1"), mapper.readValue("1", Object.class));
    assertEquals(toBigDecimal("1.1"), mapper.readValue("1.1", Object.class));
  }

  @Test
  public void verifyBigDecimals() throws IOException {
    ObjectMapper mapper = ObjectMapperBuilder.build();

    assertEquals(asList(toBigDecimal("1")), mapper.readValue("[1]", Object.class));
    assertEquals(asList(toBigDecimal("1"), toBigDecimal("2"), toBigDecimal("3")), mapper.readValue("[1, 2, 3]", Object.class));
    assertEquals(asList(toBigDecimal("1.1")), mapper.readValue("[1.1]", Object.class));
    assertEquals(asList(toBigDecimal("1.1"), toBigDecimal("2.12"), toBigDecimal("3.123")), mapper.readValue("[1.1, 2.12, 3.123]", Object.class));
  }

  @Test
  public void verifyBoolean() throws IOException {
    ObjectMapper mapper = ObjectMapperBuilder.build();

    assertEquals(TRUE, mapper.readValue("true", Object.class));
    assertEquals(FALSE, mapper.readValue("false", Object.class));
  }

  @Test
  public void verifyBooleans() throws IOException {
    ObjectMapper mapper = ObjectMapperBuilder.build();

    assertEquals(asList(TRUE), mapper.readValue("[true]", Object.class));
    assertEquals(asList(TRUE, TRUE, TRUE), mapper.readValue("[true, true, true]", Object.class));
    assertEquals(asList(FALSE), mapper.readValue("[false]", Object.class));
    assertEquals(asList(FALSE, FALSE, FALSE), mapper.readValue("[false, false, false]", Object.class));
  }

  @Test
  public void verifyLocalDate() throws IOException {
    ObjectMapper mapper = ObjectMapperBuilder.build();

    assertEquals(LocalDate.of(2018, 12, 31), mapper.readValue("\"2018/12/31\"", Object.class));
    assertEquals(LocalDate.of(2019, 1, 1), mapper.readValue("\"2019/01/01\"", Object.class));
  }

  @Test
  public void verifyLocalDates() throws IOException {
    ObjectMapper mapper = ObjectMapperBuilder.build();

    assertEquals(asList(LocalDate.of(2018, 12, 31)), mapper.readValue("[\"2018/12/31\"]", Object.class));
    assertEquals(asList(LocalDate.of(2018, 12, 31), LocalDate.of(2019, 1, 1)), mapper.readValue("[\"2018/12/31\", \"2019/01/01\"]", Object.class));
  }

  @Test
  public void verifyMapEmpty() throws IOException {
    ObjectMapper mapper = ObjectMapperBuilder.build();

    final Map<String, Object> map = new HashMap<>();

    assertEquals(map, mapper.readValue("{}", Map.class));
  }

  @Test
  public void verifyMapString() throws IOException {
    ObjectMapper mapper = ObjectMapperBuilder.build();

    final Map<String, Object> map = new HashMap<>();
    map.put("any-key", "any-value");

    assertEquals(map, mapper.readValue("{\"any-key\": \"any-value\"}", Map.class));
  }

  @Test
  public void verifyMapBigDecimal() throws IOException {
    ObjectMapper mapper = ObjectMapperBuilder.build();

    final Map<String, Object> map = new HashMap<>();
    map.put("any-key", toBigDecimal("5"));

    assertEquals(map, mapper.readValue("{\"any-key\": 5}", Map.class));
  }

  @Test
  public void verifyMapBoolean() throws IOException {
    ObjectMapper mapper = ObjectMapperBuilder.build();

    final Map<String, Object> map = new HashMap<>();
    map.put("any-key", true);

    assertEquals(map, mapper.readValue("{\"any-key\": true}", Map.class));
  }

  @Test
  public void verifyMapLocalDate() throws IOException {
    ObjectMapper mapper = ObjectMapperBuilder.build();

    final Map<String, Object> map = new HashMap<>();
    map.put("any-key", LocalDate.of(2018, 12, 31));

    assertEquals(map, mapper.readValue("{\"any-key\": \"2018/12/31\"}", Map.class));
  }

  @Test
  public void verifyString() throws IOException {
    ObjectMapper mapper = ObjectMapperBuilder.build();

    assertEquals("any-text", mapper.readValue("\"any-text\"", Object.class));
  }

  @Test
  public void verifyStrings() throws IOException {
    ObjectMapper mapper = ObjectMapperBuilder.build();

    assertEquals(asList("any-text-1"), mapper.readValue("[\"any-text-1\"]", Object.class));
    assertEquals(asList("any-text-1", "any-text-2"), mapper.readValue("[\"any-text-1\", \"any-text-2\"]", Object.class));
  }
}
