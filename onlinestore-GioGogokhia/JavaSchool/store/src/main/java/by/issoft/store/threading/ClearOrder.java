package by.issoft.store.threading;

import by.issoft.store.Store;
import lombok.SneakyThrows;
import java.util.TimerTask;

public class ClearOrder extends TimerTask {
    Store store = Store.getInstance();

    @SneakyThrows
    @Override
    public void run(){
        store.clearPurchasedGoods();
        System.out.println("Order list was cleared");
        store.printPurchasedProducts();
    }
}
