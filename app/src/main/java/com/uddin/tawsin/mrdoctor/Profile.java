package com.uddin.tawsin.mrdoctor;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    private TextView textView,textView3;
    public String id,username,password;
    public static final String KEY_ID="id";
    int oo=0;

    private ArrayList<String> dataList = new ArrayList<>();
    ProgressDialog loading;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView = (TextView) findViewById(R.id.textView);
        textView3 = (TextView) findViewById(R.id.textView3);

        Intent intent = getIntent();

        username= intent.getStringExtra(login.KEY_USERNAME);
        password= intent.getStringExtra(login.KEY_PASSWORD);


        textView.setText("Welcome  Dr. " + intent.getStringExtra(login.KEY_USERNAME));
    }


    public void getData() {

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = "http://tawsindotuddin.site88.net/getData1(4).php?username="+username+"&&password="+password;

     //  String url1 = "http://tawsindotuddin.site88.net/getData1(4).php?username=t&&password=t";

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Profile.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void showJSON(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
                JSONObject collegeData = result.getJSONObject(0);
                id=collegeData.getString("doc_id");
              //  name = collegeData.getString(Config.KEY_NAME);
               // phone = collegeData.getString(Config.KEY_PHONE);
               // fee = collegeData.getString(Config.KEY_FEE);

            //    dataList.add('\n'+"ID : "+id+'\n'+"Name : " + name + '\n' + "Phone : " + phone + '\n' + "Fee : " + fee + "\n\n");

              //  fee="555"
        }catch(JSONException e){
            e.printStackTrace();
        }

        textView3.setText("Your ID is "+id);

       // if (k == 0) textViewResult.setText("No doctor found in your area");
       // else textViewResult.setText(dataList.toString());
      //  dataList.clear();

    }




  /*  public void getData1() {
       // String id = spinner.getSelectedItem().toString().trim();
        //  String id = editText10.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an area", Toast.LENGTH_LONG).show();
            return;
        }
       // loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = "http://tawsindotuddin.site88.net/getData1(3).php?id="+id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Profile.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void showJSON(String response) {
      //  String id = "";
        String name = "";
       // String phone = "";
       // String fee = "";
        int k=0;
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            k=result.length();


            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
              //  id=collegeData.getString("doc_id");
               name = collegeData.getString("username");
             //   phone = collegeData.getString(Config.KEY_PHONE);
               // fee = collegeData.getString(Config.KEY_FEE);

                dataList.add('\n'+"Name : "+name+'\n');

                //  name="dfdfdf";
                //  phone="010101";

            }//  fee="555"
        }catch(JSONException e){
            e.printStackTrace();
        }

        if (k == 0) textView3.setText("No patient found");
        else textView3.setText(dataList.toString());
        dataList.clear();

    }*/





    public void bbb(View view){
        oo=1;
        getData();

     //   getData1();
    }

    public  void  bbb2(View view) {
        if (oo == 0) Toast.makeText(Profile.this, "Please get your ID", Toast.LENGTH_LONG).show();
        else {
            Intent intent = new Intent(this, getApp.class);
            intent.putExtra(KEY_ID, id);
            startActivity(intent);
            oo=0;
        }
    }






    }




