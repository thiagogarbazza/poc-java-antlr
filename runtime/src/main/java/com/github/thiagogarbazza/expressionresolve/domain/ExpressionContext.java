package com.github.thiagogarbazza.expressionresolve.domain;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.github.thiagogarbazza.expressionresolve.domain.PropertieUtil.getPropertie;
import static org.apache.commons.lang3.StringUtils.trimToNull;
import static org.apache.commons.lang3.Validate.isInstanceOf;
import static org.apache.commons.lang3.Validate.notNull;

public class ExpressionContext {

    private final Map<String, Object> variables;

    public ExpressionContext() {
        variables = new HashMap<String, Object>();
        set("today", Calendar.getInstance());
    }

    public void set(final String variable, final Object value) {
        final String key = buildKey(variable);
        variables.put(key, value);
    }

    private String buildKey(final String variable) {
        final String key = trimToNull(variable);
        notNull(key, getPropertie("context.variable.name-not-be-null-or-empty"));
        return key;
    }

    public <T> T get(final String variable, Class<T> type) {
        final String key = buildKey(variable);
        final Object value = variables.get(key);
        notNull(value, getPropertie("context.variable.not-present"), key);
        isInstanceOf(type, value, getPropertie("context.variable.not-instance-valid"), key, type);
        return (T) value;
    }
}