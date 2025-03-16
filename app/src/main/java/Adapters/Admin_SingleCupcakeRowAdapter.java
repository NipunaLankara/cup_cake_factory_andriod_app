package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cupcake_factory_final.R;

import java.util.List;

public class Admin_SingleCupcakeRowAdapter extends ArrayAdapter<CupCakeClass> {

    TextView txtViewCupcakeId,txtViewCupcakeName,txtViewCategoryId,txtViewPrice,txtViewQuantity;
    private Context context;
    private int resource;
    private List<CupCakeClass> cupcakeL;


    public Admin_SingleCupcakeRowAdapter(@NonNull Context context, int resource, List<CupCakeClass> cupcakeL) {
        super(context, resource, cupcakeL);
        this.context = context;
        this.resource = resource;
        this.cupcakeL = cupcakeL;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        txtViewCupcakeId = row.findViewById(R.id.txtViewCupcakeId);
        txtViewCupcakeName = row.findViewById(R.id.txtViewCupcakeName);
        txtViewCategoryId = row.findViewById(R.id.txtViewCategoryId);
        txtViewPrice = row.findViewById(R.id.txtViewPrice);
        txtViewQuantity = row.findViewById(R.id.txtViewQuantity);

        CupCakeClass cupCakeClass = cupcakeL.get(position);

        txtViewCupcakeId.setText(cupCakeClass.getCupcakeId());
        txtViewCupcakeName.setText(cupCakeClass.getCupcakeName());
        txtViewCategoryId.setText(cupCakeClass.getCategoryId());
        txtViewPrice.setText(String.valueOf(cupCakeClass.getPrice()));
        txtViewQuantity.setText(String.valueOf(cupCakeClass.getQuantity()));

        return row;
    }
}
