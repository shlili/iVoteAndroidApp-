package com.example.ivote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_feed);
    }

    public void redirectToLoginPage(View widget) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void redirectToCreateNewPollPage(View widget) {
        startActivity(new Intent(this, CreateNewPollActivity.class));
    }

    public void redirectToDashboard(View widget) {
        startActivity(new Intent(this, DashboardActivity.class));
    }
}