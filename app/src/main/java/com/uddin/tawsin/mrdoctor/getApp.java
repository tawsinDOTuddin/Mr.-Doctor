package com.uddin.tawsin.mrdoctor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class getApp extends AppCompatActivity {

    private ArrayList<String> dataList = new ArrayList<>();
    ProgressDialog loading;
    //Context ctx=this;
    String id;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_app);

        textView3=(TextView) findViewById(R.id.textView3);


        Intent intent = getIntent();

        id= intent.getStringExtra(Profile.KEY_ID);
    }

    public void getData1() {
        // String id = spinner.getSelectedItem().toString().trim();
        //  String id = editText10.getText().toString().trim();
             if (id.equals("")) {
                 Toast.makeText(this, "Please enter an area", Toast.LENGTH_LONG).show();
                 return;
             }
         loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

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
                        Toast.makeText(getApp.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void showJSON(String response) {
        //  String id = "";
        String name = "";
        // String phone = "";
        // String fee = "";
        int k = 0;
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            k = result.length();


            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                //  id=collegeData.getString("doc_id");
                name = collegeData.getString("username");
                //   phone = collegeData.getString(Config.KEY_PHONE);
                // fee = collegeData.getString(Config.KEY_FEE);

                dataList.add('\n' + "Name : " + name + '\n');

                //  name="dfdfdf";
                //  phone="010101";

            }//  fee="555"
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (k == 0) textView3.setText("No patient found");
        else textView3.setText(dataList.toString());
        dataList.clear();

       // textView3.setText(id);

    }

    public void bbb3(View view){
        getData1();
    }

    public void bbb4(View view){
        textView3.setText(" ");
    }
}
