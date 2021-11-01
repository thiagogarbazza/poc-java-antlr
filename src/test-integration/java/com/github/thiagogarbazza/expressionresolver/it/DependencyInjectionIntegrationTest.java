package com.github.thiagogarbazza.expressionresolver.it;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.DependencyInjection;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DependencyInjectionIntegrationTest {

  @Nested
  class GetBean {

    @Test
    void verify() {
      final ASingletonService bean = DependencyInjection.getDependencyInjection().getBean(ASingletonService.class);
      assertNotNull(bean);

      final ASingletonService bean2 = DependencyInjection.getDependencyInjection().getBean(ASingletonService.class);
      assertNotNull(bean2);
      assertEquals(bean, bean2);
    }

    @Test
    void verifyBeanControllByDI() {
      final Exception exception = Assertions.assertThrows(Exception.class, () -> DependencyInjection.getDependencyInjection().getBean(
        BeanNotAnnotedForDI.class));
      assertEquals("Bean \"com.github.thiagogarbazza.expressionresolver.it.DependencyInjectionIntegrationTest.BeanNotAnnotedForDI\" is not annotated for DI.",
        exception.getMessage());
    }

    @Test
    void verifyBeanWithConstructorNotAnnotedForDI() {
      final Exception exception = Assertions.assertThrows(Exception.class, () -> DependencyInjection.getDependencyInjection().getBean(
        BeanWithConstructorNotAnnotedForDI.class));
      assertEquals(
        "Bean \"com.github.thiagogarbazza.expressionresolver.it.DependencyInjectionIntegrationTest.BeanWithConstructorNotAnnotedForDI\" there is no annotated"
          + " constructor to be used for DI.",
        exception.getMessage());
    }

    @Test
    void verifyWithDependency() {
      final ASingletonWithDependencyService bean = DependencyInjection.getDependencyInjection().getBean(ASingletonWithDependencyService.class);
      assertNotNull(bean);

      final ASingletonWithDependencyService bean2 = DependencyInjection.getDependencyInjection().getBean(ASingletonWithDependencyService.class);
      assertNotNull(bean2);
      assertEquals(bean, bean2);

      assertEquals(bean.getaSingletonService(), bean2.getaSingletonService());
      assertEquals(bean.getaSingletonService(), DependencyInjection.getDependencyInjection().getBean(ASingletonService.class));
    }
  }

  @Nested
  class GetBeanRequest {

    @Test
    void verify() {
      final ASingletonService bean = DependencyInjection.getDependencyInjection().getBean(ASingletonService.class);
      final ASingletonService bean2 = DependencyInjection.getDependencyInjection().getBean(ASingletonService.class);
      assertEquals(bean, bean2);

      assertNotEquals(bean, DependencyInjection.getDependencyInjection().getBeanRequest(ASingletonService.class));

      assertNotEquals(
        DependencyInjection.getDependencyInjection().getBeanRequest(ASingletonService.class),
        DependencyInjection.getDependencyInjection().getBeanRequest(ASingletonService.class));
    }
  }

  @Nested
  class RegisterBean {

    @Test
    void verifyRegisterNewInstance() {
      final ASingletonService bean = DependencyInjection.getDependencyInjection().getBean(ASingletonService.class);
      assertNotNull(bean);

      DependencyInjection.getDependencyInjection().registerBean(ASingletonService.class, new ASingletonService());

      final ASingletonService bean2 = DependencyInjection.getDependencyInjection().getBean(ASingletonService.class);
      assertNotNull(bean2);
      assertNotEquals(bean, bean2);
    }
  }

  @Service
  static class ASingletonService {

    @Inject
    public ASingletonService() {}
  }

  @Service
  static class ASingletonWithDependencyService {

    private final ASingletonService aSingletonService;

    @Inject
    private ASingletonWithDependencyService(final ASingletonService aSingletonService) {
      this.aSingletonService = aSingletonService;
    }

    protected ASingletonService getaSingletonService() {
      return aSingletonService;
    }
  }

  static class BeanNotAnnotedForDI {

    public BeanNotAnnotedForDI() {}
  }

  @Service
  static class BeanWithConstructorNotAnnotedForDI {

    public BeanWithConstructorNotAnnotedForDI() {}
  }
}