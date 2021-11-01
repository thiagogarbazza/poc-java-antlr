package com.github.thiagogarbazza.expressionresolver.resolver.comparedate;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.util.NumberUtil.normalizeResultCompare;
import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor = @__(@Inject))
public class FunctionCompareDateResolver {

  public BigDecimal resolver(final LocalDate left, final LocalDate right) {
    final int result = left.compareTo(right);

    return normalizeResultCompare(result);
  }
}
