package com.example.ivote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText emailEdt, passwordEdt;
    AccountDBHandler dbHandler;
    String loginMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEdt = findViewById(R.id.editTextTextEmailAddress);
        passwordEdt = findViewById(R.id.editTextTextPassword);

        dbHandler = new AccountDBHandler(MainActivity.this);
    }

    public void redirectToRegister(View widget) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    public void redirectToDisplayPolls(View widget) {
        String email = emailEdt.getText().toString();
        String password = passwordEdt.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter all fields.", Toast.LENGTH_SHORT).show();
        } else {
            loginMsg = dbHandler.loginToAccount(email, password);

            Toast.makeText(MainActivity.this, loginMsg, Toast.LENGTH_SHORT).show();

            emailEdt.setText("");
            passwordEdt.setText("");

            if (loginMsg == "Successfully logged in.") {
                startActivity(new Intent(this, DisplayPollsActivity.class));
            }

            if (loginMsg == "Admin successfully logged in.") {
                startActivity(new Intent(this, AdminFeedActivity.class));
            }
        }
    }
}