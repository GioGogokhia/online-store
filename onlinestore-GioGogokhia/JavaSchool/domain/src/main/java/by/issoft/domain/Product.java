package by.issoft.domain;

public class Product {
    private final String name;
    private final int rate;
    private final int price;

    public Product(String name, int rate, int price){
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Name: '%s', Rate: %1d, Price: %1d", name, rate, price);
    }
}
