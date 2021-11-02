package vn.dev.danghung.dao.jdbc;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractFacade<T> {
    public abstract void create(T entity) throws SQLException;

    public abstract void edit(T entity) throws SQLException;

    public abstract void remove(T entity) throws SQLException;

    public abstract T find(Object id) throws SQLException;

    public abstract List<T> findAll() throws SQLException;

    public abstract int count() throws SQLException;
}
