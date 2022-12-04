package com.example.ivote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DisplayPollsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_polls);
    }

    public void redirectToLoginPage(View widget) {
        startActivity(new Intent(this, MainActivity.class));
    }
}