package UIClasses.Member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.cupcake_factory_final.R;

import java.util.List;

import Adapters.Admin_SingleOrderRow;
import Adapters.OrderClass;
import DB.DBController;

public class ViewMemberOrdersActivity extends AppCompatActivity {

    ListView lstUserOrders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member_orders);

        lstUserOrders = findViewById(R.id.lstUserOrders);

        String userId = getIntent().getStringExtra("userId");

        DBController dbHelper = new DBController(getApplicationContext());
        dbHelper.OpenDB();

        List<OrderClass> orderClassList = dbHelper.viewUserOrder(userId);

        Admin_SingleOrderRow adapter = new Admin_SingleOrderRow(getApplicationContext(),R.layout.single_order_row,orderClassList);
        lstUserOrders.setAdapter(adapter);


    }

    public void btnBackOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MemberHomeActivity.class);
        startActivity(intent);

    }
}