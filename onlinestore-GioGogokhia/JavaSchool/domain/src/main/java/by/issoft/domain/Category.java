package by.issoft.domain;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;

public class Category {
    private final String name;
    private final List<Product> productList;

    public Category(String name){
        this.name = name;
        productList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addProduct(Product product){
        productList.add(product);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Category: " + name + "\nProducts:\n");
        for (Product product : productList
        ) {
            stringBuilder.append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    public String getSortedProducts(Comparator<Product> comparator) {
        StringBuilder stringBuilder = new StringBuilder("Category: " + name + "\nProducts:\n");
        productList.stream().sorted(comparator).forEach(product -> stringBuilder.append(product).append("\n"));
        return stringBuilder.toString();
    }

    public List<Product> getProductList() {
        return productList;
    }
}
