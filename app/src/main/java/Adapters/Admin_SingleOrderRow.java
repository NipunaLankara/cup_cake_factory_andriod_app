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

public class Admin_SingleOrderRow extends ArrayAdapter<OrderClass> {

    TextView txtViewOrderId,txtViewOrderCakeId,txtViewUserId,txtViewOrderQty,txtViewTotal;

    private Context context;
    private int resource;
    private List<OrderClass> orderClassList;
    public Admin_SingleOrderRow(@NonNull Context context, int resource,List<OrderClass> orderClassList) {
        super(context, resource,orderClassList);
        this.context = context;
        this.resource = resource;
        this.orderClassList = orderClassList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        txtViewOrderId = row.findViewById(R.id.txtViewOrderId);
        txtViewOrderCakeId = row.findViewById(R.id.txtViewOrderCakeId);
        txtViewUserId = row.findViewById(R.id.txtViewUserId);
        txtViewOrderQty = row.findViewById(R.id.txtViewOrderQty);
        txtViewTotal = row.findViewById(R.id.txtViewTotal);

        OrderClass orderClass = orderClassList.get(position);

        txtViewOrderId.setText(orderClass.getOrderId());
        txtViewOrderCakeId.setText(orderClass.getCupcakeId());
        txtViewUserId.setText(orderClass.getUserId());
        txtViewOrderQty.setText(String.valueOf(orderClass.getQuantity()));
        txtViewTotal.setText(String.valueOf(orderClass.getTotal()));

        return row;
    }
}
