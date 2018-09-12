package com.github.thiagogarbazza.expressionresolve.compile;

import com.github.thiagogarbazza.expressionresolve.parser.ExpressionLexer;

final class LexerBuilder {

    public ExpressionLexer build(final ExpressionInputStream in) {
        ExpressionLexer lexer = new ExpressionLexer(in);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new LexerErrorListener());
        return lexer;
    }
}
