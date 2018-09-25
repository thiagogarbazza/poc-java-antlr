package com.github.thiagogarbazza.expressionresolver.runnable;

import org.apache.commons.collections4.Predicate;

import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static org.apache.commons.collections4.IterableUtils.find;

enum Comparison {

  /**
   * Greater than
   */
  GT(">") {
    @Override
    public Boolean compare(final BigDecimal left, final BigDecimal right) {
      return left.compareTo(right) > 0;
    }
  },

  /**
   * Greater than or equal
   */
  GE(">=") {
    @Override
    public Boolean compare(final BigDecimal left, final BigDecimal right) {
      return left.compareTo(right) >= 0;
    }
  },

  /**
   * Less than
   */
  LT("<") {
    @Override
    public Boolean compare(final BigDecimal left, final BigDecimal right) {
      return left.compareTo(right) < 0;
    }
  },

  /**
   * Less than or equal
   */
  LE("<=") {
    @Override
    public Boolean compare(final BigDecimal left, final BigDecimal right) {
      return left.compareTo(right) <= 0;
    }
  },

  /**
   * Equals
   */
  EQ("==") {
    @Override
    public Boolean compare(final BigDecimal left, final BigDecimal right) {
      return left.equals(right);
    }
  },

  /**
   * Not equal
   */
  NE("!=") {
    @Override
    public Boolean compare(final BigDecimal left, final BigDecimal right) {
      return !left.equals(right);
    }
  };

  private final String operator;

  Comparison(final String operator) {
    this.operator = operator;
  }

  public abstract Boolean compare(BigDecimal left, BigDecimal right);

  public static Comparison findByOperator(final String operator) {
    return find(asList(values()), new Predicate<Comparison>() {
      public boolean evaluate(final Comparison comparison) {
        return comparison.operator.equals(operator);
      }
    });
  }
}
