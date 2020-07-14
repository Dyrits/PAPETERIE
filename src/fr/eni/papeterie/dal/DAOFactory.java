package fr.eni.papeterie.dal;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.dal.jdbc.ArticleDaoJdbcImpl;

public class DAOFactory {

    public static DAO<Article> getArticleDAO()  {
        return new ArticleDaoJdbcImpl();
    }
}
