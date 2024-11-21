package com.example.androidappproject;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Primary extends AppCompatActivity {

    ArrayList<String> LocationsM;
    ArrayAdapter<String> Madapter;

    ArrayList<String> LocationsW;
    ArrayAdapter<String> Wadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_primary);

        AutoCompleteTextView actvEnd = findViewById(R.id.EndSelection);
        RadioGroup EndRadio = findViewById(R.id.EndRadio);

        //RadioButton StartM = findViewById(R.id.StartRadioM);
        //RadioButton EndM = findViewById(R.id.EndRadioM);
        //RadioButton StartW = findViewById(R.id.StartRadioW);
        //RadioButton EndW = findViewById(R.id.EndRadioW);

        initialiseAdapters();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EndRadio.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton selected = findViewById(i);
            String selectedText = selected.getText().toString();

            activateList(actvEnd, selectedText);
        });
    }

    private void initialiseAdapters(){
        LocationsM = loadLocations("M");
        Madapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, LocationsM);

        LocationsW = loadLocations("W");
        Wadapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, LocationsW);
    }

    private ArrayList<String> loadLocations(String X){
        ArrayList<String> locations = new ArrayList<>();

        if(Objects.equals(X, "M")){
            try {
                AssetManager AssMan = getAssets();
                InputStream istream = AssMan.open("Mlocations.txt");
                BufferedReader buffread = new BufferedReader(new InputStreamReader(istream));

                String line;
                while((line = buffread.readLine()) != null){
                    locations.add(line);
                }
                buffread.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                AssetManager AssMan = getAssets();
                InputStream istream = AssMan.open("Wlocations.txt");
                BufferedReader buffread = new BufferedReader(new InputStreamReader(istream));

                String line;
                while((line = buffread.readLine()) != null){
                    locations.add(line);
                }
                buffread.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return locations;

    }

    private void activateList(AutoCompleteTextView actv, String selectedText){

        actv.setVisibility(View.VISIBLE);

        if(Objects.equals(selectedText, "Men (M)")){
            actv.setAdapter(Madapter);
        }
        else{
            actv.setAdapter(Wadapter);
        }
    }
}