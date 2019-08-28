package com.github.thiagogarbazza.expressionresolver.at.steps;

import com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.TreeSet;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trimToNull;

class UtilATBigDecimal {

  private static final String COLLECTION_SEPARATOR = "::";

  public static BigDecimal stringToBigDecimal(final String number) {
    if (isBlank(number)) {
      return null;
    }

    return NormalizeResult.toBigDecimal(number);
  }

  public static Collection<BigDecimal> stringToBigDecimals(final String numbers) {
    Collection<BigDecimal> cNumbers = new TreeSet<>();

    if (isBlank(numbers)) {
      return cNumbers;
    }

    for (String number : numbers.split(COLLECTION_SEPARATOR)) {
      cNumbers.add(stringToBigDecimal(number));
    }

    return cNumbers;
  }
}
