package com.example.pemil.juliashouse;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SitActivity extends AppCompatActivity {
    int duration = Toast.LENGTH_SHORT;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sit_layout);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.delay_values, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void cancelSit(View view) {
        Toast.makeText(getApplicationContext(), R.string.sit_cancelled_message, duration).show();
    }

    public void sitDone(View view) {
        Toast.makeText(getApplicationContext(), R.string.sit_done_message, duration).show();
    }


    public void arrivedAtDestination(View view) {
        Toast.makeText(getApplicationContext(), R.string.arrived_message, duration).show();
    }

    public void addComment(View view) {
        findViewById(R.id.button_save).setVisibility(View.VISIBLE);
    }

    public void saveComment(View view) {
        findViewById(R.id.button_save).setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), R.string.comment_saved_message, duration).show();
    }
}

