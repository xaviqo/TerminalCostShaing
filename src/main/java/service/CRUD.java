package service;

import java.util.List;

public interface CRUD<T> {
    public boolean save(T t);
    public boolean delete(T t);
    public boolean update(T t);
    public List<T> getList();
}
