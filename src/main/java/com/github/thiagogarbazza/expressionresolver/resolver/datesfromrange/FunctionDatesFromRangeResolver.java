package com.github.thiagogarbazza.expressionresolver.resolver.datesfromrange;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
public class FunctionDatesFromRangeResolver {

  public Collection<LocalDate> resolver(final LocalDate left, final LocalDate right) {
    Collection<LocalDate> dates = new ArrayList<>();

    LocalDate aux = left;
    while (aux.compareTo(right) <= 0) {
      dates.add(aux);
      aux = aux.plusDays(1);
    }

    return dates;
  }
}
