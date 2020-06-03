package by.gsu.menu;

import by.gsu.utils.Container;
import by.gsu.utils.ScannerWrapper;

public class DeleteMenuItem<T> implements MenuItem<T> {

    private Container<T> container;
    private ScannerWrapper sc = new ScannerWrapper();


    public DeleteMenuItem(Container<T> container) {
        this.container = container;
    }

    @Override
    public int getOrder() {
        return 3;
    }

    @Override
    public String getTitle() {
        return "Delete element";
    }

    @Override
    public void execute() {
        System.out.println("Input index");
        int choice = sc.nextInt(0, container.size());

        container.delete(choice);
    }
}
