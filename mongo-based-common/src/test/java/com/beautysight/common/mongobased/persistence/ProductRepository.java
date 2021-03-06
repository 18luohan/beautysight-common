/*
 * Copyright (C) 2014, BeautySight Inc. All rights reserved.
 */

package com.beautysight.common.mongobased.persistence;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Here is Javadoc.
 * <p/>
 * Created by chenlong on 2015-05-13.
 *
 * @author chenlong
 * @since 1.0
 */
@Repository
public class ProductRepository extends AbstractMongoRepository<Product> {

    public List<Product> findProductsBy(Category category) {
        return findBy(Conditions.newInstance().andEqual("category_id", category.id()));
    }

    @Override
    protected Class<Product> entityClass() {
        return Product.class;
    }

}
