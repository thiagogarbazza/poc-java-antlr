package com.github.thiagogarbazza.expressionresolver;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ResultTest {

  @Test
  public void verifyAsBoolean() {
    assertEquals(true, new Result(true).asBoolean());
  }

  @Test
  public void verifyAsBooleans() {
    assertEquals(asList(true, false), new Result(asList(true, false)).asBooleans());
  }

  @Test
  public void verifyAsDate() {
    assertEquals(LocalDate.of(2016, 2, 29), new Result(LocalDate.of(2016, 2, 29)).asDate());
  }

  @Test
  public void verifyAsDates() {
    assertEquals(asList(LocalDate.of(2016, 2, 29), LocalDate.of(2000, 1, 1)),
      new Result(asList(LocalDate.of(2016, 2, 29), LocalDate.of(2000, 1, 1))).asDates());
  }

  @Test
  public void verifyAsMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("any-key", "any-balue");

    assertEquals(map, new Result(map).asMap());
  }

  @Test
  public void verifyAsMaps() {
    Map<String, Object> map1 = new HashMap<>();
    map1.put("any-key", "any-balue");

    Map<String, Object> map2 = new HashMap<>();
    map2.put("any-key", "any-balue");

    assertEquals(asList(map1, map2), new Result(asList(map1, map2)).asMaps());
  }

  @Test
  public void verifyAsNumber() {
    assertEquals(new BigDecimal("3.14159265359"), new Result(new BigDecimal("3.14159265359")).asNumber());
  }

  @Test
  public void verifyAsNumbers() {
    assertEquals(asList(new BigDecimal("3.14159265359"), new BigDecimal("1234567890.001")),
      new Result(asList(new BigDecimal("3.14159265359"), new BigDecimal("1234567890.001"))).asNumbers());
  }

  @Test
  public void verifyAsText() {
    assertEquals("any text", new Result("any text").asText());
  }

  @Test
  public void verifyAsTexts() {
    assertEquals(asList("any text", "any other text"), new Result(asList("any text", "any other text")).asTexts());
  }

  @Test
  public void verifyToStandardizedBoolean() {
    Map<String, Object> result = new HashMap<>();
    result.put("value", true);

    assertEquals(asList(result), new Result(true).toStandardized());
  }

  @Test
  public void verifyToStandardizedBooleans() {
    Map<String, Object> result = new HashMap<>();
    result.put("value", true);

    Map<String, Object> result2 = new HashMap<>();
    result2.put("value", false);

    assertEquals(asList(result, result2), new Result(asList(true, false)).toStandardized());
  }

  @Test
  public void verifyToStandardizedDate() {
    Map<String, Object> result = new HashMap<>();
    result.put("value", LocalDate.of(2016, 2, 29));

    assertEquals(asList(result), new Result(LocalDate.of(2016, 2, 29)).toStandardized());
  }

  @Test
  public void verifyToStandardizedDates() {
    Map<String, Object> result = new HashMap<>();
    result.put("value", LocalDate.of(2016, 2, 29));

    Map<String, Object> result2 = new HashMap<>();
    result2.put("value", LocalDate.of(2000, 1, 1));

    assertEquals(asList(result, result2), new Result(asList(LocalDate.of(2016, 2, 29), LocalDate.of(2000, 1, 1))).toStandardized());
  }

  @Test
  public void verifyToStandardizedMap() {
    Map<String, Object> result = new HashMap<>();
    result.put("value", true);

    assertEquals(asList(result), new Result(result).toStandardized());
  }

  @Test
  public void verifyToStandardizedMaps() {
    Map<String, Object> result = new HashMap<>();
    result.put("value", true);

    Map<String, Object> result2 = new HashMap<>();
    result2.put("value", false);

    assertEquals(asList(result, result2), new Result(asList(result, result2)).toStandardized());
  }

  @Test
  public void verifyToStandardizedNumber() {
    Map<String, Object> result = new HashMap<>();
    result.put("value", new BigDecimal("3.14159265359"));

    assertEquals(asList(result), new Result(new BigDecimal("3.14159265359")).toStandardized());
  }

  @Test
  public void verifyToStandardizedNumbers() {
    Map<String, Object> result = new HashMap<>();
    result.put("value", new BigDecimal("3.14159265359"));

    Map<String, Object> result2 = new HashMap<>();
    result2.put("value", new BigDecimal("1234567890.001"));

    assertEquals(asList(result, result2), new Result(asList(new BigDecimal("3.14159265359"), new BigDecimal("1234567890.001"))).toStandardized());
  }

  @Test
  public void verifyToStandardizedText() {
    Map<String, Object> result = new HashMap<>();
    result.put("value", "any text");

    assertEquals(asList(result), new Result("any text").toStandardized());
  }

  @Test
  public void verifyToStandardizedTexts() {
    Map<String, Object> result = new HashMap<>();
    result.put("value", "any text");

    Map<String, Object> result2 = new HashMap<>();
    result2.put("value", "any other text");

    assertEquals(asList(result, result2), new Result(asList("any text", "any other text")).toStandardized());
  }
}
