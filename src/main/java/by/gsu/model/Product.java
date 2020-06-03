package by.gsu.model;
//Класс описывающий продукты продаваемые в магазине

import java.io.Serializable;
import java.util.Objects;

public class Product implements Comparable<Product>, Serializable {
    private int price;
    private String productName;

    public Product(){    }

    public Product(String productName, int price) {
        this.price = price;
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                Objects.equals(getProductName(), product.getProductName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getProductName());
    }

    @Override
    public String toString() {
        return productName + ":" + price;
    }

    @Override
    public int compareTo(Product o) {
        return Integer.compare(price, o.getPrice());
    }

    public void show(){
        System.out.println("Product " + productName + " price " + price);
    }
}
