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

public class LoginApplication extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;

    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_application);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);


        // Press Done on keyboard
        edtPassword.setOnEditorActionListener(
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


        // LOGIN BUTTON
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                validate();
            }
        });


        // REGISTER BUTTON
        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent openRegister =
                        new Intent(
                                LoginApplication.this,
                                RegisterApplication.class
                        );

                startActivity(openRegister);
            }
        });
    }


    // If user is already logged in,
    // skip Login screen
    @Override
    protected void onStart() {
        super.onStart();

        if (Preferences.getStatusLogin(getBaseContext())) {

            startActivity(
                    new Intent(
                            LoginApplication.this,
                            MainActivity.class
                    )
            );

            finish();
        }
    }


    // =========================
    // VALIDATION
    // =========================

    private void validate() {

        edtUsername.setError(null);
        edtPassword.setError(null);

        View focus = null;
        boolean cancel = false;

        String username =
                edtUsername.getText().toString();

        String password =
                edtPassword.getText().toString();


        // Username
        if (TextUtils.isEmpty(username)) {

            edtUsername.setError("Username is required");
            focus = edtUsername;
            cancel = true;

        } else if (!userExists(username)) {

            edtUsername.setError("Username is not found");
            focus = edtUsername;
            cancel = true;
        }


        // Password
        if (TextUtils.isEmpty(password)) {

            edtPassword.setError("Password is required");
            focus = edtPassword;
            cancel = true;

        } else if (!passwordMatches(password)) {

            edtPassword.setError("Password does not match");
            focus = edtPassword;
            cancel = true;
        }


        if (cancel) {

            if (focus != null) {
                focus.requestFocus();
            }

        } else {

            goLogin();
        }
    }


    // =========================
    // LOGIN SUCCESS
    // =========================

    private void goLogin() {

        Preferences.setUserLogin(
                getBaseContext(),
                Preferences.getUserName(getBaseContext())
        );

        Preferences.setStatusLogin(
                getBaseContext(),
                true
        );

        Intent intent =
                new Intent(
                        LoginApplication.this,
                        MainActivity.class
                );

        startActivity(intent);
        finish();
    }


    // =========================
    // CHECK USERNAME
    // =========================

    private boolean userExists(String username) {

        return username.equals(
                Preferences.getUserName(getBaseContext())
        );
    }


    // =========================
    // CHECK PASSWORD
    // =========================

    private boolean passwordMatches(String password) {

        return password.equals(
                Preferences.getPassword(getBaseContext())
        );
    }
}