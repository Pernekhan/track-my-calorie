package com.trackmycalorie.dao.api.crud;

import java.io.Serializable;

public interface FindEntity<T extends Serializable, PK extends Serializable> {
    T find(PK pk);
}
