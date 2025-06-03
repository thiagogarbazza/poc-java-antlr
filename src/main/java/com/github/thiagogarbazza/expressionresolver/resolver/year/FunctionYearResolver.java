package com.github.thiagogarbazza.expressionresolver.resolver.year;

import com.github.thiagogarbazza.expressionresolver.util.NumberUtil;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
public class FunctionYearResolver {

  public BigDecimal resolver(final LocalDate value) {
    final int day = value.getYear();

    return NumberUtil.toBigDecimal(day);
  }
}
