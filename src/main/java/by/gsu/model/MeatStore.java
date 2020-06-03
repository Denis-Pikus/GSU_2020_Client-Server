package by.gsu.model;

import java.util.List;

public class MeatStore extends GroceryStore<MeatStore> {

    public MeatStore() {}

    public MeatStore(List<Product> products, int squareStore) {
        super(products, squareStore);
    }

    @Override
    public void sale() {
        System.out.println("Hello, here sell meats!");
    }

}
