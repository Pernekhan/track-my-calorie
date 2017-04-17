package com.trackmycalorie.dao.api.crud;

import java.io.Serializable;

public interface RemoveEntity<T extends Serializable> {
    void remove(T entity);
}
