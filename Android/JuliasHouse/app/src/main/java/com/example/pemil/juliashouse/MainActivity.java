package com.example.pemil.juliashouse;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //Declare a private RequestQueue variable
    private RequestQueue requestQueue;
    private static MainActivity mInstance;

    private Context context;
    private int id;


    public static synchronized MainActivity getInstance() {
        return mInstance;
    }
    /*
    Create a getRequestQueue() method to return the instance of
    RequestQueue.This kind of implementation ensures that
    the variable is instatiated only once and the same
    instance is used throughout the application
    */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        return requestQueue;
    }
    /*
    public method to add the Request to the the single
    instance of RequestQueue created above.Setting a tag to every
    request helps in grouping them. Tags act as identifier
    for requests and can be used while cancelling them
    */
    public void addToRequestQueue(Request request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }
    /*
    Cancel all the requests matching with the given tag
    */
    public void cancelAllRequests(String tag) {
        getRequestQueue().cancelAll(tag);
    }

    private ArrayList<Object> sits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInstance = this;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        Log.i("ID",id + " ");
        context = this;
        getSits();
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

    public void getSits() {
        String url = "https://code-for-good.herokuapp.com/api/user/" + id + "/getAll";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Sits", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback
                        Log.i("Error", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        // Adding the request to the queue along with a unique string tag
        MainActivity.getInstance().addToRequestQueue(jsonObjReq, "getRequest");
    }
    public void openSit(View view) {
        Intent intent = new Intent(this, SitActivity.class);
        startActivity(intent);
    }
}
