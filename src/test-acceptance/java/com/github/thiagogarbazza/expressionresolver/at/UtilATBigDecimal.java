package com.github.thiagogarbazza.expressionresolver.at;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.TreeSet;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trimToNull;

public class UtilATBigDecimal {

  private static final String COLLECTION_SEPARATOR = "::";

  public static BigDecimal stringToBigDecimal(final String number) {
    return new BigDecimal(trimToNull(number));
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
