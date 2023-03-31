package by.issoft.store;

import by.issoft.domain.Product;
import by.issoft.domain.Category;
import by.issoft.store.db.repositories.CategoryRepository;
import by.issoft.store.db.repositories.ProductRepository;
import com.github.javafaker.Faker;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class RandomStorePopulator {
    private Faker faker;
    private Reflections reflections;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public RandomStorePopulator(){
        faker = new Faker();
        reflections = new Reflections("by.issoft.domain.categories");
        categoryRepository = new CategoryRepository();
        productRepository = new ProductRepository();
    }

    public void fillStore() {
        Set<Class<? extends Category>> categories = reflections.getSubTypesOf(Category.class);
        for (Class<? extends Category> category : categories
        ) {
            try {
                Category temp = category.getDeclaredConstructor().newInstance();
                categoryRepository.addCategory(temp.getName());
                addProductsToCategory(temp, faker.number().numberBetween(2, 6));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    private Category addProductsToCategory(Category category, int num){
        for(int i = 0; i < num; i++){
            productRepository.addProduct(new Product(generateProductName(category.getName()),
                    faker.number().numberBetween(1,10),
                    generateProductPrice(category.getName())), category);
        }
        return category;
    }

    private String generateProductName(String category) {
        switch (category) {
            case "bike":
                return faker.company().name();
            case "milk":
                return faker.food().ingredient();
            case "phone":
                return faker.app().name();
            default:
                return "Invalid type of category";
        }
    }

    private Integer generateProductPrice(String category) {
        switch (category) {
            case "bike":
                return faker.number().numberBetween(1, 30_000);
            case "milk":
                return faker.number().numberBetween(1, 100);
            case "phone":
                return faker.number().numberBetween(1, 5000);
            default:
                return faker.number().numberBetween(1, 35_000);
        }
    }
}
