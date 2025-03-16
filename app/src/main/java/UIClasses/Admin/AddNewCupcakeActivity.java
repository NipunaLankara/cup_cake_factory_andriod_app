package UIClasses.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cupcake_factory_final.R;

import java.util.Vector;

import Adapters.CupCakeClass;
import DB.DBController;

public class AddNewCupcakeActivity extends AppCompatActivity {

    EditText txtNewCupcakeId,txtNewCupcakeName,txtPrice,txtQuantity;

    Spinner sp_A_Category;

    Button btnFinalAddNewCupcake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_cupcake);

        txtNewCupcakeId = findViewById(R.id.txtNewCupcakeId);
        txtNewCupcakeName = findViewById(R.id.txtNewCupcakeName);
        txtPrice = findViewById(R.id.txtPrice);
        txtQuantity = findViewById(R.id.txtQuantity);

        sp_A_Category = findViewById(R.id.sp_A_Category);

        btnFinalAddNewCupcake = findViewById(R.id.btnFinalAddNewCupcake);

        DBController dbController = new DBController(getApplicationContext());
        dbController.OpenDB();

        Vector<String> categoryName = dbController.getCategoryName();

        ArrayAdapter arrayAdapter = new ArrayAdapter (getApplicationContext(),android.R.layout.simple_spinner_item,categoryName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_A_Category.setAdapter(arrayAdapter);

        btnFinalAddNewCupcake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cupcakeId = txtNewCupcakeId.getText().toString();
                String cupcakeName = txtNewCupcakeName.getText().toString();
                String price = txtPrice.getText().toString();
                String quantity = txtQuantity.getText().toString();
                String categoryName = sp_A_Category.getSelectedItem().toString();

                if (cupcakeId.isEmpty()) {
                    txtNewCupcakeId.requestFocus();
                    Toast.makeText(getApplicationContext(),"Field's cant be blank", Toast.LENGTH_LONG).show();

                } else if (cupcakeName.isEmpty()) {
                    txtNewCupcakeName.requestFocus();
                    Toast.makeText(getApplicationContext(),"Field's cant be blank", Toast.LENGTH_LONG).show();

                } else if (price.isEmpty()) {
                    txtPrice.requestFocus();
                    Toast.makeText(getApplicationContext(),"Field's cant be blank", Toast.LENGTH_LONG).show();

                } else if (quantity.isEmpty()) {
                    txtQuantity.requestFocus();
                    Toast.makeText(getApplicationContext(),"Field's cant be blank", Toast.LENGTH_LONG).show();

                } else {

                    String categoryId = dbController.getCategoryId(categoryName);

                    CupCakeClass cupCakeClass = new CupCakeClass(cupcakeId,cupcakeName,categoryId,Integer.parseInt(price),Integer.parseInt(quantity));

                    if (dbController.addNewCupcakeItem(cupCakeClass)) {
                        Toast.makeText(getApplicationContext(),"New CupCake Item Insert",Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(AddNewCupcakeActivity.this, CupcakesManageActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(),"Failed", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

    }

    public void btn_AddCupcake_BackOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), CupcakesManageActivity.class);
        startActivity(intent);
    }
}