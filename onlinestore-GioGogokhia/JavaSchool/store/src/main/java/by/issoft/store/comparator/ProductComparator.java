package by.issoft.store.comparator;

import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ProductComparator{

    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String RATE = "rate";
    public static final String DESCENDING = "desc";

    public static Comparator<Product> createComparator(Map<String, String> sortingRules) {
        List<Map.Entry<String, String>> listOfRules = new ArrayList<>();
        sortingRules.entrySet().forEach(listOfRules::add);
        Comparator<Product> comparator = createComparator(listOfRules.get(0));
        for (int i = 1; i < listOfRules.size(); i++) {
            comparator = createComparatorFromExisting(comparator, listOfRules.get(i));
        }
        return comparator;
    }

    private static Comparator<Product> createComparator(Map.Entry<String, String> firstRule) {
        String sortKey = firstRule.getKey();
        String order = firstRule.getValue();
        Comparator<Product> comparator;
        switch (sortKey) {
            case NAME: {
                if(DESCENDING.equals(order)) {
                    comparator = Comparator.comparing(Product::getName, Comparator.reverseOrder());
                } else {
                    comparator = Comparator.comparing(Product::getName);
                }
                break;
            }
            case PRICE: {
                if(DESCENDING.equals(order)) {
                    comparator = Comparator.comparing(Product::getPrice, Comparator.reverseOrder());
                } else {
                    comparator = Comparator.comparing(Product::getPrice);
                }
                break;
            }
            case RATE: {
                if(DESCENDING.equals(order)) {
                    comparator = Comparator.comparing(Product::getRate, Comparator.reverseOrder());
                } else {
                    comparator = Comparator.comparing(Product::getRate);
                }
                break;
            }
            default:
                throw new IllegalArgumentException();
        }
        return comparator;
    }

    private static Comparator<Product> createComparatorFromExisting(Comparator<Product> comparator, Map.Entry<String, String> rule) {
        String sortKey = rule.getKey();
        String order = rule.getValue();
        switch (sortKey) {
            case NAME: {
                if(DESCENDING.equals(order)) {
                    comparator = comparator.thenComparing(Product::getName, Comparator.reverseOrder());
                } else {
                    comparator = comparator.thenComparing(Product::getName);
                }
                break;
            }
            case PRICE: {
                if(DESCENDING.equals(order)) {
                    comparator = comparator.thenComparing(Product::getPrice, Comparator.reverseOrder());
                } else {
                    comparator = comparator.thenComparing(Product::getPrice);
                }
                break;
            }
            case RATE: {
                if(DESCENDING.equals(order)) {
                    comparator = comparator.thenComparing(Product::getRate, Comparator.reverseOrder());
                } else {
                    comparator = comparator.thenComparing(Product::getRate);
                }
                break;
            }
            default:
                throw new IllegalArgumentException();
        }
        return comparator;
    }
}
