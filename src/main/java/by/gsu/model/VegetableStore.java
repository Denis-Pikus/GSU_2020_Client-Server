package by.gsu.model;

import java.util.List;

public class VegetableStore extends GroceryStore<VegetableStore> {

    public VegetableStore(){}

    public VegetableStore(List<Product> products, int squareStore) {
        super(products, squareStore);
    }

    @Override
    public void sale() {
        System.out.println("Hello, here sell vegetables!");
    }
}
