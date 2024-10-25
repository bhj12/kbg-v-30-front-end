// SubActivity.java
package com.bhjbestkalyangame.realapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_layout);

        // Initialize buttons
        Button singleKalyanGameButton = findViewById(R.id.singleKalyanButton);
        Button jodiKalyanGameButton = findViewById(R.id.jodiKalyanButton);
        Button panelGameButton = findViewById(R.id.panelKalyanButton);

        // Set click listeners for each button to open ResultActivity
        singleKalyanGameButton.setOnClickListener(v -> openResultActivity("Single Kalyan Game"));
        jodiKalyanGameButton.setOnClickListener(v -> openResultActivity("Jodi Kalyan Game"));
        panelGameButton.setOnClickListener(v -> openResultActivity("Panel Game"));
    }

    // Method to start ResultActivity and pass the button name
    private void openResultActivity(String buttonName) {
        Intent intent = new Intent(SubActivity.this, ResultActivity.class);
        intent.putExtra("button_name", buttonName); // Pass the button name to ResultActivity
        startActivity(intent);
    }
}
