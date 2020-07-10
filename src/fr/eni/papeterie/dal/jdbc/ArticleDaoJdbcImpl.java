package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.*;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoJdbcImpl {
    private static final String SELECT_BY_ID =
            "SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type " +
                    "FROM Articles WHERE idArticle = ?";
    private static final String SELECT_ALL =
            "SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type " +
                    "FROM Articles";

    /**
     * @param identifiant int | Identifiant de l'article à sélectionné.
     * @return Article | Article correspondant à l'identifiant entré en paramètre.
     * @throws DALException Exception.
     */
    public Article selectById(int identifiant) throws DALException {
        Connection connection = DBConnection.connect();
        Article article = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, identifiant);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                article = initialize(resultSet);
            }
        } catch (SQLException exception) {
            throw new DALException("Sélection avec l'identifiant " + identifiant + " échouée." , exception);
        }
        DBConnection.disconnect(connection);
        return article;
    }

    /**
     * @return ArrayList | Liste de l'ensemble des articles.
     * @throws DALException Exception
     */
    public List<Article> selectAll() throws DALException {
        Connection connection = DBConnection.connect();
        List<Article> articles = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            Article article = null;
            while (resultSet.next()) {
                article = initialize(resultSet);
                articles.add(article);
            }
        } catch (SQLException exception) {
            throw new DALException("Sélection des résultats échouée." , exception);
        }
        DBConnection.disconnect(connection);
        return articles;
    }

    /**
     * @param resultSet ResultSet | Set de résultat contenant les données extraites de la table interrogé via une requête.
     * @return Article | Article incluant les données du set de résultat.
     * @throws DALException Exception
     */
    private Article initialize(ResultSet resultSet) throws DALException {
        Article article = null;
        try {
            String type = resultSet.getString("type").toUpperCase();
            if (type.equals("STYLO")) {
                article = new Stylo(resultSet.getString("couleur"));
            } else if (type.equals("RAMETTE")) {
                article = new Ramette(resultSet.getInt("ramette"));
            }
            assert article != null;
            article.setIdArticle(resultSet.getInt("idArticle"));
            article.setMarque(resultSet.getString("marque"));
            article.setReference(resultSet.getString("reference"));
            article.setPrixUnitaire(resultSet.getFloat("prixUnitaire"));
            article.setDesignation(resultSet.getString("designation"));
            article.setQteStock(resultSet.getInt("qteStock"));
        } catch (SQLException exception) {
            throw  new DALException("Erreur lors de la création d'un nouvel article avec les données du set de résultat.", exception);
        }
        return article;
    }
}
