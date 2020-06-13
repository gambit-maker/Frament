package vn.edu.ntu.frament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vn.edu.ntu.IController.IController;
import vn.edu.ntu.model.Product;

public class DSMatHang extends Fragment {

    RecyclerView rvListMH;
    FloatingActionButton fab;
    NavController controller;
    IController iController;
    ArrayList<Product> listproduct;
    ProductAdapter productAdapter;
    //set menu_cart (mnu)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        View view = inflater.inflate(R.layout.fragment_first,container,false);
        rvListMH = view.findViewById(R.id.rvListMH);
        fab = view.findViewById(R.id.fab);

        // Inflate the layout for this fragment
//        iController = (IController) getActivity().getApplication();
        iController = ((MainActivity)getActivity()).cartController;
        listproduct = iController.listProduct();
        productAdapter = new ProductAdapter(listproduct);
        rvListMH.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvListMH.setAdapter(productAdapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.mnu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.mnuCart){
            CallShoppingFragment();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CallShoppingFragment() {
        NavController controller = NavHostFragment.findNavController(DSMatHang.this);
        controller.navigate(R.id.action_DSMatHang_to_shoppingCart);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iController = ((MainActivity)getActivity()).cartController;
        controller = NavHostFragment.findNavController(DSMatHang.this);
        ((MainActivity)getActivity()).controller = controller;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_DSMatHang_to_product);
            }
        });

//        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(DSMatHang.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtName, txtPrice, txtDesc;
        ImageView imgShoppingCart;
        Product p;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            imgShoppingCart = itemView.findViewById(R.id.imgShoppingCart);

            imgShoppingCart.setOnClickListener(this);
        }
        public void bind(Product p){
            this.p = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());
        }

        @Override
        public void onClick(View v) {
//            iController = (IController) getActivity().getApplication();
            iController = ((MainActivity)getActivity()).cartController;
            if (iController.check(p)){
                Toast.makeText(getActivity(), "Đã thêm " + p.getName() +" vào giỏ", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getActivity(), "Đã có " +p.getName() +" trong giở", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{

        ArrayList<Product> listProduct;

        public ProductAdapter(ArrayList<Product> listproduct) {
            this.listProduct = listproduct;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.product_item,parent,false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(listProduct.get(position));
        }

        @Override
        public int getItemCount() {
            return listProduct.size();
        }
    }

}
