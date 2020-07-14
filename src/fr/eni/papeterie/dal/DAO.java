package fr.eni.papeterie.dal;

import java.util.List;

public interface DAO<T> {

    public T selectById(int identifiant) throws DALException;

    public List<T> selectAll() throws DALException;

    public void update(T object) throws DALException;

    public void insert(T object) throws DALException;

    public void delete(int identifiant) throws DALException;
}
