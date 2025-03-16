package UIClasses;

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

import Adapters.UserClass;
import DB.DBController;
import UIClasses.LoginActivity;

public class CreateNewAccountActivity extends AppCompatActivity {

    EditText txt_R_UserId,txt_R_UserName,txt_R_Password,txt_R_ConfirmPassword;

    Button btnRegister,btn_R_Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        txt_R_UserId = findViewById(R.id.txt_R_UserId);
        txt_R_UserName = findViewById(R.id.txt_R_UserName);
        txt_R_Password = findViewById(R.id.txt_R_Password);
        txt_R_ConfirmPassword = findViewById(R.id.txt_R_ConfirmPassword);

        btnRegister = findViewById(R.id.btnRegister);
        btn_R_Back = findViewById(R.id.btn_R_Back);

        DBController dbController = new DBController(getApplicationContext());
        dbController.OpenDB();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userId = txt_R_UserId.getText().toString();
                String userName = txt_R_UserName.getText().toString();
                String password = txt_R_Password.getText().toString();
                String confirmPassword = txt_R_ConfirmPassword.getText().toString();

                String userTypeAdmin = "Admin";
                String userType = "Member";

                if (userId.isEmpty()) {
                    txt_R_UserId.requestFocus();
                    Toast.makeText(getApplicationContext(),"Field's cant be blank", Toast.LENGTH_LONG).show();

                } else if (userName.isEmpty()) {
                    txt_R_UserName.requestFocus();
                    Toast.makeText(getApplicationContext(),"Field's cant be blank", Toast.LENGTH_LONG).show();

                } else if (password.isEmpty()) {
                    txt_R_Password.requestFocus();
                    Toast.makeText(getApplicationContext(),"Field's cant be blank", Toast.LENGTH_LONG).show();

                } else if (confirmPassword.isEmpty()) {
                    txt_R_ConfirmPassword.requestFocus();
                    Toast.makeText(getApplicationContext(),"Field's cant be blank", Toast.LENGTH_LONG).show();

                } else if (password.length()<3) {
                    txt_R_Password.requestFocus();
                    Toast.makeText(getApplicationContext(),"Password must have more than 3 characters",Toast.LENGTH_LONG).show();

                } else if (!password.equals(confirmPassword)) {
                    txt_R_Password.requestFocus();

                    Toast.makeText(getApplicationContext(), "Password and confirm password should match Login",
                            Toast.LENGTH_LONG).show();


                } else {
                    UserClass userClass = new UserClass(userId,userName,password,userType);

                    if(dbController.createNewUser(userClass)) {
                        Toast.makeText(getApplicationContext(),
                                "User created", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(),
                                "User creation Failed", Toast.LENGTH_LONG).show();

                    }

                }

            }
        });


    }

    public void btn_R_BackOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}