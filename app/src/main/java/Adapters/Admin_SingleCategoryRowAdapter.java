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

public class Admin_SingleCategoryRowAdapter extends ArrayAdapter <CategoryClass> {

    private  Context context;
    private  int resource;
    private List<CategoryClass> categoryL;


    public Admin_SingleCategoryRowAdapter(@NonNull Context context, int resource, List<CategoryClass> categoryL) {
        super(context, resource ,categoryL);
        this.context = context;
        this.resource = resource;
        this.categoryL = categoryL;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView txtCategoryId = row.findViewById(R.id.txtCategoryId);
        TextView txtCategoryName = row.findViewById(R.id.txtCategoryName);

        CategoryClass categoryClass = categoryL.get(position);

        txtCategoryId.setText(categoryClass.getCategoryId());
        txtCategoryName.setText(categoryClass.getCategoryName());

        return row;
    }
}

