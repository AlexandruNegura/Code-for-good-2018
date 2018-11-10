package com.example.pemil.juliashouse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    //Declare a private RequestQueue variable
    private RequestQueue requestQueue;
    private static LoginActivity mInstance;

    private Context context;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private EditText usernameEditText;
    private EditText passwordEditText;


    public static synchronized LoginActivity getInstance() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context = this;
        Boolean logged = false;
        String savedUser;
        String davedPassword;
        sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();




        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mInstance = this;

        // Check if UserResponse is Already Logged In
        savedUser = sharedPref.getString("username", "");
        davedPassword = sharedPref.getString("password", "");

        logged = (savedUser != "") && (davedPassword != "");

        if (logged) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }


    }


    public void login(View viewd) {

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String requestUrl = "https://code-for-good.herokuapp.com/api/user/login";
        JSONObject postparams = new JSONObject();

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.input_user_password);


        final String user = usernameEditText.getText().toString();
        final String passwd = passwordEditText.getText().toString();
        Log.i("DANA", user + " " + passwd);
        try {
            postparams.put("username", user);
            postparams.put("password", passwd);

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    requestUrl, postparams,
                    new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {

                            // caching the authentification details
                            Log.i("Auth successful",response.toString());
                            editor.putString("username", user);
                            editor.putString("password", passwd);
                            editor.commit();

                            // change intent

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                        }


                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Failure Callback
                            Log.e("ERROR", error.toString());
                        }
                    });

            // Adding the request to the queue along with a unique string tag
            LoginActivity.getInstance().addToRequestQueue(jsonObjReq, "postRequest");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
