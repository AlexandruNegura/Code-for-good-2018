package com.example.pemil.juliashouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.pemil.juliashouse.Models.Sit;
import com.example.pemil.juliashouse.notifications.NotificationUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        List<Sit> sits = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date start = simpleDateFormat.parse("10/11/2018 05:42");
            Date end = simpleDateFormat.parse("10/11/2018 05:43");
            sits.add(new Sit(0L, start, end, 5L, null));

            start = simpleDateFormat.parse("10/11/2018 05:44");
            end = simpleDateFormat.parse("10/11/2018 05:45");
            sits.add(new Sit(1L, start, end, 6L, null));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext(), sits);
        notificationUtils.createNotificationChannel();
        notificationUtils.createNotificationGroup();
    }

    private ArrayList<Object> getSit() {
        sits.add(getVerticalData().get(0));
        return sits;
    }

    public static ArrayList<SingleVertical> getVerticalData() {
        ArrayList<SingleVertical> singleVerticals = new ArrayList<>();
        singleVerticals.add(new SingleVertical("Sit 1", "10/11/2018", "10/11/2018", R.drawable.task3));
        singleVerticals.add(new SingleVertical("Sit 2", "10/11/2018", "10/11/2018", R.drawable.task4));
        singleVerticals.add(new SingleVertical("Sit 3", "10/11/2018", "10/11/2018", R.drawable.task));

        return singleVerticals;
    }

    public void openSit(View view) {
        Intent intent = new Intent(this, SitActivity.class);
        startActivity(intent);
    }
}
