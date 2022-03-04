package com.example.sample5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class registration1 extends AppCompatActivity {
    DatabaseHelper db;
EditText e1,e2,e3,e4,e5;
Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);
        db=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        e4=(EditText)findViewById(R.id.e4);
        e5=(EditText)findViewById(R.id.e5);
        b1=(Button)findViewById(R.id.b);




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                String s3=e3.getText().toString();
                String s4=e4.getText().toString();
                String s5=e5.getText().toString();

                if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")) {

                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();

                }else{



                            Boolean insert =db.insert(s1,s2,s3,s4,s5);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Added the data successfully",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), registration2.class);
                                intent.putExtra("EXTRA_SESSION_ID", e1.getText().toString());
                                startActivity(intent);

                            }

                        else{
                            Toast.makeText(getApplicationContext(),"can not register",Toast.LENGTH_SHORT).show();
                        }

                    Toast.makeText(getApplicationContext(),"Re enter",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}