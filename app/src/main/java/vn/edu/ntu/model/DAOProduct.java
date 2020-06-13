package vn.edu.ntu.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface DAOProduct {
    @Insert
    public void insertProduct(Product...Products);

    @Update
    public void updateProduct(Product...products);

    @Delete
    public void deleteProduct(Product product);

    @Query("SELECT * FROM Product")
    List<Product> getListProduct();

    @Query("SELECT * FROM Product WHERE id = :id")
    public  Product getProductId(long id);

}
