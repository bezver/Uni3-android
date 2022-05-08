package com.example.usermanager.Core.Repository;

import java.util.ArrayList;

public interface IRepository<T, K> {
    ArrayList<T> GetAll();
    T GetById(K id);
    T Add(T item);
    T Update(T item);
    void Delete(K id);
}