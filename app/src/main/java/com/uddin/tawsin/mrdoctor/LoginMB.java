package com.uddin.tawsin.mrdoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginMB extends AppCompatActivity {
   // Button doctor,patient;
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       // doctor=(Button)findViewById(R.id.button1) ;
       // patient=(Button)findViewById(R.id.button2) ;
    }

    public  void  ccc(View view){
        Intent i = new Intent(this,login.class);
        startActivity(i);
    }

    public  void  mama(View view){
        startActivity(new Intent(this,rogi_login.class));
    }
}