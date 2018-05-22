package com.uddin.tawsin.mrdoctor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import static android.R.attr.id;
import static android.R.attr.name;

public class rogi_reg extends Activity {

    EditText full_name,username, age,password, email,phone;
    String Password, Email,Full,User,Phone,Age;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rogi_reg);
        full_name = (EditText) findViewById(R.id.editText13);
        username=(EditText)findViewById(R.id.editText6) ;
        password = (EditText) findViewById(R.id.editText21);
        email = (EditText) findViewById(R.id.editText20);
        phone=(EditText)findViewById(R.id.editText22);
        age=(EditText) findViewById(R.id.editText17);
    }

    public void pp01(View view){
        startActivity(new Intent(this, com.uddin.tawsin.mrdoctor.rogi_login.class));
    }

    public void ck01(View v){
        Full = full_name.getText().toString();
        Password = password.getText().toString();
        Email = email.getText().toString();
        User=username.getText().toString();
        Phone=phone.getText().toString();
        Age=age.getText().toString();
        if(Full.length() == 0 || Password.length() == 0 || Email.length() == 0 || User.length() == 0 || Phone.length() == 0 || Age.length()==0) Toast.makeText(ctx,"Fill all field",Toast.LENGTH_LONG).show();
        else {BackGround b = new BackGround();
            b.execute(Full,User,Age, Email, Password,Phone);}
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String full_name = params[0];
            String username = params[1];
            String age = params[2];
            String email = params[3];
            String password = params[4];
            String phone = params[5];

            String data="";
            int tmp;
            //int i
            try {
                URL url = new URL("http://tawsindotuddin.site88.net/register1-Copy.php");
                String urlParams = "full_name="+full_name+"&username="+username+"&age="+age+"&email="+email+"&password="+password+"&phone="+phone;

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
                s="Data saved successfully.";
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }

}