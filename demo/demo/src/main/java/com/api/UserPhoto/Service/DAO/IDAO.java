package com.api.UserPhoto.Service.DAO;

import java.util.List;

public interface IDAO<T, ID> {
    List<T> findAll();
    T getById(ID id);
    void update(T entity);
    T save(T entity);
    void delete(T entity);
    void create(T entity);
}