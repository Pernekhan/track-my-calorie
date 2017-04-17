package com.trackmycalorie.dao.api.crud;

import java.io.Serializable;

public interface SaveEntity<T extends Serializable> {
    T save(T entity);
}
