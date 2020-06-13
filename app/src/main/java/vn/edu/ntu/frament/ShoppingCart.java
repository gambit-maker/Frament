package vn.edu.ntu.frament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

import vn.edu.ntu.IController.IController;
import vn.edu.ntu.model.Product;

public class ShoppingCart extends Fragment {

    TextView txtGH;
    Button btn1,btn2;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtGH = view.findViewById(R.id.txtGH);
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyen layout shopping cart qua layout confirm
                NavHostFragment.findNavController(ShoppingCart.this)
                        .navigate(R.id.action_shoppingCart_to_confirm);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IController controller = (IController) getActivity().getApplication();
                controller.clearShoppingCart();
                txtGH.setText("");
            }
        });

        ViewCartInfo();
    }

    private void ViewCartInfo() {
//        IController controller = (IController) getActivity().getApplication();
        IController controller = ((MainActivity)getActivity()).cartController;
        ArrayList<Product> listProduct = controller.shoppingList();

        StringBuilder builder = new StringBuilder();
        for (Product p:listProduct){
            builder.append(p.getName()+"\t\t"+p.getPrice()+" VND\n");
        }
        txtGH.setText(builder.toString());
    }
}
