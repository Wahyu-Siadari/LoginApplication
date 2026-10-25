package com.example.loginapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtAddress;
    TextView txtPhone;

    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Connect TextViews
        txtName = findViewById(R.id.txtName);
        txtAddress = findViewById(R.id.txtAddress);
        txtPhone = findViewById(R.id.txtPhone);

        // Connect Button
        btnLogout = findViewById(R.id.btnLogout);


        // Display saved user data
        txtName.setText(
                "Username : " +
                        Preferences.getUserName(getBaseContext())
        );

        txtAddress.setText(
                "Address : " +
                        Preferences.getAddress(getBaseContext())
        );

        txtPhone.setText(
                "Phone : " +
                        Preferences.getPhone(getBaseContext())
        );


        // Logout Button
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Preferences.clearLoggedUser(
                        getBaseContext()
                );

                Intent intent =
                        new Intent(
                                MainActivity.this,
                                LoginApplication.class
                        );

                startActivity(intent);

                finish();
            }
        });
    }
}