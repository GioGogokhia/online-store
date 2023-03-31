package by.issoft.consoleApp;

import by.issoft.store.Store;
import by.issoft.store.comparator.ProductComparator;
import by.issoft.store.db.DBInit;
import by.issoft.store.threading.ClearOrder;
import by.issoft.store.threading.CreateOrder;
import by.issoft.store.xmlParser.XMLParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;

public class StoreApp {
    public static void main(String[] args) throws IOException {
        Store store = Store.getInstance();
        DBInit.initDB();
        System.out.println(store);
        Timer timer = new Timer();
        timer.schedule(new ClearOrder(), 120000, 120000);

        boolean flag = true;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("The store is created and filled with random products.");
        while (flag) {
            System.out.println("To interact with the store use next commands: sort, topFive, order, quit:");
            String command = bufferedReader.readLine();

            switch (command) {
                case "sort":
                    System.out.print(store.getSortedStore(
                            ProductComparator.createComparator(XMLParser.getSortingMap())));
                    break;
                case "topFive":
                    System.out.println(store.getTopFiveByPrice());
                    break;
                case "order":
                    new CreateOrder().start();
                    break;
                case "quit":
                    timer.cancel();
                    flag = false;
                    break;
                default:
                    System.out.println("Command is not supported");
            }
        }

    }
}
