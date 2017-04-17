package com.trackmycalorie.dao.api.crud;

import java.io.Serializable;

public interface Dao<T extends Serializable, PK extends Serializable> extends CreateEntity<T>, SaveEntity<T>, RemoveEntity<T>, FindEntity<T, PK> {
}
