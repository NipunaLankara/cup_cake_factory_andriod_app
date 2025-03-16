package UIClasses.Member;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cupcake_factory_final.R;

import java.util.ArrayList;
import java.util.List;

import Adapters.Admin_SingleCupcakeRowAdapter;
import Adapters.CupCakeClass;
import DB.DBController;

public class ViewCupcakeItemActivity extends AppCompatActivity {

    ListView lst_M_CupcakesItem;

    List<CupCakeClass> cupCakeClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cupcake_item);

        lst_M_CupcakesItem = findViewById(R.id.lst_M_CupcakesItem);

        String categoryId = getIntent().getStringExtra("categoryId");
        String userId = getIntent().getStringExtra("userId");

        DBController dbController = new DBController(getApplicationContext());
        dbController.OpenDB();

        cupCakeClasses = dbController.searchCupcakesItem(categoryId);

        Admin_SingleCupcakeRowAdapter adapter = new Admin_SingleCupcakeRowAdapter(getApplicationContext(),R.layout.single_cupcake_row,cupCakeClasses);
        lst_M_CupcakesItem.setAdapter(adapter);

        lst_M_CupcakesItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CupCakeClass cupCakeClass = cupCakeClasses.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewCupcakeItemActivity.this);
                builder.setTitle(cupCakeClass.getCupcakeId());
                builder.setMessage(cupCakeClass.getCupcakeName());

                builder.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), OrderCupcakeActivity.class);
                        intent.putExtra("cupcakeName",cupCakeClass.getCupcakeName());
                        intent.putExtra("cupcakeId",cupCakeClass.getCupcakeId());
                        intent.putExtra("price",String.valueOf(cupCakeClass.getPrice()));
                        intent.putExtra("userId",userId);

                        startActivity(intent);

                    }
                });

                builder.show();

            }
        });


    }

    public void btn_M_Cup_BackOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MemberActivity.class);
        startActivity(intent);

    }
}