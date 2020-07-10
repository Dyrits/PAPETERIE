package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DBConnection;

import java.sql.*;

public class ArticleDaoJdbcImpl {
    private static final String SELECT_BY_ID = "SELECT * FROM Articles WHERE idArticle = ?";

    public Article selectById(int identifiant) throws DALException {
        Connection connection = DBConnection.connect();
        Article article;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, identifiant);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String type = resultSet.getString("type").toUpperCase();
                if (type.equals("STYLO")) {
                    article = new Stylo(resultSet.getString("couleur"));
                } else if (type.equals("RAMETTE")) {
                    article = new Ramette(resultSet.getInt("ramette")));
                }
                article.setIdArticle(identifiant);
                article.setMarque(resultSet.getString("marque"));
                article.setReference(resultSet.getString("reference"));
                article.setPrixUnitaire(resultSet.getFloat("prixUnitaire"));
                article.setDesignation(resultSet.getString("designation"));
                article.setQteStock(resultSet.getInt("qteStock"));
            }
        } catch (SQLException exception) {
            throw new DALException("Sélection avec l'identifiant " + identifiant + " échouée." , exception);
        }
        return article;
    }
}
