package by.issoft.store.db;

import by.issoft.store.RandomStorePopulator;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.Statement;

public class DBInit {
    final static String DROP_IF_EXISTS_CATEGORIES = "DROP TABLE IF EXISTS categories";
    final static String DROP_IF_EXISTS_PRODUCTS = "DROP TABLE IF EXISTS products";
    final static String CREATE_CATEGORIES = "CREATE TABLE IF NOT EXISTS categories (" +
            "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
            "name VARCHAR(255) UNIQUE NOT NULL);";
    final static String CREATE_PRODUCTS = "CREATE TABLE IF NOT EXISTS products (" +
            "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
            "category VARCHAR(255) NOT NULL," +
            "name VARCHAR(255) NOT NULL," +
            "rate INT NOT NULL," +
            "price INT NOT NULL," +
            "FOREIGN KEY (category) REFERENCES categories(name));";

    @SneakyThrows
    public static void initDB(){
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(DROP_IF_EXISTS_PRODUCTS);
        statement.executeUpdate(DROP_IF_EXISTS_CATEGORIES);
        statement.executeUpdate(CREATE_CATEGORIES);
        statement.executeUpdate(CREATE_PRODUCTS);
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        randomStorePopulator.fillStore();
    }
}
