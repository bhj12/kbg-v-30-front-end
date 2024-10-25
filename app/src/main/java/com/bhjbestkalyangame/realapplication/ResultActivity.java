package com.bhjbestkalyangame.realapplication;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    private HashSet<Integer> generatedNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        // Initialize the HashSet
        generatedNumbers = new HashSet<>();

        // Set game title
        String buttonName = getIntent().getStringExtra("button_name");
        TextView gameTitleTextView = findViewById(R.id.gameTitleTextView);
        gameTitleTextView.setText(buttonName);

        // Set current date
        TextView dateDisplayTextView = findViewById(R.id.datedisplay);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        dateDisplayTextView.setText(currentDate);

        // Find the GridLayout for results
        GridLayout resultsGridLayout = findViewById(R.id.resultsGridLayout);
        resultsGridLayout.setColumnCount(3); // Set number of columns

        // Generate results based on the selected game
        generateResults(buttonName, resultsGridLayout);
    }

    private void generateResults(String buttonName, GridLayout resultsGridLayout) {
        int totalItems = getTotalItems(buttonName);
        int minRandom = getMinRandom(buttonName);
        int maxRandom = getMaxRandom(buttonName);
        Random random = new Random();

        if ("Panel Game".equals(buttonName)) {
            generateUniqueThreeDigitNumbers(random, resultsGridLayout, totalItems);
        } else {
            generateUniqueNumbers(random, resultsGridLayout, buttonName, totalItems, minRandom, maxRandom);
        }
    }

    private int getTotalItems(String buttonName) {
        switch (buttonName) {
            case "Single Kalyan Game": return 4;
            case "Jodi Kalyan Game": return 12;
            case "Panel Game": return 15;
            default: return 0;
        }
    }

    private int getMinRandom(String buttonName) {
        switch (buttonName) {
            case "Single Kalyan Game": return 0;
            case "Jodi Kalyan Game": return 10;
            case "Panel Game": return 100;
            default: return 0;
        }
    }

    private int getMaxRandom(String buttonName) {
        switch (buttonName) {
            case "Single Kalyan Game": return 9;
            case "Jodi Kalyan Game": return 99;
            case "Panel Game": return 999;
            default: return 0;
        }
    }

    private void generateUniqueNumbers(Random random, GridLayout resultsGridLayout, String buttonName, int totalItems, int minRandom, int maxRandom) {
        for (int i = 0; i < totalItems; i++) {
            int randomNumber;
            do {
                randomNumber = random.nextInt((maxRandom - minRandom) + 1) + minRandom;
            } while (!generatedNumbers.add(randomNumber)); // Ensure uniqueness

            addResultView(resultsGridLayout, randomNumber, buttonName, i + 1);
        }
    }

    private void generateUniqueThreeDigitNumbers(Random random, GridLayout resultsGridLayout, int totalItems) {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < totalItems) {
            int num = random.nextInt(900) + 100; // Generate number between 100 and 999
            if (generatedNumbers.add(num)) {
                numbers.add(num);
            }
        }

        // Format and display numbers
        for (int num : numbers) {
            int formattedNum = formatThreeDigitNumber(num);
            addResultView(resultsGridLayout, formattedNum, "three-digit", numbers.indexOf(num) + 1);
        }
    }

    private int formatThreeDigitNumber(int number) {
        char[] digits = String.valueOf(number).toCharArray();
        Arrays.sort(digits);

        StringBuilder formatted = new StringBuilder();
        boolean zeroFound = false;

        for (char digit : digits) {
            if (digit == '0') {
                zeroFound = true;
            } else {
                formatted.append(digit);
            }
        }

        if (zeroFound) {
            formatted.append('0'); // Append zero at the end if found
        }

        return Integer.parseInt(formatted.toString());
    }

    private void addResultView(GridLayout resultsGridLayout, int number, String type, int superNo) {
        LinearLayout resultLayout = new LinearLayout(this);
        resultLayout.setOrientation(LinearLayout.VERTICAL);
        resultLayout.setGravity(Gravity.CENTER);

        // Label for the result
        TextView superNoTextView = createTextView(String.format("Super No %d", superNo), 16, Color.parseColor("#BE7273"), Typeface.BOLD);
        TextView dataTextView = createTextView(formatData(number, type), 35, Color.parseColor("#5C0002"), Typeface.BOLD);

        resultLayout.addView(superNoTextView);
        resultLayout.addView(dataTextView);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.setMargins(16, 16, 16, 16);
        resultLayout.setLayoutParams(params);
        resultsGridLayout.addView(resultLayout);
    }

    private String formatData(int number, String type) {
        if ("three-digit".equals(type)) {
            return String.format("%03d", number); // Format to three digits
        }
        return String.valueOf(number); // Return as is for single and two digits
    }

    private TextView createTextView(String text, float textSize, int textColor, int typefaceStyle) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        textView.setTypeface(null, typefaceStyle);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
