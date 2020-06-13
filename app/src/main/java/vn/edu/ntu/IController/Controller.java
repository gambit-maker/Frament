package vn.edu.ntu.IController;

import android.app.Application;

import java.util.ArrayList;

import vn.edu.ntu.model.Product;

public class Controller extends Application implements IController {

    ArrayList<Product> listProduct = new ArrayList<>();
    ArrayList<Product> shoppingList = new ArrayList<>();
    public Controller() {
        listProduct.add(new Product("Xoài", 69000,"Xoài Xoài"));
        listProduct.add(new Product("Khoai tây", 39000,"Khoai tây đà lạt"));
        listProduct.add(new Product("Hoa hồng", 50000,"Hoa hồng tươi"));
        listProduct.add(new Product("Cần tây", 12000,"Cần tây giống tốt"));
        listProduct.add(new Product("Cà chua", 5000,"Khá chua"));
        listProduct.add(new Product("Hoa hướng dương", 50000,"có màu vàng"));
    }

    @Override
    public ArrayList<Product> listProduct() {
        return listProduct;
    }

    @Override
    public ArrayList<Product> shoppingList() {
        return shoppingList;
    }

    @Override
    public boolean check(Product p) {
        if (shoppingList.contains(p))
            return false;
        else{
            shoppingList.add(p);
            return true;
        }
    }


    @Override
    public void clearShoppingCart() {
        shoppingList.clear();
    }

    @Override
    public void addProduct(Product p) {
        listProduct.add(p);
    }
}
