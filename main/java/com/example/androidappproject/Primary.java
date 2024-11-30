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
            //ones with pointers need to change or deleted
            case "M1":
                return "25°16'54.7 55°28'23.3";
            case "M2":
                return "25°16'57.9 55°28'25.7";
            case "M3":
                return "25°17'02.1 55°28'28.7";
            case "M4":
                return "25°17'09.5 55°28'30.6";
            case "M5":
                return "M5, University of Sharjah";
            case "M6":
                return "25°17'08.1 55°28'33.8";
            case "M7":
                return "M7 Collage of Sciences (Men)";
            case "M8":
                return "7FPG+RC Sharjah";
            case "M9":
                return "M9, University of Sharjah";
            case "M10":
                return "25°17'12.2 55°28'36.5";
            case "M11":
                return "University of Sharjah: M11 - Main Building/ Admin building";
            case "M12":
                return "Men's Central Laboratories - M12";
            case "W1":
                return "7FVJ+6HP - University City - Sharjah";
            case "W2":
                return "W2 Building - University City - Sharjah";
            case "W3":
                return "7FRH+CWQ - University City - Sharjah";
            case "W4":
                return "7FQH+R42 Sharjah";
            case "W5":
                return "7FQH+Q83 - University City - Sharjah";
            case "W6":
                return "7FQH+JFR - University City - Sharjah";
            case "W7":
                return "College of Communication - W7";
            case "W8":
                return "7FQG+CRC - University City - Sharjah";
            case "W9":
                return "7FQG+7X Sharjah";
            case "W10":
                return "7FQH+65X Sharjah";
            case "W11":
                return "University of Sharjah: M11 - Main Building/ Admin building";
            case "W12":
                return "Women's Central Laboratories - W12";
            default:
                return "";
        }
    }

}
