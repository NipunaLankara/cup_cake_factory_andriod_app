package UIClasses.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cupcake_factory_final.R;

import Adapters.CategoryClass;
import DB.DBController;

public class AddNewCategoryActivity extends AppCompatActivity {

    EditText txtNewCategoryId,txtNewCategoryName;

    Button btnFinalAddNewCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_category);

        DBController dbController = new DBController(getApplicationContext());
        dbController.OpenDB();

        txtNewCategoryId = findViewById(R.id.txtNewCategoryId);
        txtNewCategoryName = findViewById(R.id.txtNewCategoryName);
        btnFinalAddNewCategory = findViewById(R.id.btnFinalAddNewCategory);

        btnFinalAddNewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryId = txtNewCategoryId.getText().toString();
                String categoryName = txtNewCategoryName.getText().toString();

                if (categoryId.isEmpty()) {
                    txtNewCategoryId.requestFocus();
                    Toast.makeText(getApplicationContext(),"Field's cant be blank", Toast.LENGTH_LONG).show();

                } else if (categoryName.isEmpty()) {
                    txtNewCategoryName.requestFocus();
                    Toast.makeText(getApplicationContext(), "Field's cant be blank", Toast.LENGTH_LONG).show();

                } else {
                    CategoryClass categoryClass = new CategoryClass(categoryId,categoryName);

                    if (dbController.addNewCategory(categoryClass)) {
                        Toast.makeText(AddNewCategoryActivity.this, "New Category Add", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), CategoryManageActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Category creation Failed", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    public void btnAddNewCatBackOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), CategoryManageActivity.class);
        startActivity(intent);
    }
}