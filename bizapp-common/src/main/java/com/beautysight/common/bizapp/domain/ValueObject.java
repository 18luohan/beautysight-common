/*
 * Copyright (C) 2014, BeautySight Inc. All rights reserved.
 */

package com.beautysight.common.bizapp.domain;

import com.beautysight.common.bizapp.JsonAnyFieldVisible;
import com.google.common.base.Optional;

import java.io.Serializable;

/**
 * A tag interface that represents value object.
 *
 * @author chenlong
 * @since 1.0
 */
public abstract class ValueObject implements JsonAnyFieldVisible, Serializable {

    public void validate() {
        validate(Optional.<String>absent());
    }

    public void validate(Optional<String> fieldPrefix) {
        throw new UnsupportedOperationException("This method has not been implemented");
    }

    protected final String prefixTo(String field, Optional<String> fieldPrefix) {
        if (fieldPrefix.isPresent()) {
            return String.format("%s.%s", fieldPrefix.get(), field);
        }
        return field;
    }

    protected final String prefix(Optional<String> fieldPrefix) {
        return (fieldPrefix.isPresent() ? (fieldPrefix.get() + ".") : "");
    }

}
