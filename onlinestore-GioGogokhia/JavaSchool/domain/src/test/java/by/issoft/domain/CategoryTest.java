package by.issoft.domain;

import by.issoft.domain.categories.BikeCategory;
import by.issoft.domain.categories.MilkCategory;
import by.issoft.domain.categories.PhoneCategory;
import com.github.javafaker.Cat;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void getName() {
        Category category = new Category("name");
        assertEquals("name", category.getName());

        category = new BikeCategory();
        assertEquals("bike", category.getName());

        category = new MilkCategory();
        assertEquals("milk", category.getName());

        category = new PhoneCategory();
        assertEquals("phone", category.getName());
    }

    @Test
    void testToString() {
        Category category = new Category("category");
        category.addProduct(new Product("first", 6, 670));
        category.addProduct(new Product("second", 2, 55));
        category.addProduct(new Product("third", 10, 5000));

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("first", 6, 670));
        productList.add(new Product("second", 2, 55));
        productList.add(new Product("third", 10, 5000));

        StringBuilder stringBuilder = new StringBuilder("Category: " + "category" + "\nProducts:\n");
        productList.forEach(product -> stringBuilder.append(product).append("\n"));

        assertEquals(stringBuilder.toString(), category.toString());
    }

    @Test
    void addProduct() {
        Category category = new Category("name");
        List<Product> productList = new ArrayList<>();

        Product product1 = new Product("first", 5, 100);
        productList.add(product1);
        category.addProduct(product1);
        assertEquals(productList, category.getProductList());

        Product product2 = new Product("second", 8, 55);
        productList.add(product2);
        category.addProduct(product2);
        assertEquals(productList, category.getProductList());
    }

    @Test
    void getSortedProducts() {
        Category category = new Category("category");
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("first", 3, 200));
        productList.add(new Product("second", 7, 4600));
        productList.add(new Product("third", 4, 25_000));
        productList.add(new Product("fourth", 10, 25));
        productList.forEach(category::addProduct);

        Comparator<Product> comparator = Comparator.comparing(Product::getName, Comparator.reverseOrder())
                .thenComparing(Product::getPrice).thenComparing(Product::getPrice, Comparator.reverseOrder());

        StringBuilder stringBuilder = new StringBuilder("Category: " + "category" + "\nProducts:\n");
        productList.stream().sorted(comparator).forEach(product -> stringBuilder.append(product).append("\n"));

        assertEquals(stringBuilder.toString(), category.getSortedProducts(comparator));
    }

    @Test
    void getProductList() {
        Category category = new Category("name");
        assertEquals(category.getProductList(), new ArrayList<>());
    }
}