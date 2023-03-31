package by.issoft.store.threading;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import lombok.SneakyThrows;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreateOrder extends Thread{
    private final Store store = Store.getInstance();

    @SneakyThrows
    @Override
    public void run(){
        Product purchasedProduct = store.getRandomProductFromStore();
        System.out.println("Ordered product: " + purchasedProduct);
        store.addPurchasedProduct(purchasedProduct);
        store.printPurchasedProducts();
        TimeUnit.SECONDS.sleep(new Random().nextInt(30));
    }
}
