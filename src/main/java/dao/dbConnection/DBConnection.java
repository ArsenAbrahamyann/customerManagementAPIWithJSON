package dao.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?createDatabaseIfNotExist=true" ,
                    "postgres", "postgres");
        }catch (Exception massage) {
            System.out.println(massage);
        }
        return connection;
    }
}
