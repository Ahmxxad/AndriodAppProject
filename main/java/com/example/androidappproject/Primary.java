package com.example.androidappproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Primary extends AppCompatActivity {

    private Spinner locationSpinner;
    private String[] menLocations = {"M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10", "M11", "M12"};
    private String[] womenLocations = {"W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9", "W10", "W11", "W12"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        RadioGroup endRadioGroup = findViewById(R.id.EndRadio);
        locationSpinner = findViewById(R.id.locationSpinner);
        Button goButton = findViewById(R.id.GoButton);

        endRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String[] locations;
            if (checkedId == R.id.EndRadioM) {
                locations = menLocations;
            } else {
                locations = womenLocations;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);
            locationSpinner.setAdapter(adapter);
            locationSpinner.setVisibility(View.VISIBLE);
        });

        goButton.setOnClickListener(v -> {
            String selectedLocation = locationSpinner.getSelectedItem().toString();
            String coordinates = getCoordinates(selectedLocation);

            if (!coordinates.isEmpty()) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + coordinates + "&mode=w");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    private String getCoordinates(String location) {
        switch (location) {
            case "M1":
                return "25.281861,55.473142";
            case "M2":
                return "25.282230,55.473810";
            case "M3":
                return "25.283050,55.474230";
            case "W1":
                return "25.271861,55.472000";
            case "W2":
                return "25.272100,55.472500";
            // Add all cases for M and W locations
            default:
                return "";
        }
    }
}
