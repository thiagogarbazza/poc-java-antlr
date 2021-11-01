package com.github.thiagogarbazza.expressionresolver.resolver.primitive;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.BooleanUtils.toBoolean;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor = @__(@Inject))
public class PrimitiveBooleanResolver {

  public Boolean resolver(final String value) {
    return toBoolean(value);
  }
}
