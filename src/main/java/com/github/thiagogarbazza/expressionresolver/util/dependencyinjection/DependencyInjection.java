package com.github.thiagogarbazza.expressionresolver.util.dependencyinjection;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.BeanUtils.checkBeanAnnotated;
import static com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.BeanUtils.newInstance;
import static java.util.stream.Collectors.toMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DependencyInjection {

  private static DependencyInjection instance;
  private final Map<Class<?>, Object> instances = new HashMap<>();

  public <T> T getBean(final Class<T> bean) {
    if (instances.containsKey(bean)) {
      return (T) instances.get(bean);
    }

    final T instance = createBean(bean);
    registerBean(bean, instance);
    return instance;
  }

  public <T> T getBeanRequest(final Class<T> bean, final Object... beansOverride) {
    final Map<Class<?>, Object> beansOverrideMap = Stream.of(beansOverride).collect(toMap(Object::getClass, x -> x));
    final Constructor<T> constructor = BeanUtils.getConstructor(bean);
    final Object[] parameters = Stream.of(constructor.getParameterTypes())
      .map(parameter -> beansOverrideMap.containsKey(parameter) ? beansOverrideMap.get(parameter) : getBean(parameter))
      .toArray();

    return newInstance(constructor, parameters);
  }

  public <T> void registerBean(final Class<T> bean, final T instance) {
    instances.put(bean, instance);
  }

  private <T> T createBean(final Class<T> bean) {
    checkBeanAnnotated(bean);

    final Constructor<T> constructor = BeanUtils.getConstructor(bean);
    final Object[] parameters = Stream.of(constructor.getParameterTypes()).map(this::getBean).toArray();

    return newInstance(constructor, parameters);
  }

  public static DependencyInjection getDependencyInjection() {
    if (instance == null) {
      synchronized (DependencyInjection.class) {
        if (instance == null) {
          instance = new DependencyInjection();
        }
      }
    }

    return instance;
  }
}
