package vn.edu.ntu.frament;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.ntu.IController.IController;
import vn.edu.ntu.model.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText edtName, edtPrice, edtDesc;
        Button btnThem, btnClear, btnSave, btnRead;

        edtName = view.findViewById(R.id.edtName);
        edtPrice = view.findViewById(R.id.edtPrice);
        edtDesc = view.findViewById(R.id.edtDesc);

        btnThem = view.findViewById(R.id.btnThem);
        btnClear = view.findViewById(R.id.btnClear);
        btnSave = view.findViewById(R.id.btnSave);
        btnRead = view.findViewById(R.id.btnRead);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MySharePref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name",edtName.getText().toString());
                editor.putString("money",edtPrice.getText().toString());
                editor.putString("desc",edtDesc.getText().toString());
                editor.commit();
                Toast.makeText(getActivity(), "Form Saved", Toast.LENGTH_SHORT).show();
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MySharePref", Context.MODE_PRIVATE);
                if (preferences != null){
                    edtName.setText(preferences.getString("name","No name"));
                    edtPrice.setText(preferences.getString("money","free"));
                    edtDesc.setText(preferences.getString("desc","no description"));
                    Toast.makeText(getActivity(), "Read Form", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtDesc.setText("");
                edtName.setText("");
                edtPrice.setText("");;
                Toast.makeText(getActivity(), "Form Cleared", Toast.LENGTH_SHORT).show();
            }
        });




        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IController controller = (IController) getActivity().getApplication();

                Product p = new Product(edtName.getText().toString()
                ,new Integer(edtPrice.getText().toString())
                ,edtDesc.getText().toString());
                controller.listProduct().add(p);
                Toast.makeText(getActivity(), "Đã thêm", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
