package com.example.androidappproject;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        radioGroup = findViewById(R.id.SchedulesRadio);
        spinner = findViewById(R.id.spinner);
        tableLayout = findViewById(R.id.tableLayout);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.SchedulesRadioM) {
                setupSpinner("M");
            } else if (checkedId == R.id.SchedulesRadioW) {
                setupSpinner("W");
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
        String[] items = new String[12];
        for (int i = 0; i < 12; i++) {
            items[i] = prefix + (i + 1);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }

    private void generateTable() {
        tableLayout.removeAllViews();
        String[] times = {"8:00-9:15", "9:30-10:45", "11:00-12:15", "12:30-1:45", "2:00-3:15"};
        String[] rooms = {"001", "002", "003", "004", "005", "006", "007", "008", "101", "102", "103", "104", "105", "106", "107", "108"};

        TableRow headerRow = new TableRow(this);
        headerRow.addView(createTextView("Time/Room"));
        for (String room : rooms) {
            headerRow.addView(createTextView(room));
        }
        tableLayout.addView(headerRow);

        for (String time : times) {
            TableRow tableRow = new TableRow(this);
            tableRow.addView(createTextView(time));
            for (String room : rooms) {
                tableRow.addView(createTextView(Math.random() > 0.5 ? "Occupied" : "Not Occupied"));
            }
            tableLayout.addView(tableRow);
        }
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(10, 10, 10, 10);
        return textView;
    }
}