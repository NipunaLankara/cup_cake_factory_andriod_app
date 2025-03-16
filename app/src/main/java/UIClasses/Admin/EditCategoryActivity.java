package UIClasses.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cupcake_factory_final.R;

import Adapters.CategoryClass;
import DB.DBController;

public class EditCategoryActivity extends AppCompatActivity {

    EditText txtEditCategory;

    TextView txtView_C_id;

    Button btn_Edit_C_Update,btn_Edit_C_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        DBController dbController = new DBController(getApplicationContext());
        dbController.OpenDB();

        txtEditCategory= findViewById(R.id.txtEditCategory);
        txtView_C_id = findViewById(R.id.txtView_C_id);

        btn_Edit_C_Update = findViewById(R.id.btn_Edit_C_Update);
        btn_Edit_C_Back = findViewById(R.id.btn_Edit_C_Back);

        String id = getIntent().getStringExtra("id");

        CategoryClass singleCategory = dbController.getSingleCategory(id);

        txtView_C_id.setText(singleCategory.getCategoryId());
        txtEditCategory.setText(singleCategory.getCategoryName());

        btn_Edit_C_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtEditCategory.getText().toString().isEmpty()) {
                    Toast.makeText(EditCategoryActivity.this, "Fields Can not be empty", Toast.LENGTH_SHORT).show();

                } else {
                    String editText = txtEditCategory.getText().toString();

                    CategoryClass categoryClass = new CategoryClass(id,editText);

                    int status = dbController.updateSingleCategory(categoryClass);

                    Toast.makeText(EditCategoryActivity.this, "Category Id "+categoryClass.getCategoryId()+ " Successfully Edited", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(EditCategoryActivity.this, CategoryManageActivity.class);
                    startActivity(intent);
                }

            }
        });

        btn_Edit_C_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditCategoryActivity.this, CategoryManageActivity.class);
                startActivity(intent);

            }
        });

    }


}