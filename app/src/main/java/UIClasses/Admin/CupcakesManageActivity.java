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

import Adapters.CupCakeClass;
import Adapters.Admin_SingleCupcakeRowAdapter;
import DB.DBController;

public class CupcakesManageActivity extends AppCompatActivity {

    ListView lstCupcakes;

    Button btnAddCupcake;

    List<CupCakeClass> cupcakeL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupcakes_manage);

        lstCupcakes = findViewById(R.id.lstCupcakes);
        btnAddCupcake = findViewById(R.id.btnAddCupcake);

        DBController dbController = new DBController(getApplicationContext());
        dbController.OpenDB();

        cupcakeL = dbController.getAllCupcakes();

        Admin_SingleCupcakeRowAdapter adapter = new Admin_SingleCupcakeRowAdapter(getApplicationContext(),R.layout.single_cupcake_row,cupcakeL);
        lstCupcakes.setAdapter(adapter);


        btnAddCupcake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CupcakesManageActivity.this, AddNewCupcakeActivity.class);
                startActivity(intent);

            }
        });


        lstCupcakes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CupCakeClass cupCakeClass = cupcakeL.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(CupcakesManageActivity.this);
                builder.setTitle(cupCakeClass.getCupcakeId());
                builder.setMessage(cupCakeClass.getCupcakeName());

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbController.deleteCupcakeItem(cupCakeClass.getCupcakeId());
                        Toast.makeText(CupcakesManageActivity.this, "CupCake Ites = "+cupCakeClass.getCupcakeName()+ " Successfully Deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), CupcakesManageActivity.class);
                        startActivity(intent);
                    }
                });

                builder.show();
            }
        });
    }

    public void btnCupcakeBackOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
        startActivity(intent);
    }
}