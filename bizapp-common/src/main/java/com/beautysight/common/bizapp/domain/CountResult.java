/*
 * Copyright (C) 2014, BeautySight Inc. All rights reserved.
 */

package com.beautysight.common.bizapp.domain;

import com.beautysight.common.bizapp.app.ResultObject;

/**
 * @author chenlong
 * @since 1.0
 */
public class CountResult implements ResultObject {

    private int count;

    public CountResult() {
    }

    public CountResult(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

}