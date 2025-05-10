package org.onumujeres.project.config;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConfiguration {

    private static final Logger LOGGER = Logger.getLogger(DatabaseConfiguration.class.getName());
    private static BasicDataSource dataSource;

    static {
        // Configuración del pool de conexiones al cargar la clase
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/onumujeres?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // Configuración del pool
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        dataSource.setMaxIdle(5);
        dataSource.setMinIdle(2);
        dataSource.setMaxWaitMillis(5000); // 5 segundos máximo de espera
    }

    /**
     * Obtiene una conexión del pool de conexiones.
     *
     * @return una conexión válida
     * @throws SQLException si no se puede obtener una conexión
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Cierra una conexión de forma segura.
     *
     * @param conn la conexión a cerrar
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close(); // Devuelve la conexión al pool
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al cerrar la conexión", e);
            }
        }
    }

    /*
    /* Método de prueba para validar la conexión.*/

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error de conexión a la base de datos", e);
        }
    }
}
