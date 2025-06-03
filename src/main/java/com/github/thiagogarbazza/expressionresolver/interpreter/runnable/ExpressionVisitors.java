package com.github.thiagogarbazza.expressionresolver.interpreter.runnable;

import com.github.thiagogarbazza.expressionresolver.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParserBaseVisitor;
import com.github.thiagogarbazza.expressionresolver.resolver.acos.FunctionAcosResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.asin.FunctionAsinResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.atan.FunctionAtanResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.comparedate.FunctionCompareDateResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.comparenumber.FunctionCompareNumberResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.comparestring.FunctionCompareStringResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.core.FunctionCollectionAddResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.cos.FunctionCosResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.date.FunctionDateResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.datesfromrange.FunctionDatesFromRangeResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.day.FunctionDayResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.ln.FunctionLnResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.log.FunctionLogResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.math.NumberOperationDivisionResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.math.NumberOperationMultiplyResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.math.NumberOperationPowResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.math.NumberOperationSubtractionResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.month.FunctionMonthResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.primitive.PrimitiveBooleanResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.primitive.PrimitiveDateResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.primitive.PrimitiveNumberResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.primitive.PrimitiveTextResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.sin.FunctionSinResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.sqrt.FunctionSqrtResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.tan.FunctionTanResolver;
import com.github.thiagogarbazza.expressionresolver.resolver.year.FunctionYearResolver;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.BooleanUtils.negate;

@RequiredArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
class ExpressionVisitors extends ExpressionParserBaseVisitor<Object> {

  private final ExpressionContext expressionContext;
  private final FunctionAcosResolver functionAcosResolver;
  private final FunctionAsinResolver functionAsinResolver;
  private final FunctionAtanResolver functionAtanResolver;
  private final FunctionCollectionAddResolver functionCollectionAddResolver;
  private final FunctionCompareDateResolver functionCompareDateResolver;
  private final FunctionCompareNumberResolver functionCompareNumberResolver;
  private final FunctionCompareStringResolver functionCompareStringResolver;
  private final FunctionCosResolver functionCosResolver;
  private final FunctionDateResolver functionDateResolver;
  private final FunctionDatesFromRangeResolver functionDatesFromRangeResolver;
  private final FunctionDayResolver functionDayResolver;
  private final FunctionLnResolver functionLnResolver;
  private final FunctionLogResolver functionLogResolver;
  private final FunctionMonthResolver functionMonthResolver;
  private final FunctionSinResolver functionSinResolver;
  private final FunctionSqrtResolver functionSqrtResolver;
  private final FunctionTanResolver functionTanResolver;
  private final FunctionYearResolver functionYearResolver;
  private final IdentifierAttrService identifierAttrService;
  private final NumberOperationDivisionResolver numberOperationDivisionResolver;
  private final NumberOperationMultiplyResolver numberOperationMultiplyResolver;
  private final NumberOperationPowResolver numberOperationPowResolver;
  private final NumberOperationSubtractionResolver numberOperationSubtractionResolver;
  private final PrimitiveBooleanResolver primitiveBooleanResolver;
  private final PrimitiveDateResolver primitiveDateResolver;
  private final PrimitiveNumberResolver primitiveNumberResolver;
  private final PrimitiveTextResolver primitiveTextResolver;
  @Getter
  private Object valueToBeReturned;

  @Override
  public final Object visitParse(final ExpressionParser.ParseContext ctx) {
    return visit(ctx.block());
  }

  @Override
  public final Object visitAssignment(final ExpressionParser.AssignmentContext ctx) {
    final String variable = ctx.IDENTIFIER().getText();
    final Object value = visit(ctx.value());

    expressionContext.set(variable, value);

    return value;
  }

  @Override
  public final Object visitAssignmentAttr(final ExpressionParser.AssignmentAttrContext ctx) {
    final String identifier = ctx.IDENTIFIER_ATTR().getText();
    final Object value = visit(ctx.value());

    this.identifierAttrService.setter(this.expressionContext, identifier, value);

    return value;
  }

  @Override
  public final Object visitIfConditional(final ExpressionParser.IfConditionalContext ctx) {
    int i = -1;
    ExpressionParser.VlTpBooleanContext VlTpBooleanCtx;

    do {
      i++;
      VlTpBooleanCtx = ctx.vlTpBoolean(i);
    } while (VlTpBooleanCtx != null && !(Boolean) visit(VlTpBooleanCtx));

    final ExpressionParser.StatementBlockContext statementBlock = ctx.statementBlock(i);
    if (statementBlock != null) {
      return visit(statementBlock);
    }

    return null;
  }

  @Override
  public final Object visitIterableExpression(final ExpressionParser.IterableExpressionContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();
    Iterator iterator = ((Iterable) visit(ctx.clTpAny())).iterator();

    while (iterator.hasNext()) {
      expressionContext.set(identifier, iterator.next());
      visit(ctx.statementBlock());
    }

    expressionContext.set(identifier, null);

    return null;
  }

  @Override
  public final Object visitReturnExpression(final ExpressionParser.ReturnExpressionContext ctx) {
    if (this.valueToBeReturned == null) {
      this.valueToBeReturned = visit(ctx.value());
    }

    return this.valueToBeReturned;
  }

  @Override
  public final Object visitCreateClTpBoolean(final ExpressionParser.CreateClTpBooleanContext ctx) {
    return ctx.vlTpBoolean().stream()
      .map(this::visit)
      .collect(toList());
  }

  @Override
  public final Object visitCreateEmptyClTpBoolean(final ExpressionParser.CreateEmptyClTpBooleanContext ctx) {
    return new ArrayList<>();
  }

  @Override
  public final Object visitCreateNullClTpBoolean(final ExpressionParser.CreateNullClTpBooleanContext ctx) {
    return null;
  }

  @Override
  public final Object visitIdentifierClTpBoolean(final ExpressionParser.IdentifierClTpBooleanContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return expressionContext.get(identifier);
  }

  @Override
  public final Object visitIdentifierAttrClTpBoolean(final ExpressionParser.IdentifierAttrClTpBooleanContext ctx) {
    final String identifier = ctx.IDENTIFIER_ATTR().getText();

    return this.identifierAttrService.getter(this.expressionContext, identifier);
  }

  @Override
  public final Object visitCreateClTpDate(final ExpressionParser.CreateClTpDateContext ctx) {
    return ctx.vlTpDate().stream()
      .map(this::visit)
      .collect(toList());
  }

  @Override
  public final Object visitCreateEmptyClTpDate(final ExpressionParser.CreateEmptyClTpDateContext ctx) {
    return new ArrayList<>();
  }

  @Override
  public final Object visitCreateNullClTpDate(final ExpressionParser.CreateNullClTpDateContext ctx) {
    return null;
  }

  @Override
  public final Object visitIdentifierClTpDate(final ExpressionParser.IdentifierClTpDateContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return expressionContext.get(identifier);
  }

  @Override
  public final Object visitIdentifierAttrClTpDate(final ExpressionParser.IdentifierAttrClTpDateContext ctx) {
    final String identifier = ctx.IDENTIFIER_ATTR().getText();

    return this.identifierAttrService.getter(this.expressionContext, identifier);
  }

  @Override
  public final Object visitCreateClTpNumber(final ExpressionParser.CreateClTpNumberContext ctx) {
    return ctx.vlTpNumber().stream()
      .map(this::visit)
      .collect(toList());
  }

  @Override
  public final Object visitCreateEmptyClTpNumber(final ExpressionParser.CreateEmptyClTpNumberContext ctx) {
    return new ArrayList<>();
  }

  @Override
  public final Object visitCreateNullClTpNumber(final ExpressionParser.CreateNullClTpNumberContext ctx) {
    return null;
  }

  @Override
  public final Object visitIdentifierClTpNumber(final ExpressionParser.IdentifierClTpNumberContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return expressionContext.get(identifier);
  }

  @Override
  public final Object visitIdentifierAttrClTpNumber(final ExpressionParser.IdentifierAttrClTpNumberContext ctx) {
    final String identifier = ctx.IDENTIFIER_ATTR().getText();

    return this.identifierAttrService.getter(this.expressionContext, identifier);
  }

  @Override
  public final Object visitCreateClTpObject(final ExpressionParser.CreateClTpObjectContext ctx) {
    return ctx.vlTpObject().stream()
      .map(this::visit)
      .collect(toList());
  }

  @Override
  public final Object visitCreateEmptyClTpObject(final ExpressionParser.CreateEmptyClTpObjectContext ctx) {
    return new ArrayList<>();
  }

  @Override
  public final Object visitCreateNullClTpObject(final ExpressionParser.CreateNullClTpObjectContext ctx) {
    return null;
  }

  @Override
  public final Object visitIdentifierClTpObject(final ExpressionParser.IdentifierClTpObjectContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return expressionContext.get(identifier);
  }

  @Override
  public final Object visitIdentifierAttrClTpObject(final ExpressionParser.IdentifierAttrClTpObjectContext ctx) {
    final String identifier = ctx.IDENTIFIER_ATTR().getText();

    return this.identifierAttrService.getter(this.expressionContext, identifier);
  }

  @Override
  public final Object visitCreateClTpText(final ExpressionParser.CreateClTpTextContext ctx) {
    return ctx.vlTpText().stream()
      .map(this::visit)
      .collect(toList());
  }

  @Override
  public final Object visitCreateEmptyClTpText(final ExpressionParser.CreateEmptyClTpTextContext ctx) {
    return new ArrayList<>();
  }

  @Override
  public final Object visitCreateNullClTpText(final ExpressionParser.CreateNullClTpTextContext ctx) {
    return null;
  }

  @Override
  public final Object visitIdentifierClTpText(final ExpressionParser.IdentifierClTpTextContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return expressionContext.get(identifier);
  }

  @Override
  public final Object visitIdentifierAttrClTpText(final ExpressionParser.IdentifierAttrClTpTextContext ctx) {
    final String identifier = ctx.IDENTIFIER_ATTR().getText();

    return this.identifierAttrService.getter(this.expressionContext, identifier);
  }

  @Override
  public final Object visitBooleanComparisonVlTpNumber(final ExpressionParser.BooleanComparisonVlTpNumberContext ctx) {
    final Object left = visit(ctx.vlTpNumber(0));
    final String operator = ctx.op.getText();
    final Object right = visit(ctx.vlTpNumber(1));

    return Comparation.valueOfId(operator).compare(left, right);
  }

  @Override
  public final Object visitBooleanGroupedBy(final ExpressionParser.BooleanGroupedByContext ctx) {
    return visit(ctx.vlTpBoolean());
  }

  @Override
  public final Object visitBooleanComparisonVlTpText(final ExpressionParser.BooleanComparisonVlTpTextContext ctx) {
    final Object left = visit(ctx.vlTpText(0));
    final String operator = ctx.op.getText();
    final Object right = visit(ctx.vlTpText(1));

    return Comparation.valueOfId(operator).compare(left, right);
  }

  @Override
  public final Object visitIdentifierVlTpBoolean(final ExpressionParser.IdentifierVlTpBooleanContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return expressionContext.get(identifier);
  }

  @Override
  public final Object visitBooleanOR(final ExpressionParser.BooleanORContext ctx) {
    return visitToBoolean(ctx.vlTpBoolean(0)) || visitToBoolean(ctx.vlTpBoolean(1));
  }

  @Override
  public final Object visitBooleanComparisonVlTpDate(final ExpressionParser.BooleanComparisonVlTpDateContext ctx) {
    final Object left = visit(ctx.vlTpDate(0));
    final String operator = ctx.op.getText();
    final Object right = visit(ctx.vlTpDate(1));

    return Comparation.valueOfId(operator).compare(left, right);
  }

  @Override
  public final Object visitCreateNullVlTpBoolean(final ExpressionParser.CreateNullVlTpBooleanContext ctx) {
    return null;
  }

  @Override
  public final Object visitPrimitiveBoolean(final ExpressionParser.PrimitiveBooleanContext ctx) {
    final String value = ctx.getText();

    return this.primitiveBooleanResolver.resolver(value);
  }

  @Override
  public final Object visitIdentifierAttrVlTpBoolean(final ExpressionParser.IdentifierAttrVlTpBooleanContext ctx) {
    final String identifier = ctx.IDENTIFIER_ATTR().getText();

    return this.identifierAttrService.getter(this.expressionContext, identifier);
  }

  @Override
  public final Object visitBooleanNegation(final ExpressionParser.BooleanNegationContext ctx) {
    Boolean value = visitToBoolean(ctx.vlTpBoolean());

    return negate(value);
  }

  @Override
  public final Object visitBooleanAND(final ExpressionParser.BooleanANDContext ctx) {
    return visitToBoolean(ctx.vlTpBoolean(0)) && visitToBoolean(ctx.vlTpBoolean(1));
  }

  @Override
  public final Object visitCreateNullVlTpDate(final ExpressionParser.CreateNullVlTpDateContext ctx) {
    return null;
  }

  @Override
  public final Object visitIdentifierVlTpDate(final ExpressionParser.IdentifierVlTpDateContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return expressionContext.get(identifier);
  }

  @Override
  public final Object visitIdentifierAttrVlTpDate(final ExpressionParser.IdentifierAttrVlTpDateContext ctx) {
    final String identifier = ctx.IDENTIFIER_ATTR().getText();

    return this.identifierAttrService.getter(this.expressionContext, identifier);
  }

  @Override
  public final Object visitPrimitiveDate(final ExpressionParser.PrimitiveDateContext ctx) {
    final String date = ctx.getText();

    return this.primitiveDateResolver.resolver(date);
  }

  @Override
  public final Object visitNumberOperationSign(final ExpressionParser.NumberOperationSignContext ctx) {
    final BigDecimal result = visitToBigDecimal(ctx.vlTpNumber());
    if (ctx.MINUS() != null) {
      return result.multiply(BigDecimal.valueOf(-1));
    }

    return result;
  }

  @Override
  public final Object visitNumberOperationPow(final ExpressionParser.NumberOperationPowContext ctx) {
    final BigDecimal left = visitToBigDecimal(ctx.vlTpNumber(0));
    final BigDecimal right = visitToBigDecimal(ctx.vlTpNumber(1));

    return this.numberOperationPowResolver.resolver(left, right);
  }

  @Override
  public final Object visitIdentifierVlTpNumber(final ExpressionParser.IdentifierVlTpNumberContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return expressionContext.get(identifier);
  }

  @Override
  public final Object visitNumberOperationMultiply(final ExpressionParser.NumberOperationMultiplyContext ctx) {
    BigDecimal left = visitToBigDecimal(ctx.vlTpNumber(0));
    BigDecimal right = visitToBigDecimal(ctx.vlTpNumber(1));

    return this.numberOperationMultiplyResolver.resolver(left, right);
  }

  @Override
  public final Object visitNumberOperationDivide(final ExpressionParser.NumberOperationDivideContext ctx) {
    BigDecimal left = visitToBigDecimal(ctx.vlTpNumber(0));
    BigDecimal right = visitToBigDecimal(ctx.vlTpNumber(1));

    return this.numberOperationDivisionResolver.resolver(left, right);
  }

  @Override
  public final Object visitNumberOperationAddition(final ExpressionParser.NumberOperationAdditionContext ctx) {
    BigDecimal result = BigDecimal.ZERO;
    for (ExpressionParser.VlTpNumberContext vlExpNumberCtx : ctx.vlTpNumber()) {
      BigDecimal child = visitToBigDecimal(vlExpNumberCtx);
      result = result.add(child);
    }

    return result;
  }

  @Override
  public final Object visitCreateNullVlTpNumber(final ExpressionParser.CreateNullVlTpNumberContext ctx) {
    return null;
  }

  @Override
  public final Object visitNumberOperationModulo(final ExpressionParser.NumberOperationModuloContext ctx) {
    final BigDecimal left = visitToBigDecimal(ctx.vlTpNumber(0));
    final BigDecimal right = visitToBigDecimal(ctx.vlTpNumber(1));

    return left.remainder(right);
  }

  @Override
  public final Object visitNumberOperationSubtract(final ExpressionParser.NumberOperationSubtractContext ctx) {
    final BigDecimal left = visitToBigDecimal(ctx.vlTpNumber(0));
    final BigDecimal right = visitToBigDecimal(ctx.vlTpNumber(1));

    return this.numberOperationSubtractionResolver.resolver(left, right);
  }

  @Override
  public final Object visitPrimitiveNumber(final ExpressionParser.PrimitiveNumberContext ctx) {
    return this.primitiveNumberResolver.resolver(ctx.getText());
  }

  @Override
  public final Object visitIdentifierAttrVlTpNumber(final ExpressionParser.IdentifierAttrVlTpNumberContext ctx) {
    final String identifier = ctx.IDENTIFIER_ATTR().getText();

    return this.identifierAttrService.getter(this.expressionContext, identifier);
  }

  @Override
  public final Object visitNumberGroupedBy(final ExpressionParser.NumberGroupedByContext ctx) {
    return visit(ctx.vlTpNumber());
  }

  @Override
  public final Object visitPrimitiveEmptyVlTpObject(final ExpressionParser.PrimitiveEmptyVlTpObjectContext ctx) {
    return new HashMap<String, Object>();
  }

  @Override
  public final Object visitPrimitiveVlTpObject(final ExpressionParser.PrimitiveVlTpObjectContext ctx) {
    final Map<String, Object> map = new HashMap<>();

    ctx.vlExpJsonPair().forEach(context -> {
      final String key = this.primitiveTextResolver.resolver(context.STRING().getText());
      final Object value = visit(context.value());

      map.put(key, value);
    });

    return map;
  }

  @Override
  public final Object visitCreateNullVlTpObject(final ExpressionParser.CreateNullVlTpObjectContext ctx) {
    return null;
  }

  @Override
  public final Object visitIdentifierVlTpObject(final ExpressionParser.IdentifierVlTpObjectContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return expressionContext.get(identifier);
  }

  @Override
  public final Object visitIdentifierAttrVlTpObject(final ExpressionParser.IdentifierAttrVlTpObjectContext ctx) {
    final String identifier = ctx.IDENTIFIER_ATTR().getText();

    return this.identifierAttrService.getter(this.expressionContext, identifier);
  }

  @Override
  public final Object visitCreateNullVlTpText(final ExpressionParser.CreateNullVlTpTextContext ctx) {
    return null;
  }

  @Override
  public final Object visitIdentifierVlTpText(final ExpressionParser.IdentifierVlTpTextContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return expressionContext.get(identifier);
  }

  @Override
  public final Object visitIdentifierAttrVlTpText(final ExpressionParser.IdentifierAttrVlTpTextContext ctx) {
    final String identifier = ctx.IDENTIFIER_ATTR().getText();

    return this.identifierAttrService.getter(this.expressionContext, identifier);
  }

  @Override
  public final Object visitPrimitiveText(final ExpressionParser.PrimitiveTextContext ctx) {
    return this.primitiveTextResolver.resolver(ctx.STRING().getText());
  }

  @Override
  public final Object visitFunctionDate(final ExpressionParser.FunctionDateContext ctx) {
    BigDecimal year = visitToBigDecimal(ctx.vlTpNumber(0));
    BigDecimal month = visitToBigDecimal(ctx.vlTpNumber(1));
    BigDecimal day = visitToBigDecimal(ctx.vlTpNumber(2));

    return this.functionDateResolver.resolver(year, month, day);
  }

  @Override
  public final Object visitFunctionToday(final ExpressionParser.FunctionTodayContext ctx) {
    return expressionContext.get("$CURRENT_DATE");
  }

  @Override
  public final Object visitFunctionAcos(final ExpressionParser.FunctionAcosContext ctx) {
    final BigDecimal value = visitToBigDecimal(ctx.vlTpNumber());

    return this.functionAcosResolver.resolver(value);
  }

  @Override
  public final Object visitFunctionAsin(final ExpressionParser.FunctionAsinContext ctx) {
    final BigDecimal value = visitToBigDecimal(ctx.vlTpNumber());

    return this.functionAsinResolver.resolver(value);
  }

  @Override
  public final Object visitFunctionAtan(final ExpressionParser.FunctionAtanContext ctx) {
    final BigDecimal value = visitToBigDecimal(ctx.vlTpNumber());

    return this.functionAtanResolver.resolver(value);
  }

  @Override
  public final Object visitFunctionCompareDates(final ExpressionParser.FunctionCompareDatesContext ctx) {
    final LocalDate left = visitToLocalDate(ctx.vlTpDate(0));
    final LocalDate right = visitToLocalDate(ctx.vlTpDate(1));

    return this.functionCompareDateResolver.resolver(left, right);
  }

  @Override
  public final Object visitFunctionCompareStrings(final ExpressionParser.FunctionCompareStringsContext ctx) {
    final String left = visitToString(ctx.vlTpText(0));
    final String right = visitToString(ctx.vlTpText(1));

    return this.functionCompareStringResolver.resolver(left, right);
  }

  @Override
  public final Object visitFunctionCompareNumbers(final ExpressionParser.FunctionCompareNumbersContext ctx) {
    final BigDecimal left = visitToBigDecimal(ctx.vlTpNumber(0));
    final BigDecimal right = visitToBigDecimal(ctx.vlTpNumber(1));

    return this.functionCompareNumberResolver.resolver(left, right);
  }

  @Override
  public final Object visitFunctionCos(final ExpressionParser.FunctionCosContext ctx) {
    final BigDecimal value = visitToBigDecimal(ctx.vlTpNumber());

    return this.functionCosResolver.resolver(value);
  }

  @Override
  public final Object visitFunctionDay(final ExpressionParser.FunctionDayContext ctx) {
    final LocalDate value = visitToLocalDate(ctx.vlTpDate());

    return this.functionDayResolver.resolver(value);
  }

  @Override
  public final Object visitFunctionLn(final ExpressionParser.FunctionLnContext ctx) {
    final BigDecimal value = visitToBigDecimal(ctx.vlTpNumber());

    return this.functionLnResolver.resolver(value);
  }

  @Override
  public final Object visitFunctionLog(final ExpressionParser.FunctionLogContext ctx) {
    final BigDecimal value = visitToBigDecimal(ctx.vlTpNumber());

    return this.functionLogResolver.resolver(value);
  }

  @Override
  public final Object visitFunctionMonth(final ExpressionParser.FunctionMonthContext ctx) {
    final LocalDate value = visitToLocalDate(ctx.vlTpDate());

    return this.functionMonthResolver.resolver(value);
  }

  @Override
  public final Object visitFunctionSin(final ExpressionParser.FunctionSinContext ctx) {
    final BigDecimal value = visitToBigDecimal(ctx.vlTpNumber());

    return this.functionSinResolver.resolver(value);
  }

  @Override
  public final Object visitFunctionSqrt(final ExpressionParser.FunctionSqrtContext ctx) {
    final BigDecimal value = visitToBigDecimal(ctx.vlTpNumber());

    return this.functionSqrtResolver.resolver(value);
  }

  @Override
  public final Object visitFunctionTan(final ExpressionParser.FunctionTanContext ctx) {
    final BigDecimal value = visitToBigDecimal(ctx.vlTpNumber());

    return this.functionTanResolver.resolver(value);
  }

  @Override
  public final Object visitFunctionYear(final ExpressionParser.FunctionYearContext ctx) {
    final LocalDate value = visitToLocalDate(ctx.vlTpDate());

    return this.functionYearResolver.resolver(value);
  }

  @Override
  public final Object visitFunctionDatesFromRange(final ExpressionParser.FunctionDatesFromRangeContext ctx) {
    final LocalDate left = visitToLocalDate(ctx.vlTpDate(0));
    final LocalDate right = visitToLocalDate(ctx.vlTpDate(1));

    return this.functionDatesFromRangeResolver.resolver(left, right);
  }

  @Override
  public final Object visitFunctionCollectionAdd(final ExpressionParser.FunctionCollectionAddContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    final Collection<Object> collection = (Collection<Object>) expressionContext.get(identifier);
    final Object value = visit(ctx.value());

    return this.functionCollectionAddResolver.resolver(collection, value);
  }

  private BigDecimal visitToBigDecimal(final ExpressionParser.VlTpNumberContext vlTpNumber) {
    return (BigDecimal) visit(vlTpNumber);
  }

  private Boolean visitToBoolean(final ExpressionParser.VlTpBooleanContext vlTpBooleanContext) {
    return (Boolean) visit(vlTpBooleanContext);
  }

  private LocalDate visitToLocalDate(final ExpressionParser.VlTpDateContext vlTpDate) {
    return (LocalDate) visit(vlTpDate);
  }

  private String visitToString(final ExpressionParser.VlTpTextContext vlTpText) {
    return (String) visit(vlTpText);
  }
}
