package lab6.dao;

import lab6.DaoException;


import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> findById(Long id) throws DaoException;
    List<T> findAll() throws DaoException;
    Long save(T t) throws DaoException;
    void update(T t) throws DaoException;
    void delete(Long id) throws DaoException;

}