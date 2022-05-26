package com.krypczyk.restservice.dao;

import java.util.List;

public interface Dao<T> {

    List<T> getAll();

    void save(T t);

}
