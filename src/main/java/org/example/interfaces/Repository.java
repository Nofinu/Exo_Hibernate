package org.example.interfaces;

import java.util.List;

public interface Repository<T> {

    void begin();
    void end();

    boolean create(T o);

    boolean update(T o);

    boolean delete(T o);

    T findById(int id);

    List<T> findAll ();
}
