package com.github.thiagogarbazza.expressionresolver.interpreter.runnable;

import com.github.thiagogarbazza.expressionresolver.exception.RunnableExpressionException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static com.github.thiagogarbazza.expressionresolver.util.DataTypeUtil.isSameType;
import static com.github.thiagogarbazza.expressionresolver.util.PropertieUtil.messageProperty;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
enum Comparation {

  /**
   * Greater than
   */
  GT(">") {
    @Override
    public Boolean compare(final Object left, final Object right) {return toCompare(left, right) > 0;}
  },

  /**
   * Greater than or equal
   */
  GE(">=") {
    @Override
    public Boolean compare(final Object left, final Object right) {
      return toCompare(left, right) >= 0;
    }
  },

  /**
   * Less than
   */
  LT("<") {
    @Override
    public Boolean compare(final Object left, final Object right) {
      return toCompare(left, right) < 0;
    }
  },

  /**
   * Less than or equal
   */
  LE("<=") {
    @Override
    public Boolean compare(final Object left, final Object right) {
      return toCompare(left, right) <= 0;
    }
  },

  /**
   * Equals
   */
  EQ("==") {
    @Override
    public Boolean compare(final Object left, final Object right) {
      return toCompare(left, right) == 0;
    }
  },

  /**
   * Not equal
   */
  NE("!=") {
    @Override
    public Boolean compare(final Object left, final Object right) {
      return toCompare(left, right) != 0;
    }
  };

  private final String id;

  public abstract Boolean compare(Object left, Object right);

  protected final int toCompare(final Object left, final Object right) {
    if (isSameType(left, right, BigDecimal.class)
      || isSameType(left, right, String.class)
      || isSameType(left, right, LocalDate.class)) {
      return new CompareToBuilder().append(left, right).build();
    }

    throw new RunnableExpressionException(messageProperty("validation.comparation.not-allowed-diff-types", left, this.id, right));
  }

  public static Comparation valueOfId(final String operator) {
    return Stream.of(values())
      .filter(c -> c.id.equals(operator))
      .findFirst()
      .orElse(null);
  }
}
