package UIClasses.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cupcake_factory_final.R;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.List;

import Adapters.Admin_SingleOrderRow;
import Adapters.OrderClass;
import DB.DBController;

public class ViewOrdersActivity extends AppCompatActivity {

    ListView listOrderDetails;
    Button btnBackOrderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        listOrderDetails = findViewById(R.id.listOrderDetails);
        btnBackOrderList = findViewById(R.id.btnBackOrderList);

        DBController dbController = new DBController(getApplicationContext());
        dbController.OpenDB();

        List<OrderClass> allOrders = dbController.getAllOrders();

        Admin_SingleOrderRow adapter = new Admin_SingleOrderRow(getApplicationContext(),R.layout.single_order_row,allOrders);
        listOrderDetails.setAdapter(adapter);


        listOrderDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderClass orderClass = allOrders.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewOrdersActivity.this);
                builder.setTitle(orderClass.getOrderId());
                builder.setMessage(orderClass.getUserId());

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbController.deleteOrder(orderClass.getOrderId());
                        Toast.makeText(ViewOrdersActivity.this, "Order Delete Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ViewOrdersActivity.class);
                        startActivity(intent);
                    }
                });

                builder.show();

            }
        });


        btnBackOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
            }
        });
    }
}