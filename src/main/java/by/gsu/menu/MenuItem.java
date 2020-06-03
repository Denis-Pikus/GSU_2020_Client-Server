package by.gsu.menu;

public interface MenuItem<T> {

    int getOrder();

    String getTitle();

    void execute();
}
