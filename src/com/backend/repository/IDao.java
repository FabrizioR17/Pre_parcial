package com.backend.repository;

public interface IDao<T> {
    T registrar(T t);

    T buscarPorId(Long id);

}