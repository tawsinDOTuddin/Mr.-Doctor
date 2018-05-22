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

public class Reg1 extends Activity {

    EditText full_name,username, password, email,phone,fee,regi,spa;
    Spinner spinner;
    String Password, Email,Area,Full,User,Phone,Fee,Regi,Spa;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg1);
        full_name = (EditText) findViewById(R.id.editText3);
        username=(EditText)findViewById(R.id.editText4) ;
        password = (EditText) findViewById(R.id.editText5);
        regi = (EditText) findViewById(R.id.editText00);
        email = (EditText) findViewById(R.id.editText7);
        spinner=(Spinner)findViewById(R.id.spin);
        phone=(EditText)findViewById(R.id.editText18);
        fee=(EditText)findViewById(R.id.editText19);
        spa=(EditText) findViewById(R.id.editText2);
    }

    public void pp(View view){
        startActivity(new Intent(this, com.uddin.tawsin.mrdoctor.login.class));
    }

    public void ck(View v){
        Full = full_name.getText().toString();
        Password = password.getText().toString();
        Email = email.getText().toString();
        User=username.getText().toString();
        Phone=phone.getText().toString();
        Regi=regi.getText().toString();
        Fee=fee.getText().toString();
        Area=spinner.getSelectedItem().toString();
        Spa=spa.getText().toString();
        if(Full.length() == 0 || Password.length() == 0 || Email.length() == 0 || User.length() == 0 || Phone.length() == 0 || Regi.length() == 0 || Fee.length() == 0 || Area.length() == 0 || Spa.length() == 0) Toast.makeText(ctx,"Fill all field",Toast.LENGTH_LONG).show();
        else {BackGround b = new BackGround();
        b.execute(Full,User, Password, Email,Area,Regi,Spa,Phone,Fee);}
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String full_name = params[0];
            String username = params[1];
            String password = params[2];
            String email = params[3];
            String area = params[4];
            String regi = params[5];
            String Spa=params[6];
            String phone = params[7];
            String fee = params[8];
            String data="";
            int tmp;
            //int i
            try {
                URL url = new URL("http://tawsindotuddin.site88.net/register-Copy.php");
                String urlParams = "full_name="+full_name+"&username="+username+"&password="+password+"&email="+email+"&area="+area+"&regi="+regi+"$specialist="+Spa+"&phone="+phone+"&fee="+fee;

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