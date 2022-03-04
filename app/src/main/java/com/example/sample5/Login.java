package com.example.sample5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
EditText e1, e2;
Button b1,b2;
DatabaseHelper db;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.LicenseNum);

        b1=(Button)findViewById(R.id.logIn);
        b2=(Button)findViewById(R.id.sign);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), registration1.class);
                startActivity(intent);

            }
        });
        b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String licenseNumber=e1.getText().toString();

                Boolean chknumber=db.chklicenseNumber(licenseNumber);
                if(chknumber==true){
                    Toast.makeText(getApplicationContext(),"Successfully Login",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), VehicleProfile.class);
                    intent.putExtra("EXTRA_SESSION_ID", e1.getText().toString());
                    startActivity(intent);
               }

                else{
                    Toast.makeText(getApplicationContext(),"Wrong licensenumber",Toast.LENGTH_SHORT).show();

                }}


        });


    }
}