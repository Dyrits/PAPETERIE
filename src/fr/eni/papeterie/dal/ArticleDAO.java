package fr.eni.papeterie.dal;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.dal.DALException;

import java.util.List;

public interface ArticleDAO {

    public Article selectById(int identifiant) throws DALException;

    public List<Article> selectAll() throws DALException;

    public void update(Article article) throws DALException;

    public void insert(Article article) throws DALException;

    public void delete(int identifiant) throws DALException;
}
