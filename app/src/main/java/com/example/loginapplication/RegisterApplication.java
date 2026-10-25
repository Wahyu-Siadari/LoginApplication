package com.example.loginapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterApplication extends AppCompatActivity {

    private Button button3;

    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private EditText edtAddress;
    private EditText edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_application);

        // Connect EditText
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);

        // Connect Button
        button3 = findViewById(R.id.button3);

        // When user presses Done on Phone Number
        edtPhone.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(
                            TextView textView,
                            int actionId,
                            KeyEvent keyEvent) {

                        if (actionId == EditorInfo.IME_ACTION_DONE
                                || actionId == EditorInfo.IME_NULL) {

                            validate();
                            return true;
                        }

                        return false;
                    }
                });


        // Sign Up Button
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }


    // =========================
    // VALIDATION
    // =========================

    private void validate() {

        edtUsername.setError(null);
        edtPassword.setError(null);
        edtConfirmPassword.setError(null);
        edtAddress.setError(null);
        edtPhone.setError(null);

        View focus = null;
        boolean cancel = false;

        String username =
                edtUsername.getText().toString();

        String password =
                edtPassword.getText().toString();

        String confirmPassword =
                edtConfirmPassword.getText().toString();

        String address =
                edtAddress.getText().toString();

        String phone =
                edtPhone.getText().toString();


        // Username validation
        if (TextUtils.isEmpty(username)) {

            edtUsername.setError("Username is required");
            focus = edtUsername;
            cancel = true;

        } else if (userExists(username)) {

            edtUsername.setError("Username already exists");
            focus = edtUsername;
            cancel = true;
        }


        // Password validation
        if (TextUtils.isEmpty(password)) {

            edtPassword.setError("Password is required");
            focus = edtPassword;
            cancel = true;

        } else if (!passwordMatches(password, confirmPassword)) {

            edtConfirmPassword.setError("Password does not match");
            focus = edtConfirmPassword;
            cancel = true;
        }


        // Address validation
        if (TextUtils.isEmpty(address)) {

            edtAddress.setError("Address is required");
            focus = edtAddress;
            cancel = true;
        }


        // Phone validation
        if (TextUtils.isEmpty(phone)) {

            edtPhone.setError("Phone number is required");
            focus = edtPhone;
            cancel = true;
        }


        if (cancel) {

            if (focus != null) {
                focus.requestFocus();
            }

        } else {

            // Save Practice 1 data
            Preferences.setUserName(
                    getBaseContext(),
                    username
            );

            Preferences.setPassword(
                    getBaseContext(),
                    password
            );


            // Save Practice 2 data
            Preferences.setAddress(
                    getBaseContext(),
                    address
            );

            Preferences.setPhone(
                    getBaseContext(),
                    phone
            );


            // Go to Login page
            Intent intent =
                    new Intent(
                            RegisterApplication.this,
                            LoginApplication.class
                    );

            startActivity(intent);
            finish();
        }
    }


    // =========================
    // CHECK USERNAME
    // =========================

    private boolean userExists(String user) {

        String savedUser =
                Preferences.getUserName(
                        getBaseContext()
                );

        if (TextUtils.isEmpty(savedUser)) {
            return false;
        }

        return user.equals(savedUser);
    }


    // =========================
    // CHECK PASSWORD
    // =========================

    private boolean passwordMatches(
            String password,
            String confirmPassword) {

        return password.equals(confirmPassword);
    }
}