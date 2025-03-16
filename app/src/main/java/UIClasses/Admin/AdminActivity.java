package UIClasses.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cupcake_factory_final.R;

import UIClasses.LoginActivity;

public class AdminActivity extends AppCompatActivity {

    Button btnCallCategoryManage,btnCallCupcakesItemManage,btnCallViewOrders,btn_A_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnCallCategoryManage = findViewById(R.id.btnCallCategoryManage);
        btnCallCupcakesItemManage =findViewById(R.id.btnCallCupcakesItemManage);
        btnCallViewOrders = findViewById(R.id.btnCallViewOrders);
        btn_A_Back = findViewById(R.id.btn_A_Back);

        btnCallCategoryManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, CategoryManageActivity.class);
                startActivity(intent);

            }
        });

        btnCallCupcakesItemManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, CupcakesManageActivity.class);
                startActivity(intent);

            }
        });

        btnCallViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, ViewOrdersActivity.class);
                startActivity(intent);

            }
        });

        btn_A_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}