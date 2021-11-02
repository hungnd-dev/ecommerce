package vn.dev.danghung.service.entity;

import java.util.List;

public interface EntityService<T>{
    List<T> findAll();
    T findById(int id);
    T findByName(String name);
    void save(T t);
}
