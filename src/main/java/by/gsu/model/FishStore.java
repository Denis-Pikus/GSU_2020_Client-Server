package by.gsu.model;

import java.util.List;

public class FishStore extends GroceryStore<FishStore> {

    public FishStore() {
    }

    public FishStore(List<Product> products, int squareStore) {
        super(products, squareStore);
    }

    @Override
    public void sale() {
        System.out.println("Hello, here sell fishes!");
    }

}
