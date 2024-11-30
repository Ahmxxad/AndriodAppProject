package com.example.androidappproject;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Secondary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        String[] times = {"8:00-9:15", "9:30-10:45", "11:00-12:15", "12:30-1:45", "2:00-3:15"};
        String[] rooms = {"001", "002", "003", "004", "005", "006", "007", "008", "101", "102", "103", "104", "105", "106", "107", "108"};

        for (String room : rooms) {
            TableRow tableRow = new TableRow(this);
            tableRow.addView(createTextView(room));

            for (String time : times) {
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
