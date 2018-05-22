package com.uddin.tawsin.mrdoctor;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Spinner;
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

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.Arrays;

        import static com.uddin.tawsin.mrdoctor.R.id.textView;

public class rogi extends AppCompatActivity {

     Spinner spinner;
     Button buttonGet;
     TextView textViewResult,uuu;

    String username,password;
    int p=0;


    EditText Id;
    public String id;
    public int k;
    ProgressDialog loading;
    //String id1;
    private ArrayList<String> dataList = new ArrayList<>();
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rogi);
        Id = (EditText)findViewById(R.id.eee);
        spinner = (Spinner)findViewById(R.id.spin1);
        buttonGet = (Button)findViewById(R.id.buttonGet);
       // ListView listView = (ListView) findViewById(R.id.kaka);
        //adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dataList);
        textViewResult = (TextView)findViewById(R.id.textView5);


        Intent intent = getIntent();

      username= intent.getStringExtra(rogi_login.KEY_USERNAME);
      password= intent.getStringExtra(rogi_login.KEY_PASSWORD);




    }




    public void getData() {
       String id = spinner.getSelectedItem().toString().trim();
      //  String id = editText10.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an area", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.DATA_URL+spinner.getSelectedItem().toString().trim();

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
                        Toast.makeText(rogi.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void showJSON(String response) {
        String id = "";
        String name = "";
        String phone = "";
        String fee = "";
        int k=0;
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
               k=result.length();


                for (int i = 0; i < result.length(); i++) {
                    JSONObject collegeData = result.getJSONObject(i);
                    id=collegeData.getString("doc_id");
                    name = collegeData.getString(Config.KEY_NAME);
                    phone = collegeData.getString(Config.KEY_PHONE);
                    fee = collegeData.getString(Config.KEY_FEE);

                    dataList.add('\n'+"ID : "+id+'\n'+"Name : " + name + '\n' + "Phone : " + phone + '\n' + "Fee : " + fee + "\n\n");

                    //  name="dfdfdf";
                    //  phone="010101";

                }//  fee="555"
            }catch(JSONException e){
                e.printStackTrace();
            }

        if (k == 0) textViewResult.setText("No doctor found in your area");
        else textViewResult.setText(dataList.toString());
            dataList.clear();

    }

    public void kaka(View view){
        getData();
        p=1;
    }

    public void get(View view) {

        id = Id.getText().toString();
        if (id.length()==0 || p==0) Toast.makeText(ctx, "Something going wrong!!!",Toast.LENGTH_LONG).show();
        else {
            BackGround b = new BackGround();
            b.execute(username, password, id);
        }
    }


    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            String id = params[2];

            String data="";
            int tmp;
            //int i
            try {
                URL url = new URL("http://tawsindotuddin.site88.net/register2-Copy.php");
                String urlParams = "username="+username+"&password="+password+"&id="+id;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                s="Appointment taken";
            }

            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }

}
