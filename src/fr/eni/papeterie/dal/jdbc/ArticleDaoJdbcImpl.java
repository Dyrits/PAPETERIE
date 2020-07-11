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
    private static final String UPDATE =
            "UPDATE Articles " +
                "SET reference = ?, marque = ?, designation = ?, prixUnitaire = ?, qteStock = ?, grammage = ?, couleur = ?, type = ? " +
                "WHERE idArticle = ?";
    private static final String INSERT =
            "INSERT INTO Articles(reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM Articles WHERE idArticle = ?";


    // Requêtes supplémentaires incluses dans le corrigé :
    private static final String SELECT_BY_MOT_CLE =
            "SELECT reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type" +
                "FROM Articles WHERE marque LIKE ? OR designation LIKE ?";
    private static final String SELECT_BY_MARQUE =
            "SELECT reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type" +
                    "FROM Articles WHERE marque = ?";

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
                article = initializeWith(resultSet);
            }
            statement.close();
        } catch (SQLException exception) {
            throw new DALException("Erreur lors de la sélection de l'article avec l'identifiant " + identifiant + "." , exception);
        }
        DBConnection.disconnect(connection);
        return article;
    }

    /**
     * Mise à jour des données de la table via une requête DML INSERT ou UPDATE.
     * @param article Article | Article correspondant à la ligne à mettre à jour ou ajouter.
     * @param insert Booléen | "true" si la requête est de type INSERT. "false" si la requête est de type UPDATE.
     * @throws DALException Exception.
     */
    private void DML(Article article, boolean insert) throws DALException {
        Connection connection = DBConnection.connect();
        try {
            PreparedStatement statement = insert ?
                    connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS) : connection.prepareStatement(UPDATE);
            statement.setString(1, article.getReference());
            statement.setString(2, article.getMarque());
            statement.setString(3, article.getDesignation());
            statement.setFloat(4, article.getPrixUnitaire());
            statement.setInt(5, article.getQteStock());
            if (article instanceof Stylo) {
                Stylo stylo = (Stylo) article;
                statement.setNull(6, Types.INTEGER); // Valeur nulle pour le grammage.
                statement.setString(7, stylo.getCouleur());
                statement.setString(8, "STYLO"); // Valeur nulle pour la couleur.
            }
            else if (article instanceof Ramette) {
                Ramette ramette = (Ramette) article;
                statement.setInt(6, ramette.getGrammage());
                statement.setNull(7, Types.VARCHAR); // Valeur nulle pour la couleur.
                statement.setString(8, "RAMETTE"); // Valeur nulle pour la couleur.
            }
            if (!insert) {
                statement.setInt(9, article.getIdArticle());
                statement.executeUpdate();
            }
            else  {
                int rows = statement.executeUpdate();
                if (rows == 1) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) { article.setIdArticle(resultSet.getInt(1)); }
                }
            }
            statement.close();
        } catch (SQLException exception) {
            throw new DALException("Erreur lors de la mise à jour des données.", exception);
        }
        DBConnection.disconnect(connection);
    }

    public void update(Article article) throws DALException {
        DML(article, false);
    }

    public void insert(Article article) throws DALException {
        DML(article, true);
    }

    /**
     * @param identifiant int | Identifiant de l'article à supprimer.
     * @throws DALException Exception.
     */
    public void delete(int identifiant) throws DALException {
        Connection connection = DBConnection.connect();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1, identifiant);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            throw new DALException("Erreur lors de la suppression de l'article avec l'identifiant " + identifiant + "." , exception);
        }
        DBConnection.disconnect(connection);
    }

    /**
     * @param query String |Requête SQL.
     * @param parameter String |  Paramètre de recherche.
     * @return ArrayList | Liste de l'ensemble des articles
     * @throws DALException Exception
     */
    private List<Article> selectAllBy(String query, String parameter) throws DALException {
        Connection connection = DBConnection.connect();
        List<Article> articles = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            if (parameter != null) {
                statement.setString(1, parameter);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                articles.add(initializeWith(resultSet));
            }
            statement.close();
        } catch (SQLException exception) {
            throw new DALException("Erreur lors de la sélection des articles." , exception);
        }
        DBConnection.disconnect(connection);
        return articles;
    }

    /**
     * @return ArrayList | Liste de l'ensemble des articles.
     * @throws DALException Exception
     */
    public List<Article> selectAll() throws DALException {
        return selectAllBy(SELECT_ALL, null);
    }

    // Méthode supplémentaire incluse dans le corrigé :
    public List<Article> selectByMotCle(String parameter) throws DALException {
        return selectAllBy(SELECT_BY_MOT_CLE, parameter);
    }

    // Méthode supplémentaire incluse dans le corrigé :
    public List<Article> selectByMarque(String parameter) throws DALException {
        return selectAllBy(SELECT_BY_MARQUE, parameter);
    }

    /**
     * @param resultSet ResultSet | Set de résultat contenant les données extraites de la table interrogé via une requête.
     * @return Article | Article incluant les données du set de résultat.
     * @throws DALException Exception
     */
    private Article initializeWith(ResultSet resultSet) throws DALException {
        Article article = null;
        try {
            String type = resultSet.getString("type").trim().toUpperCase();
            if (type.equals("STYLO")) {
                article = new Stylo(resultSet.getString("couleur"));
            } else if (type.equals("RAMETTE")) {
                article = new Ramette(resultSet.getInt("grammage"));
            }
            assert article != null;
            article.setIdArticle(resultSet.getInt("idArticle"));
            article.setMarque(resultSet.getString("marque"));
            article.setReference(resultSet.getString("reference").trim());
            article.setPrixUnitaire(resultSet.getFloat("prixUnitaire"));
            article.setDesignation(resultSet.getString("designation"));
            article.setQteStock(resultSet.getInt("qteStock"));
        } catch (SQLException exception) {
            throw  new DALException("Erreur lors de la création d'un nouvel article avec les données du set de résultat.", exception);
        }
        return article;
    }
}
