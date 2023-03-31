package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.db.repositories.CategoryRepository;
import by.issoft.store.db.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Store {
    private final List<Product> purchasedGoods;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private static volatile Store storeInstance;

    private Store() {
        categoryRepository = new CategoryRepository();
        productRepository = new ProductRepository();
        purchasedGoods = new CopyOnWriteArrayList<>();
    }

    private static class StoreHolder {
        private static final Store store = new Store();
    }

    public static Store getInstance() {
        return StoreHolder.store;
    }

    public void addCategory(Category category){
        categoryRepository.addCategory(category.getName());
    }

    public void addPurchasedProduct(Product product){
        purchasedGoods.add(product);
    }

    public void clearPurchasedGoods(){
        StoreHolder.store.purchasedGoods.clear();
    }

    public List<Product> getPurchasedGoods() {
        return purchasedGoods;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Store:\n");
        for (Category category : categoryRepository.getCategories()
        ) {
            stringBuilder.append(category);
        }
        return stringBuilder.toString();
    }

    public String getSortedStore(Comparator<Product> comparator) {
        StringBuilder stringBuilder = new StringBuilder("Store:\n");
        for (Category category : categoryRepository.getCategories()
        ) {
            stringBuilder.append(category.getSortedProducts(comparator));
        }
        return stringBuilder.toString();
    }

    public String getTopFiveByPrice() {
        StringBuilder stringBuilder = new StringBuilder("Top five products by price:\n");
        List<Product> allProducts = new ArrayList<>();
        categoryRepository.getCategories().forEach(category -> allProducts.addAll(category.getProductList()));
        allProducts.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).limit(5)
                .forEach(product -> stringBuilder.append(product).append("\n"));
        return stringBuilder.toString();
    }

    public Product getRandomProductFromStore(){
        List<Product> allProducts = new ArrayList<>();
        categoryRepository.getCategories().forEach(category -> allProducts.addAll(category.getProductList()));
        return allProducts.get(new Random().nextInt(allProducts.size()));
    }

    public void printPurchasedProducts(){
        System.out.println("------------------------------");
        System.out.println("Cart refreshed");
        for (int i = 1; i < purchasedGoods.size(); i++){
            System.out.println(i + ": " + purchasedGoods.get(i - 1));
        }
        System.out.println("\n" + purchasedGoods.size() + " products are in the cart");
        System.out.println("------------------------------");
    }
}