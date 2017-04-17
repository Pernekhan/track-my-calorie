package com.trackmycalorie.dao.api.crud;

import java.io.Serializable;

public interface CreateEntity<T extends Serializable> {
    void create(T entity);
}
