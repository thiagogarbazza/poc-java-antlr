package com.github.thiagogarbazza.expressionresolver.resolver;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@UtilityClass
public class NormalizeResult {

  private static final int TAMANHO_CASAS_DECIMAIS = 34;
  private static final RoundingMode TIPO_ARREDONDAMENTO = RoundingMode.HALF_UP;
  public static final MathContext PRECISAO_E_ARREDONDAMENTO = new MathContext(TAMANHO_CASAS_DECIMAIS, TIPO_ARREDONDAMENTO);

  public static BigDecimal setScale(final BigDecimal valor, int casasDecimais) {
    return valor.setScale(casasDecimais, TIPO_ARREDONDAMENTO);
  }

  public static BigDecimal normalizeResult(final BigDecimal value) {
    return setScale(value, TAMANHO_CASAS_DECIMAIS);
  }

  public static BigDecimal toBigDecimal(final String valor) {
    return normalizeResult(new BigDecimal(valor));
  }

  public static BigDecimal normalizeResult(final int value) {
    final BigDecimal result = BigDecimal.valueOf(value);

    return normalizeResult(result);
  }

  public static BigDecimal normalizeResult(final double value) {
    final BigDecimal result = BigDecimal.valueOf(value);

    return normalizeResult(result);
  }

  public static BigDecimal normalizeResultCompare(final int value) {
    if (value == 0) {
      return normalizeResult(0);
    }

    return value < 0
      ? normalizeResult(-1)
      : normalizeResult(1);
  }
}

