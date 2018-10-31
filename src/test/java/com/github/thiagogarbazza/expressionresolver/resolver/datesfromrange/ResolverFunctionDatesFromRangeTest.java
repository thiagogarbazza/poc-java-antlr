package com.github.thiagogarbazza.expressionresolver.resolver.datesfromrange;

import org.apache.commons.collections4.IterableUtils;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collection;

import static com.github.thiagogarbazza.expressionresolver.resolver.datesfromrange.ResolverFunctionDatesFromRange.getResolverFunctionDatesFromRange;
import static org.junit.Assert.assertEquals;

public class ResolverFunctionDatesFromRangeTest {

  private ResolverFunctionDatesFromRange resolverFunctionDatesFromRange;

  @Before
  public void before() {
    resolverFunctionDatesFromRange = getResolverFunctionDatesFromRange();
  }

  @Test
  public void verifyResolverOneDayMore() {
    LocalDate left = LocalDate.of(2000, 1, 1);
    LocalDate right = LocalDate.of(2000, 1, 2);

    final Collection<LocalDate> dates = resolverFunctionDatesFromRange.resolver(left, right);

    assertEquals(2, dates.size());
    assertEquals(left, IterableUtils.get(dates, 0));
    assertEquals(right, IterableUtils.get(dates, 1));
  }

  @Test
  public void verifyResolverSameDate() {
    LocalDate left = LocalDate.of(2000, 1, 1);
    LocalDate right = LocalDate.of(2000, 1, 1);

    final Collection<LocalDate> dates = resolverFunctionDatesFromRange.resolver(left, right);

    assertEquals(1, dates.size());
    assertEquals(left, IterableUtils.get(dates, 0));
  }

  @Test
  public void verifyResolverTwoDayMore() {
    LocalDate left = LocalDate.of(2000, 1, 1);
    LocalDate middle = LocalDate.of(2000, 1, 2);
    LocalDate right = LocalDate.of(2000, 1, 3);

    final Collection<LocalDate> dates = resolverFunctionDatesFromRange.resolver(left, right);

    assertEquals(3, dates.size());
    assertEquals(left, IterableUtils.get(dates, 0));
    assertEquals(middle, IterableUtils.get(dates, 1));
    assertEquals(right, IterableUtils.get(dates, 2));
  }
}
