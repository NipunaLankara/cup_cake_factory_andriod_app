package UIClasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cupcake_factory_final.R;

import java.util.ArrayList;

import Adapters.UserClass;
import DB.DBController;
import UIClasses.Admin.AdminActivity;
import UIClasses.Member.MemberHomeActivity;

public class LoginActivity extends AppCompatActivity {

    EditText txt_L_UserId,txt_L_Password;

    TextView txtCreateNewAccount;

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_L_UserId = findViewById(R.id.txt_L_UserId);
        txt_L_Password = findViewById(R.id.txt_L_Password);

        btnLogin = findViewById(R.id.btnLogin);
        txtCreateNewAccount = findViewById(R.id.txtCreateNewAccount);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userId = txt_L_UserId.getText().toString();
                String password = txt_L_Password.getText().toString();

                if (userId.isEmpty()) {
                    txt_L_UserId.requestFocus();
                    Toast.makeText(LoginActivity.this, "Field's cant be blank", Toast.LENGTH_SHORT).show();

                } else if (password.isEmpty()) {
                    txt_L_Password.requestFocus();
                    Toast.makeText(LoginActivity.this, "Field's cant be blank", Toast.LENGTH_SHORT).show();

                } else {
                    DBController dbController = new DBController(getApplicationContext());
                    dbController.OpenDB();

                    ArrayList<UserClass> userDetails = dbController.loginDetails(userId, password);

                    if (userDetails.size()!= 0) {
                        UserClass userClass = userDetails.get(0);
                        String userType = userClass.getUserType();

                        if (userType.equals("Admin")) {
                            Toast.makeText(LoginActivity.this, "Welcome to Admin Interface", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginActivity.this, "Welcome to Member Interface", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MemberHomeActivity.class);
                            intent.putExtra("userId",userId);
                            startActivity(intent);
                        }

                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        txtCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateNewAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}