package UIClasses.Member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cupcake_factory_final.R;

import java.util.List;

import Adapters.Admin_SingleCategoryRowAdapter;
import Adapters.CategoryClass;
import DB.DBController;
import UIClasses.LoginActivity;

public class MemberActivity extends AppCompatActivity {

    ListView lst_M_Category;

    List<CategoryClass> categoryL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        lst_M_Category = findViewById(R.id.lst_M_Category);

        DBController dbController = new DBController(getApplicationContext());
        dbController.OpenDB();

        categoryL = dbController.getAllCategory();

        String userId = getIntent().getStringExtra("userId");


        Admin_SingleCategoryRowAdapter adapter = new Admin_SingleCategoryRowAdapter(getApplicationContext(),R.layout.admin_single__category_row,categoryL);
        lst_M_Category.setAdapter(adapter);

        lst_M_Category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CategoryClass categoryClass = categoryL.get(position);
                String categoryId = categoryClass.getCategoryId();

                Intent intent = new Intent(getApplicationContext(), ViewCupcakeItemActivity.class);
                intent.putExtra("categoryId",categoryId);
                intent.putExtra("userId",userId);
                startActivity(intent);

            }
        });

    }

    public void btn_Member_BacknClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MemberHomeActivity.class);
        startActivity(intent);
    }
}