package fr.eni.papeterie.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=PAPETERIE_DB";
    static final String USER = "sa";
    static final String PASSWORD = "Pa$$w0rd";
    Connection connection = null;

    public static Connection connect() throws DALException {
        try {
            DriverManager.registerDriver(new SQLServerDriver());
        } catch (SQLException exception) {
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
