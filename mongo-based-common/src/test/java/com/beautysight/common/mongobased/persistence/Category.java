/*
 * Copyright (C) 2014, BeautySight Inc. All rights reserved.
 */

package com.beautysight.common.mongobased.persistence;

import com.beautysight.common.mongobased.domain.MongoAwareEntity;
import org.mongodb.morphia.annotations.Entity;

/**
 * Here is Javadoc.
 * <p/>
 * Created by chenlong on 2015-05-18.
 *
 * @author chenlong
 * @since 1.0
 */
@Entity(value = "categories", noClassnameStored = true)
public class Category extends MongoAwareEntity {
    private String name;
    private String description;

    private Category() {}

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
