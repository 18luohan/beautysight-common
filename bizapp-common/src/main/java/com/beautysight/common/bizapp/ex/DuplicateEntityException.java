/*
 * Copyright (C) 2014, BeautySight Inc. All rights reserved.
 */

package com.beautysight.common.bizapp.ex;

/**
 * Here is Javadoc.
 * <p/>
 * Created by chenlong on 2015-05-08.
 *
 * @author chenlong
 * @since 1.0
 */
public class DuplicateEntityException extends ApplicationException {

    public DuplicateEntityException(String message) {
        this(CommonErrorId.server_data_stale, message);
    }

    public DuplicateEntityException(Error.Id errorId, String message) {
        super(errorId, message);
    }

}
