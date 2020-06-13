package vn.edu.ntu.frament;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.ntu.IController.IController;
import vn.edu.ntu.model.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class Confirm extends Fragment {

    public Confirm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txtTinhTien;

        txtTinhTien = view.findViewById(R.id.txtTinhTien);

        IController controller = (IController) getActivity().getApplication();
        ArrayList<Product> products = controller.shoppingList();

        int k = 0;
        for (Product p:products){
            k += p.getPrice();
        }

        txtTinhTien.setText("Tổng tiền: "+Integer.toString(k)+" VND");
    }
}
