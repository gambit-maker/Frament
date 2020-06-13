package vn.edu.ntu.IController;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.model.AppDatabase;
import vn.edu.ntu.model.DAOProduct;
import vn.edu.ntu.model.Product;

public class CartControllerDB implements IController {

    AppDatabase database;
    ArrayList<Product> shoppingList = new ArrayList<>();
    DAOProduct daoProduct;

    public CartControllerDB(Context context){
        database = Room.databaseBuilder(context,AppDatabase.class,"appdb").allowMainThreadQueries().build();
        daoProduct = database.getProductDAO();
    }

    @Override
    public ArrayList<Product> listProduct() {
        return (ArrayList<Product>) daoProduct.getListProduct();
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
        daoProduct.insertProduct(p);
    }
}
