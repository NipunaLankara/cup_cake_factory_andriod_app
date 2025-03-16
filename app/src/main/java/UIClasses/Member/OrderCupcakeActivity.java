package UIClasses.Member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cupcake_factory_final.R;

import Adapters.OrderClass;
import DB.DBController;

public class OrderCupcakeActivity extends AppCompatActivity {

    EditText txtOrderId,txtBuyQty;

    Button btn_Buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cupcake);

        DBController dbController = new DBController(getApplicationContext());
        dbController.OpenDB();

        txtOrderId = findViewById(R.id.txtOrderId);
        txtBuyQty = findViewById(R.id.txtBuyQty);

        btn_Buy = findViewById(R.id.btn_Buy);

        String cupcakeName = getIntent().getStringExtra("cupcakeName");
        String cupcakeId = getIntent().getStringExtra("cupcakeId");
        String userId = getIntent().getStringExtra("userId");
        String price = getIntent().getStringExtra("price");

        int intPrice = Integer.parseInt(price);


        btn_Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtOrderId.getText().toString().isEmpty()) {
                    txtOrderId.requestFocus();
                    Toast.makeText(getApplicationContext(),"Input Order Id", Toast.LENGTH_LONG).show();

                } else if (txtBuyQty.getText().toString().isEmpty()) {
                    txtBuyQty.requestFocus();
                    Toast.makeText(getApplicationContext(),"Enter How many CupCakes you want", Toast.LENGTH_LONG).show();

                } else {
                    String orderId = txtOrderId.getText().toString();
                    String buyQty = txtBuyQty.getText().toString();

                    int ibuyQty = Integer.parseInt(buyQty);

                    int cupcakeQuantity = dbController.getCupcakeQuantity(cupcakeName);

                    if (ibuyQty<cupcakeQuantity) {
                        dbController.buyCupcake(cupcakeId,ibuyQty);

                        int total = ibuyQty * intPrice;

                        OrderClass orderClass = new OrderClass(orderId,cupcakeId,userId,ibuyQty,total);

                        if (dbController.addOrderDetails(orderClass)) {

                            Toast.makeText(OrderCupcakeActivity.this, "Buy :" + cupcakeName+ " Total is "+total , Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MemberActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(),"Error Buying Product", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(OrderCupcakeActivity.this, "Not enough CupCakes in stock", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    public void btn_Buy_BackOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewCupcakeItemActivity.class);
        startActivity(intent);
    }
}