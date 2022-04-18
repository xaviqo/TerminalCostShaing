package service;

import java.util.Set;

public interface CRUD<T> {
    public boolean save(T t);
    public boolean delete(T t);
    public boolean update(T t);
    public Set<T> getSet();
}
