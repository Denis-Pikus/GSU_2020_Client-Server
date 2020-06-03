package by.gsu;

import by.gsu.menu.*;
import by.gsu.model.GroceryStore;
import by.gsu.model.GroceryStoreFactory;
import by.gsu.network.NetworkClientContainer;
import by.gsu.utils.Container;
import by.gsu.utils.Factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenericMenuMain {

    public static void main(String[] args) throws IOException {

        Factory<GroceryStore<?>> factory = new GroceryStoreFactory();
        Container<GroceryStore<?>> container = new NetworkClientContainer<>("localhost", 8080);

        List<MenuItem<GroceryStore<?>>> items = new ArrayList<>();
        items.add(new AddMenuItem<>(container, factory));
        items.add(new DeleteMenuItem<>(container));
        items.add(new PrintAllMenuItem<>(container));
        items.add(new UpdateMenuItem<>(container, factory));
        items.add(new SizeMenuItem<>(container));
        TopLevelMenu<GroceryStore<?>> menu1 = new TopLevelMenu<>(items, "transport menu", 0);

        menu1.execute();
    }
}
