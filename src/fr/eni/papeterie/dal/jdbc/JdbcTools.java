package fr.eni.papeterie.dal.jdbc;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
    static final String DRIVER = Settings.getProperty("DRIVER");
    static final String URL = Settings.getProperty("URL");
    static final String USER = Settings.getProperty("USER");
    static final String PASSWORD = Settings.getProperty("PASSWORD");

    public static Connection connect() throws DALException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException exception) {
            throw  new DALException("Erreur. Impossible de monter le driver en mémoire.", exception);
        }
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException exception) {
            throw  new DALException("Erreur lors de la tentative de connexion. Vérifiez la chaîne de connexion.", exception);
        }
    }

    public static void disconnect(Connection connection) throws DALException {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw  new DALException("Erreur lors de la déconnexion.", exception);
        }
    }
}
