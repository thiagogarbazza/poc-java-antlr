package com.github.thiagogarbazza.expressionresolver.functionresolver.datesfromrange;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.TreeSet;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverDatesFromRange {

  private static final FunctionResolverDatesFromRange INSTANCE = new FunctionResolverDatesFromRange();

  public Collection<LocalDate> resolver(LocalDate left, LocalDate right) {
    Collection<LocalDate> dates = new TreeSet<>();

    LocalDate aux = left;
    while (aux.compareTo(right) <= 0) {
      dates.add(aux);
      aux = aux.plusDays(1);
    }

    return dates;
  }

  public static FunctionResolverDatesFromRange getFunctionResolverDatesFromRange() {
    return INSTANCE;
  }
}
