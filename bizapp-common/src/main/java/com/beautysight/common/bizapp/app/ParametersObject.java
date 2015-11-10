/*
 * Copyright (C) 2014, BeautySight Inc. All rights reserved.
 */

package com.beautysight.common.bizapp.app;

import com.google.common.base.Optional;

/**
 * Actually, this is a parameters object. All the classes which suffix with "PO" should
 * implement this interface. That is, PO is the abbreviation of "Parameters Object".
 *
 * @author chenlong
 * @since 1.0
 */
public abstract class ParametersObject implements DTO {

    public void validate() {
        validate(Optional.<String>absent());
    }

    public void validate(Optional<String> fieldPrefix) {
        throw new UnsupportedOperationException("This method has not been implemented");
    }

    public <T> T transformToDomainModel() {
        throw new UnsupportedOperationException("This method has not been implemented");
    }

    protected final String prefixTo(String field, Optional<String> fieldPrefix) {
        if (fieldPrefix.isPresent()) {
            return String.format("%s.%s", fieldPrefix.get(), field);
        }
        return field;
    }

    protected final String prefix(Optional<String> fieldPrefix) {
        return (fieldPrefix.isPresent() ? fieldPrefix.get() : "");
    }

}