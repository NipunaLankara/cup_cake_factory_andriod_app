package UIClasses.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cupcake_factory_final.R;

import java.util.List;


import Adapters.CategoryClass;
import Adapters.Admin_SingleCategoryRowAdapter;
import DB.DBController;

public class CategoryManageActivity extends AppCompatActivity {

    ListView lstCategory;

    Button btnAddCategory;

    List<CategoryClass> categoryL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_manage);

        lstCategory = findViewById(R.id.lstCategory);
        btnAddCategory = findViewById(R.id.btnAddCategory);

        DBController dbController = new DBController(getApplicationContext());
        dbController.OpenDB();

        categoryL = dbController.getAllCategory();

        Admin_SingleCategoryRowAdapter adapter = new Admin_SingleCategoryRowAdapter(getApplicationContext(),R.layout.admin_single__category_row,categoryL);
        lstCategory.setAdapter(adapter);


        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddNewCategoryActivity.class);
                startActivity(intent);

            }
        });

        lstCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CategoryClass categoryClass = categoryL.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(CategoryManageActivity.this);
                builder.setTitle(categoryClass.getCategoryId());
                builder.setMessage(categoryClass.getCategoryName());

                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(CategoryManageActivity.this, EditCategoryActivity.class);
                        intent.putExtra("id",categoryClass.getCategoryId());
                        startActivity(intent);


                    }

                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dbController.deleteCategory(categoryClass.getCategoryId());
                        Toast.makeText(CategoryManageActivity.this, "Category "+ categoryClass.getCategoryName()+" is Successfully Deleted", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CategoryManageActivity.this, CategoryManageActivity.class);
                        startActivity(intent);

                    }
                });
                builder.show();
            }
        });
    }
    public void btnCategoryManageBackOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
        startActivity(intent);
    }
}