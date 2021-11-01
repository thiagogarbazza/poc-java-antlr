package com.github.thiagogarbazza.expressionresolver.interpreter.runnable;

import com.github.thiagogarbazza.expressionresolver.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.exception.RunnableExpressionException;
import com.github.thiagogarbazza.expressionresolver.util.json.JsonUtils;
import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static com.github.thiagogarbazza.expressionresolver.AssertionsCustom.assertThrowException;
import static com.github.thiagogarbazza.expressionresolver.util.NumberUtil.toBigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IdentifierAttrServiceTest {

  private ExpressionContext expressionContext;
  private IdentifierAttrService identifierAttrService;
  private Object variable;

  @BeforeEach
  void before() {
    this.variable = JsonUtils.objectToMap(ExampleOfObject.createExampleOfObject());
    this.expressionContext = new ExpressionContext().set("$variable", this.variable);
    this.identifierAttrService = new IdentifierAttrService();
  }

  @Nested
  class MethodGetter {

    @Test
    void verifyValueDataTypeOfBoolean() {
      assertEquals(Boolean.TRUE, identifierAttrService.getter(expressionContext, "$variable.attrBoolean"));
      assertEquals(Boolean.FALSE, identifierAttrService.getter(expressionContext, "$variable.subPath.attrBoolean"));
      assertEquals(Boolean.TRUE, identifierAttrService.getter(expressionContext, "$variable.subPath.subPath.attrBoolean"));
    }

    @Test
    void verifyValueDataTypeOfCalendar() {
      assertEquals(LocalDate.of(1970, 1, 1), identifierAttrService.getter(expressionContext, "$variable.attrCalendar"));
      assertEquals(LocalDate.of(1971, 1, 1), identifierAttrService.getter(expressionContext, "$variable.subPath.attrCalendar"));
      assertEquals(LocalDate.of(1972, 1, 1), identifierAttrService.getter(expressionContext, "$variable.subPath.subPath.attrCalendar"));
    }

    @Test
    void verifyValueDataTypeOfDate() {
      assertEquals(LocalDate.of(1980, 1, 1), identifierAttrService.getter(expressionContext, "$variable.attrDate"));
      assertEquals(LocalDate.of(1981, 1, 1), identifierAttrService.getter(expressionContext, "$variable.subPath.attrDate"));
      assertEquals(LocalDate.of(1982, 1, 1), identifierAttrService.getter(expressionContext, "$variable.subPath.subPath.attrDate"));
    }

    @Test
    void verifyValueDataTypeOfLocalDate() {
      assertEquals(LocalDate.of(2020, 1, 1), identifierAttrService.getter(expressionContext, "$variable.attrLocalDate"));
      assertEquals(LocalDate.of(2021, 1, 1), identifierAttrService.getter(expressionContext, "$variable.subPath.attrLocalDate"));
      assertEquals(LocalDate.of(2022, 1, 1), identifierAttrService.getter(expressionContext, "$variable.subPath.subPath.attrLocalDate"));
    }

    @Test
    void verifyValueDataTypeOfNumberBigDecimal() {
      assertEquals(toBigDecimal("21"), identifierAttrService.getter(expressionContext, "$variable.attrNumberBigDecimal"));
      assertEquals(toBigDecimal("22"), identifierAttrService.getter(expressionContext, "$variable.subPath.attrNumberBigDecimal"));
      assertEquals(toBigDecimal("23"), identifierAttrService.getter(expressionContext, "$variable.subPath.subPath.attrNumberBigDecimal"));
    }

    @Test
    void verifyValueDataTypeOfNumberInteger() {
      assertEquals(toBigDecimal("1"), identifierAttrService.getter(expressionContext, "$variable.attrNumberInteger"));
      assertEquals(toBigDecimal("2"), identifierAttrService.getter(expressionContext, "$variable.subPath.attrNumberInteger"));
      assertEquals(toBigDecimal("3"), identifierAttrService.getter(expressionContext, "$variable.subPath.subPath.attrNumberInteger"));
    }

    @Test
    void verifyValueDataTypeOfNumberLong() {
      assertEquals(toBigDecimal("11"), identifierAttrService.getter(expressionContext, "$variable.attrNumberLong"));
      assertEquals(toBigDecimal("12"), identifierAttrService.getter(expressionContext, "$variable.subPath.attrNumberLong"));
      assertEquals(toBigDecimal("13"), identifierAttrService.getter(expressionContext, "$variable.subPath.subPath.attrNumberLong"));
    }

    @Test
    void verifyValueDataTypeOfObject() {
      assertNotNull(identifierAttrService.getter(expressionContext, "$variable.subPath"));
    }

    @Test
    void verifyValueDataTypeOfText() {
      assertEquals("any text", identifierAttrService.getter(expressionContext, "$variable.attrText"));
      assertEquals("subPath - any text", identifierAttrService.getter(expressionContext, "$variable.subPath.attrText"));
      assertEquals("subPath.subPath - any text", identifierAttrService.getter(expressionContext, "$variable.subPath.subPath.attrText"));
    }

    @Test
    void verifyVariableNotPresentInContext() {
      final Exception exception = assertThrows(RunnableExpressionException.class,
        () -> identifierAttrService.getter(expressionContext, "$variableNotPresent.subPath"));
      assertThrowException("The variable $variableNotPresent is not present in the execution context of the expression.", exception);
    }

    @Test
    void verifyVariablePresentInContextButAttrNotADataTypeOfObject() {
      expressionContext.set("$var", "any text value");

      final Exception exception = assertThrows(RunnableExpressionException.class,
        () -> identifierAttrService.getter(expressionContext, "$var.attr"));
      assertThrowException("The variable $var is not a data type of object.", exception);
    }

    @Test
    void verifyVariablePresentInContextButAttrNotADataTypeOfObjectInSubPath() {
      expressionContext.set("$var", ImmutableMap.builder()
        .put("subPath", ImmutableMap.builder().put("attr", 1).build())
        .put("attr", "any text")
        .build());

      final Exception exception = assertThrows(RunnableExpressionException.class,
        () -> identifierAttrService.getter(expressionContext, "$var.attr.subattr"));
      assertThrowException("The variable $var.attr.subattr is not a data type of object.", exception);
    }

    @Test
    void verifyVariablePresentInContextButAttrNotExists() {
      expressionContext.set("$variable", ImmutableMap.builder().put("attr", "Any text").build());

      final Exception exception = assertThrows(RunnableExpressionException.class,
        () -> identifierAttrService.getter(expressionContext, "$variable.anyAttr"));
      assertThrowException("The variable $variable.anyAttr is not present in the execution context of the expression.", exception);
    }

    @Test
    void verifyVariablePresentInContextButAttrNotExistsInSubPath() {
      expressionContext.set("$variable", ImmutableMap.builder().put("subPath", ImmutableMap.builder().put("attr", 1).build()).build());
      final Exception exception = assertThrows(RunnableExpressionException.class,
        () -> identifierAttrService.getter(expressionContext, "$variable.subPath.attrNotPresent"));
      assertThrowException("The variable $variable.subPath.attrNotPresent is not present in the execution context of the expression.", exception);
    }

    @Test
    void verifyVariablePresentInContextButIsNull() {
      expressionContext.set("$variable", new HashMap() {{put("attr", null);}});
      assertNull(identifierAttrService.getter(expressionContext, "$variable.attr"));
    }
  }

  @Nested
  class MethodSetter {

    @Test
    void verifySetterInVariable01Subpath() {
      identifierAttrService.setter(expressionContext, "$newVariable.attr", "any text");

      assertEquals("any text", identifierAttrService.getter(expressionContext, "$newVariable.attr"));
    }

    @Test
    void verifySetterInVariable02Subpath() {
      identifierAttrService.setter(expressionContext, "$newVariable.sub1.attr", "any text");

      assertEquals("any text", identifierAttrService.getter(expressionContext, "$newVariable.sub1.attr"));
    }

    @Test
    void verifySetterInVariable03Subpath() {
      identifierAttrService.setter(expressionContext, "$newVariable.sub1.sub2.attr", "any text");

      assertEquals("any text", identifierAttrService.getter(expressionContext, "$newVariable.sub1.sub2.attr"));
    }

    @Test
    void verifySetterInVariableExists() {
      identifierAttrService.setter(expressionContext, "$var.attr", "any text");
      identifierAttrService.setter(expressionContext, "$var.sub1.attr", "subPath - any text");
      identifierAttrService.setter(expressionContext, "$var.sub1.sub2.attr", "subPath.subPath - any text");

      assertEquals("any text", identifierAttrService.getter(expressionContext, "$var.attr"));
      assertEquals("subPath - any text", identifierAttrService.getter(expressionContext, "$var.sub1.attr"));
      assertEquals("subPath.subPath - any text", identifierAttrService.getter(expressionContext, "$var.sub1.sub2.attr"));
    }

    @Test
    void verifySetterInVariableNotADataTypeOfObject() {
      expressionContext.set("$variable", toBigDecimal("123456789"));
      identifierAttrService.setter(expressionContext, "$variable.attr", "any text");

      assertEquals("any text", identifierAttrService.getter(expressionContext, "$variable.attr"));
    }

    @Test
    void verifySetterInVariableNotADataTypeOfObjectManySubPath() {
      identifierAttrService.setter(expressionContext, "$v.s1", "any text to v.s1");
      identifierAttrService.setter(expressionContext, "$v.s1.s2", "any text to v.s1.s2");
      identifierAttrService.setter(expressionContext, "$v.s1.s2.s3", "any text to v.s1.s2.s3");
      identifierAttrService.setter(expressionContext, "$v.s1.s2.s3.s4", "any text to v.s1.s2.s3.s4");
      identifierAttrService.setter(expressionContext, "$v.s1.s2.s3.s4.s5.", "any text to v.s1.s2.s3.s4.s5.");
      identifierAttrService.setter(expressionContext, "$v.s1.s2.s3.s4.s5.s6", "any text to v.s1.s2.s3.s4.s5.s6");
      identifierAttrService.setter(expressionContext, "$v.s1.s2.s3.s4.s5.s6.s7", "any text to v.s1.s2.s3.s4.s5.s6.s7");
      identifierAttrService.setter(expressionContext, "$v.s1.s2.s3.s4.s5.s6.s7.s8", "any text to v.s1.s2.s3.s4.s5.s6.s7.s8");
      identifierAttrService.setter(expressionContext, "$v.s1.s2.s3.s4.s5.s6.s7.s8.s9", "any text to v.s1.s2.s3.s4.s5.s6.s7.s8.s9");

      assertEquals("any text to v.s1.s2.s3.s4.s5.s6.s7.s8.s9", identifierAttrService.getter(expressionContext, "$v.s1.s2.s3.s4.s5.s6.s7.s8.s9"));
    }

    @Test
    void verifySetterInVariableXXSubpath() {
      identifierAttrService.setter(expressionContext, "$v.s1.s2.s3.s4.s5.s6.s7.s8.s9", "any text");

      assertEquals("any text", identifierAttrService.getter(expressionContext, "$v.s1.s2.s3.s4.s5.s6.s7.s8.s9"));
    }
  }

  @Getter
  @Setter
  @Builder
  @EqualsAndHashCode
  @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ExampleOfObject {

    private final Boolean attrBoolean;
    private final Calendar attrCalendar;
    private final Date attrDate;
    private final LocalDate attrLocalDate;
    private final BigDecimal attrNumberBigDecimal;
    private final Integer attrNumberInteger;
    private final Long attrNumberLong;
    private final String attrText;
    private final ExampleOfObject subPath;

    static ExampleOfObject createExampleOfObject() {
      return ExampleOfObject.builder()
        .attrBoolean(Boolean.TRUE)
        .attrCalendar(new GregorianCalendar(1970, Calendar.JANUARY, 1))
        .attrDate(Date.from(LocalDate.of(1980, 1, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
        .attrLocalDate(LocalDate.of(2020, 1, 1))
        .attrNumberBigDecimal(new BigDecimal("21"))
        .attrNumberInteger(1)
        .attrNumberLong(11L)
        .attrText("any text")
        .subPath(ExampleOfObject.builder()
          .attrBoolean(Boolean.FALSE)
          .attrCalendar(new GregorianCalendar(1971, Calendar.JANUARY, 1))
          .attrDate(Date.from(LocalDate.of(1981, 1, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
          .attrLocalDate(LocalDate.of(2021, 1, 1))
          .attrNumberBigDecimal(new BigDecimal("22"))
          .attrNumberInteger(2)
          .attrNumberLong(12L)
          .attrText("subPath - any text")
          .subPath(ExampleOfObject.builder()
            .attrBoolean(Boolean.TRUE)
            .attrCalendar(new GregorianCalendar(1972, Calendar.JANUARY, 1))
            .attrDate(Date.from(LocalDate.of(1982, 1, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
            .attrLocalDate(LocalDate.of(2022, 1, 1))
            .attrNumberBigDecimal(new BigDecimal("23"))
            .attrNumberInteger(3)
            .attrNumberLong(13L)
            .attrText("subPath.subPath - any text")
            .subPath(null)
            .build())
          .build())
        .build();
    }
  }
}