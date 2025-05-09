package com.tiendaropa.util;

import org.jdbi.v3.core.Jdbi;

public class DatabaseConnector {
    private static final String URL = "jdbc:mariadb://localhost:3306/tienda_ropa";
    private static final String USER = "root"; // Cambia esto por tu usuario
    private static final String PASSWORD = ""; // Cambia esto por tu contraseña

    private static Jdbi jdbi;

    public static Jdbi getJdbi() {
        if (jdbi == null) {
            jdbi = Jdbi.create(URL, USER, PASSWORD);
        }
        return jdbi;
    }

    // Método para probar la conexión
    public static boolean testConnection() {
        try {
            getJdbi().withHandle(handle -> {
                handle.execute("SELECT 1");
                return null;
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}