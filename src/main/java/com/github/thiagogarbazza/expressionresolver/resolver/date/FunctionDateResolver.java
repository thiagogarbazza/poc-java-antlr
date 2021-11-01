package com.github.thiagogarbazza.expressionresolver.resolver.date;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor = @__(@Inject))
public class FunctionDateResolver {

  public LocalDate resolver(final BigDecimal year, final BigDecimal month, final BigDecimal day) {
    return LocalDate.of(year.intValue(), month.intValue(), day.intValue());
  }
}

