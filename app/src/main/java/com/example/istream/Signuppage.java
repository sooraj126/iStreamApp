package com.example.istream;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.room.Room;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Signuppage extends AppCompatActivity {

    EditText edtFullName, edtUsername, edtPassword, edtConfirmPassword;
    Button btnSignup;
    Databaseistream db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signuppage);

        edtFullName = findViewById(R.id.edtFullName);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnSignup = findViewById(R.id.btnSignup);


        db = Room.databaseBuilder(getApplicationContext(),
                        Databaseistream.class, "Databaseistream")
                .allowMainThreadQueries()
                .build();


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullName = edtFullName.getText().toString().trim();
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confirmPassword = edtConfirmPassword.getText().toString().trim();

                if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(Signuppage.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(Signuppage.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    user userpresent = db.userDao().getUserByUsername(username);

                    if (userpresent != null) {
                        Toast.makeText(Signuppage.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        user user = new user();
                        user.fullName = fullName;
                        user.username = username;
                        user.password = password;

                        db.userDao().insertUser(user);

//                        user check = db.userDao().getUserByUsername(username);

//                         System.out.println("user" + checkUser.username + " / " + checkUser.password);


                        Toast.makeText(Signuppage.this, "Signup successfully completed", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }



}