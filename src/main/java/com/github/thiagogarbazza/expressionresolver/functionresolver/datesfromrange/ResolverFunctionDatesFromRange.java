package com.github.thiagogarbazza.expressionresolver.functionresolver.datesfromrange;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.TreeSet;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionDatesFromRange {

  private static final ResolverFunctionDatesFromRange INSTANCE = new ResolverFunctionDatesFromRange();

  public Collection<LocalDate> resolver(LocalDate left, LocalDate right) {
    Collection<LocalDate> dates = new TreeSet<>();

    LocalDate aux = left;
    while (aux.compareTo(right) <= 0) {
      dates.add(aux);
      aux = aux.plusDays(1);
    }

    return dates;
  }

  public static ResolverFunctionDatesFromRange getResolverFunctionDatesFromRange() {
    return INSTANCE;
  }
}
