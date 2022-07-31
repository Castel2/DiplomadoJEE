package co.com.claro.services;

import java.util.List;

public interface Service<T> {

    T save (T t);

    T findById(Long id);

    List<T> findAll();

    void delete (T t);

}
