package vn.edu.ntu.IController;

import java.util.ArrayList;

import vn.edu.ntu.model.Product;

public interface IController {
    public ArrayList<Product> listProduct();
    public ArrayList<Product> shoppingList();
    public boolean check(Product p);
    public void clearShoppingCart();
}
