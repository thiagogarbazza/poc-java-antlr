package com.github.thiagogarbazza.expressionresolver.util;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataTypeUtilTest {

  @Nested
  class MethodIsSameType {

    @Test
    void verifyIsNotSame() {
      assertFalse(DataTypeUtil.isSameType(5, 5, String.class));
      assertFalse(DataTypeUtil.isSameType("text", 5, String.class));
      assertFalse(DataTypeUtil.isSameType(5, "text", String.class));
    }

    @Test
    void verifyIsSame() {

      assertTrue(DataTypeUtil.isSameType("text", "text", String.class));
      assertTrue(DataTypeUtil.isSameType("text", null, String.class));
      assertTrue(DataTypeUtil.isSameType(null, "text", String.class));
      assertTrue(DataTypeUtil.isSameType(null, null, String.class));
    }
  }
}