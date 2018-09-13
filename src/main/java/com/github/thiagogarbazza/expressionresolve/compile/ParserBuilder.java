package com.github.thiagogarbazza.expressionresolve.compile;

import org.antlr.v4.runtime.CommonTokenStream;

import com.github.thiagogarbazza.expressionresolve.parser.ExpressionLexer;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser;

final class ParserBuilder {

    public ExpressionParser build(final ExpressionLexer lexer) {
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        ExpressionParser parser = new ExpressionParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new LexerErrorListener());
        parser.setErrorHandler(new ParserErrorStrategy());
        return parser;
    }
}
