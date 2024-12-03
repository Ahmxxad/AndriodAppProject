package com.example.androidappproject;

import static com.example.androidappproject.Primary.passedLoc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Secondary extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Spinner spinner;
    private TableLayout tableLayout;
    private String passedLocation;
    private RadioButton SRM;
    private RadioButton SRW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        passedLocation = getIntent().getStringExtra(passedLoc);
        radioGroup = findViewById(R.id.SchedulesRadio);
        spinner = findViewById(R.id.spinner);
        tableLayout = findViewById(R.id.tableLayout);
        SRM = findViewById(R.id.SchedulesRadioM);
        SRW = findViewById(R.id.SchedulesRadioW);

        if(passedLocation != null){

            String PLprefix = String.valueOf(passedLocation.charAt(0));
            switch(PLprefix){
                case "M": {SRM.setChecked(true); break;}
                case "W": {SRW.setChecked(true); break;}
                default: {};
            }
            setupSpinner(PLprefix);
            checkSpinner(passedLocation);
            spinner.setVisibility(View.VISIBLE);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (SRM.isChecked()) {
                    setupSpinner("M");
                } else if (SRW.isChecked()) {
                    setupSpinner("W");
                }
                spinner.setVisibility(View.VISIBLE);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                generateTable();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupSpinner(String prefix) {
        String[] items = new String[10];
        for (int i = 0; i < 10; i++) {
            items[i] = prefix + (i + 1);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

    }

    private void checkSpinner(String passedLocation) {
        int LocationNumber = Integer.parseInt(String.valueOf(passedLocation.charAt(1)));
        spinner.setSelection(LocationNumber - 1);
    }

    private void generateTable() {
        tableLayout.removeAllViews();
        String[] times = {"8:00-9:15", "9:30-10:45", "11:00-12:15", "12:30-1:45", "2:00-3:15"};
        String[] rooms = {"001", "002", "003", "004", "005", "006", "007", "008", "101", "102", "103", "104", "105", "106", "107", "108"};

        TableRow headerRow = new TableRow(this);
        headerRow.addView(createCell("Time/Room"));
        for (int i = 0; i < rooms.length; i++) {
            String room = rooms[i];
            headerRow.addView(createCell(room));
        }
        tableLayout.addView(headerRow);

        for (int i = 0; i < times.length; i++) {
            String time = times[i];
            TableRow tableRow = new TableRow(this);
            tableRow.addView(createCell(time));
            for (int j = 0; j < rooms.length; j++) {
                tableRow.addView(createCell(Math.random() > 0.5 ? "Occupied" : "Not Occupied"));
            }
            tableLayout.addView(tableRow);
        }
    }

    private TextView createCell(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(25, 25, 25, 25);
        return textView;
    }
}