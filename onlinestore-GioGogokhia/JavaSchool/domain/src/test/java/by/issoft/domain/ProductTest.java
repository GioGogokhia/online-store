package by.issoft.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private static final String NAME = "name";
    private static final int RATE = 6;
    private static final int  PRICE = 500;
    private static final Product product = new Product(NAME, RATE, PRICE);

    @Test
    void getName() {
        assertEquals(NAME, product.getName());
    }

    @Test
    void getRate() {
        assertEquals(RATE, product.getRate());
    }

    @Test
    void getPrice() {
        assertEquals(PRICE, product.getPrice());
    }

    @Test
    void testToString() {
        assertEquals(String.format("Name: '%s', Rate: %1d, Price: %1d", NAME, RATE, PRICE), product.toString());
    }
}