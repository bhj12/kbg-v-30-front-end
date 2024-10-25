// MainActivity.java
package com.bhjbestkalyangame.realapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize buttons
        Button kalyanMatka = findViewById(R.id.button1);
        Button kalyanNight = findViewById(R.id.button2);
        Button rajdhaniNight = findViewById(R.id.button3);
        Button specialGame = findViewById(R.id.button4);

        // Set up click listeners for each button
        kalyanMatka.setOnClickListener(v -> openSubActivity("Kalyan Matka"));
        kalyanNight.setOnClickListener(v -> openSubActivity("Kalyan Night"));
        rajdhaniNight.setOnClickListener(v -> openSubActivity("Rajdhani Night"));
        specialGame.setOnClickListener(v -> openSubActivity("Special Game"));
    }

    // Method to start SubActivity and pass the button name
    private void openSubActivity(String buttonName) {
        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        intent.putExtra("button_name", buttonName); // Pass the button name to SubActivity
        startActivity(intent);
    }
}
