package com.github.thiagogarbazza.expressionresolver.resolver.core;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.util.Collection;

import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
public class FunctionCollectionAddResolver {

  public Object resolver(final Collection<Object> collection, final Object value) {
    return collection.add(value);
  }
}
