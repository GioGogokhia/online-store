package by.issoft.store.db.repositories;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.db.DBConnector;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final static String GET_ALL_PRODUCTS_BY_CATEGORY =
            "SELECT * FROM products WHERE category=?";
    private final static String ADD_PRODUCT =
            "INSERT INTO products(name, rate, price, category) values (?,?,?,?)";
    private final static String GET_ALL_PRODUCTS = "SELECT * FROM products";


    @SneakyThrows
    public List<Product> getAllProductsByCategory(String categoryName) {
        PreparedStatement preparedStatement = DBConnector.getConnection()
                .prepareStatement(GET_ALL_PRODUCTS_BY_CATEGORY);
        preparedStatement.setString(1, categoryName);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getListOfProductsFromResultSet(resultSet);
    }

    @SneakyThrows
    public void addProduct(Product product, Category category) {
        PreparedStatement preparedStatement = DBConnector.getConnection()
                .prepareStatement(ADD_PRODUCT);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getRate());
        preparedStatement.setInt(3, product.getPrice());
        preparedStatement.setString(4, category.getName());
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    public List<Product> getAllProducts() {
        Statement statement = DBConnector.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_PRODUCTS);
        return getListOfProductsFromResultSet(resultSet);
    }

    @SneakyThrows
    private List<Product> getListOfProductsFromResultSet(ResultSet resultSet) {
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int rate = resultSet.getInt("rate");
            int price = resultSet.getInt("price");
            productList.add(new Product(name, rate, price));
        }
        return productList;
    }

}
