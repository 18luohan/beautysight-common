/*
 * Copyright (C) 2014, BeautySight Inc. All rights reserved.
 */

package com.beautysight.common.mongobased.domain;

import com.beautysight.common.bizapp.domain.DomainEntity;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

/**
 * Abstract class to be extended by any domain entity that will be
 * saved into mongo db.
 *
 * @author chenlong
 * @since 1.0
 */
public abstract class MongoAwareEntity implements DomainEntity {

    private static final long serialVersionUID = -2361648049519441593L;

    @Id
    protected ObjectId id;
    protected Date createdAt = new Date();
    protected Date modifiedAt;

    public void setId(String idString) {
        this.id = new ObjectId(idString);
    }

    public void setMongoId(ObjectId id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public ObjectId id() {
        return id;
    }

    public String idStr() {
        return id.toHexString();
    }

    public Date createdAt() {
        return this.createdAt;
    }

    public Date modifiedAt() {
        return this.modifiedAt;
    }

}