package com.example.pemil.juliashouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Object> sits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        MainAdapter adapter = new MainAdapter(this, getSit());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<Object> getSit() {
        sits.add(getVerticalData().get(0));
        return sits;
    }

    public static ArrayList<SingleVertical> getVerticalData() {
        ArrayList<SingleVertical> singleVerticals = new ArrayList<>();
        singleVerticals.add(new SingleVertical("Sit 1", "10/11/2018", "10/11/2018", R.drawable.task3));
        singleVerticals.add(new SingleVertical("Sit 2","10/11/2018", "10/11/2018" , R.drawable.task4));
        singleVerticals.add(new SingleVertical("Sit 3", "10/11/2018", "10/11/2018", R.drawable.task));

        return singleVerticals;
    }

    public void openSit(View view) {
        Intent intent = new Intent(this, SitActivity.class);
        startActivity(intent);
    }
}
