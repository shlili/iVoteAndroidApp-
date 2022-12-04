package com.example.ivote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    EditText nameEdt, surnameEdt, emailEdt, passwordEdt;
    AccountDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameEdt = findViewById(R.id.editTextTextPersonName);
        surnameEdt = findViewById(R.id.editTextTextPersonName2);
        emailEdt = findViewById(R.id.editTextTextEmailAddress2);
        passwordEdt = findViewById(R.id.editTextTextPassword2);

        dbHandler = new AccountDBHandler(RegistrationActivity.this);

    }

    public void registerNewAccount(View view) {
        String name = nameEdt.getText().toString();
        String surname = surnameEdt.getText().toString();
        String email = emailEdt.getText().toString();
        String password = passwordEdt.getText().toString();

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegistrationActivity.this, "Enter all fields.", Toast.LENGTH_SHORT).show();
        } else {
            dbHandler.addNewAccount(name, surname, email, password, "user");

            Toast.makeText(RegistrationActivity.this, "You've been successfully registered.", Toast.LENGTH_SHORT).show();
            nameEdt.setText("");
            surnameEdt.setText("");
            emailEdt.setText("");
            passwordEdt.setText("");

            Intent loginIntent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(loginIntent);
        }

        // add logic and second password field, return toast if both passwords don't match
    }
}