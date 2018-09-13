package com.github.thiagogarbazza.expressionresolve.runnable;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.ArithmeticExpressionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsFunctionAcosContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsFunctionAsinContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsFunctionAtanContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsFunctionCosContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsFunctionLnContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsFunctionLogContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsFunctionSinContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsFunctionSqrtContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsFunctionTanContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsFunctionsContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsGroupedByContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsNumericContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsOperationAdditionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsOperationDivideContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsOperationModuloContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsOperationMultiplyContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsOperationPowContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsOperationSignContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.MathematicsOperationSubtractContext;

import java.math.BigDecimal;

abstract class MathematicsVisitors extends IdentifierVisitors {

  public MathematicsVisitors(final ExpressionContext expressionContext) {
    super(expressionContext);
  }

  @Override
  public final Object visitMathematicsOperationPow(final MathematicsOperationPowContext ctx) {
    final BigDecimal left = (BigDecimal) visit(ctx.arithmeticExpression(0));
    final BigDecimal right = (BigDecimal) visit(ctx.arithmeticExpression(1));
    return left.pow(right.intValue());
  }

  @Override
  public final Object visitMathematicsOperationSign(final MathematicsOperationSignContext ctx) {
    final BigDecimal result = (BigDecimal) visit(ctx.arithmeticExpression());
    if (ctx.MINUS() != null) {
      return result.multiply(BigDecimal.valueOf(-1));
    }
    return result;
  }

  @Override
  public final Object visitMathematicsOperationDivide(final MathematicsOperationDivideContext ctx) {
    BigDecimal result = (BigDecimal) visit(ctx.arithmeticExpression(0));
    for (int i = 1; i < ctx.arithmeticExpression().size(); i++) {
      final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression(i));
      result = result.divide(child);
    }
    return result;
  }

  @Override
  public final Object visitMathematicsOperationSubtract(final MathematicsOperationSubtractContext ctx) {
    BigDecimal result = (BigDecimal) visit(ctx.arithmeticExpression(0));
    for (int i = 1; i < ctx.arithmeticExpression().size(); i++) {
      final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression(i));
      result = result.subtract(child);
    }
    return result;
  }

  @Override
  public final Object visitMathematicsOperationModulo(final MathematicsOperationModuloContext ctx) {
    final BigDecimal left = (BigDecimal) visit(ctx.arithmeticExpression(0));
    final BigDecimal right = (BigDecimal) visit(ctx.arithmeticExpression(1));
    return left.remainder(right);
  }

  @Override
  public final Object visitMathematicsFunctions(final MathematicsFunctionsContext ctx) {
    final BigDecimal result = (BigDecimal) visit(ctx.functionsExpression());
    return result;
  }

  @Override
  public final Object visitMathematicsOperationAddition(final MathematicsOperationAdditionContext ctx) {
    BigDecimal result = BigDecimal.ZERO;
    for (ArithmeticExpressionContext aectx : ctx.arithmeticExpression()) {
      BigDecimal child = (BigDecimal) visit(aectx);
      result = result.add(child);
    }
    return result;
  }

  @Override
  public final Object visitMathematicsGroupedBy(final MathematicsGroupedByContext ctx) {
    final BigDecimal result = (BigDecimal) visit(ctx.arithmeticExpression());
    return result;
  }

  @Override
  public final Object visitMathematicsNumeric(final MathematicsNumericContext ctx) {
    final BigDecimal result = (BigDecimal) visit(ctx.numberExpresion());
    return result;
  }

  @Override
  public final Object visitMathematicsOperationMultiply(final MathematicsOperationMultiplyContext ctx) {
    BigDecimal result = (BigDecimal) visit(ctx.arithmeticExpression(0));
    for (int i = 1; i < ctx.arithmeticExpression().size(); i++) {
      final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression(i));
      result = result.multiply(child);
    }
    return result;
  }

  @Override
  public final Object visitMathematicsFunctionCos(final MathematicsFunctionCosContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double degrees = child.doubleValue();
    final double cos = Math.cos(degrees);

    final BigDecimal result = BigDecimal.valueOf(cos);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionAcos(final MathematicsFunctionAcosContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double degrees = child.doubleValue();
    final double acos = Math.acos(degrees);

    final BigDecimal result = BigDecimal.valueOf(acos);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionSin(final MathematicsFunctionSinContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double degrees = child.doubleValue();
    final double sin = Math.sin(degrees);

    final BigDecimal result = BigDecimal.valueOf(sin);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionAsin(final MathematicsFunctionAsinContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double degrees = child.doubleValue();
    final double asin = Math.asin(degrees);

    final BigDecimal result = BigDecimal.valueOf(asin);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionTan(final MathematicsFunctionTanContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double degrees = child.doubleValue();
    final double tan = Math.tan(degrees);

    final BigDecimal result = BigDecimal.valueOf(tan);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionAtan(final MathematicsFunctionAtanContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double degrees = child.doubleValue();
    final double atan = Math.atan(degrees);

    final BigDecimal result = BigDecimal.valueOf(atan);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionLn(final MathematicsFunctionLnContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double value = child.doubleValue();
    final double log = Math.log(value);

    final BigDecimal result = BigDecimal.valueOf(log);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionLog(final MathematicsFunctionLogContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double value = child.doubleValue();
    final double log = Math.log10(value);

    final BigDecimal result = BigDecimal.valueOf(log);
    return resultNormatize(result);
  }

  @Override
  public Object visitMathematicsFunctionSqrt(final MathematicsFunctionSqrtContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double value = child.doubleValue();
    final double sqrt = Math.sqrt(value);

    final BigDecimal result = BigDecimal.valueOf(sqrt);
    return resultNormatize(result);
  }
}
