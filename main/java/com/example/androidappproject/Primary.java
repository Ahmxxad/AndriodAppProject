package com.example.androidappproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Primary extends AppCompatActivity {

    public static String passedLoc;
    private Spinner locationSpinner;
    private String[] menLocations = {"M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10", "M11", "M12", "M-Library", "M-Mosque", "M-Forum", "M-Student Centre", "M-Cafeteria"};
    private String[] womenLocations = {"W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9", "W10", "W11", "W12", "W-Library", "W-Forum", "W-Student Centre", "W-Cafeteria"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        RadioGroup endRadioGroup = findViewById(R.id.EndRadio);
        locationSpinner = findViewById(R.id.locationSpinner);
        Button goButton = findViewById(R.id.GoButton);
        Button SchedButton = findViewById(R.id.SchedButton);

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

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(locationSpinner.getSelectedItemPosition() < 10){
                    SchedButton.setVisibility(View.VISIBLE);
                } else SchedButton.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedLocation = locationSpinner.getSelectedItem().toString();
                String coordinates = getCoordinates(selectedLocation);

                if (!coordinates.isEmpty()) {
                    Uri MapIntentUri = Uri.parse("google.navigation:q=" + coordinates + "&mode=w");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, MapIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            }
        });

        SchedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loc = locationSpinner.getSelectedItem().toString();
                Intent intent = new Intent(Primary.this, Secondary.class);
                intent.putExtra(passedLoc, locationSpinner.getSelectedItem().toString());
                startActivity(intent);
            }

        });
    }

    private String getCoordinates(String location) {
        switch (location) {
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
                return "25°17'09.1 55°28'36.4";
            case "M8":
                return "25°17'14.1 55°28'33.7";
            case "M9":
                return "M9, University of Sharjah";
            case "M10":
                return "25°17'12.2 55°28'36.5";
            case "M11":
                return "University of Sharjah: M11 - Main Building/ Admin building";
            case "M12":
                return "Men's Central Laboratories - M12";
            case "M-Library":
                return "Men's Library";
            case "M-Mosque":
                return "Sharjah University Mosque";
            case "M-Forum":
                return "25°17'05.4 55°28'28.8";
            case "M-Student Centre":
                return "Student Center M21 - Sharjah";
            case "M-Cafeteria":
                return "25°17'03.0 55°28'31.0";
            case "W1":
                return "25°17'34.4 55°28'52.3";
            case "W2":
                return "W2 Building - University City - Sharjah";
            case "W3":
                return "25°17'28.2 55°28'47.7";
            case "W4":
                return "25°17'23.0 55°28'40.4";
            case "W5":
                return "25°17'21.9 55°28'41.9";
            case "W6":
                return "25°17'21.1 55°28'43.1";
            case "W7":
                return "College of Communication - W7";
            case "W8":
                return "25°17'18.8 55°28'37.3";
            case "W9":
                return "College of Engineering - W9";
            case "W10":
                return "25°17'18.8 55°28'37.3";
            case "W11":
                return "University of Sharjah: M11 - Main Building/ Admin building";
            case "W12":
                return "Women's Central Laboratories - W12";
            case "W-Library":
                return "Women’s Library - W18";
            case "W-Forum":
                return "25°17'26.1 55°28'43.9";
            case "W-Student Centre":
                return "25°17'24.2 55°28'48.9";
            case "W-Cafeteria":
                return "25°17'26.1 55°28'46.7";
            default:
                return "";
        }
    }

}
