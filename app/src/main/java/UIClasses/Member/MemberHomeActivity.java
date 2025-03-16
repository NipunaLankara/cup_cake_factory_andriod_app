package UIClasses.Member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cupcake_factory_final.R;

import UIClasses.LoginActivity;

public class MemberHomeActivity extends AppCompatActivity {

    Button btn_Call_BuyCake,btn_Call_ViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_home);

        btn_Call_BuyCake = findViewById(R.id.btn_Call_BuyCake);
        btn_Call_ViewOrder = findViewById(R.id.btn_Call_ViewOrder);

        String userId = getIntent().getStringExtra("userId");


        btn_Call_BuyCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MemberActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);

            }
        });

        btn_Call_ViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewMemberOrdersActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });


    }

    public void btnLogOutOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}