package com.example.sample5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VehicleProfile extends AppCompatActivity {
    DatabaseHelper db;
    Button btnviewAll, payment, test,update;
    TextView t1,t2,t3,t4,t5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);
        db = new DatabaseHelper(this);
        btnviewAll=(Button)findViewById(R.id.b1);
        payment=(Button)findViewById(R.id.b8);
        test=(Button)findViewById(R.id.b9);
        update=(Button)findViewById(R.id.b10);

        t1=(TextView)findViewById(R.id.V1);
        t2=(TextView)findViewById(R.id.V2);
        t3=(TextView)findViewById(R.id.V3);
        t4=(TextView)findViewById(R.id.V4);
        t5=(TextView)findViewById(R.id.V5);
        UpdateData();
        Payemnt();
        Test();
        getpage();








        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }
                });






    }
    public void getpage(){
        String s0 = getIntent().getStringExtra("EXTRA_SESSION_ID");
        Cursor res = db.getAllData3(s0);
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            t1.setText( res.getString(0)+"\n");
            t2.setText( res.getString(1)+"\n");
            t3.setText( res.getString(2)+"\n");
            t4.setText( res.getString(3)+"\n");
            t5.setText( res.getString(4)+"\n");


        }
    }




    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = db.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            t1.setText( res.getString(0)+"\n");
                            t2.setText( res.getString(1)+"\n");
                            t3.setText( res.getString(2)+"\n");
                            t4.setText( res.getString(3)+"\n");
                            t5.setText( res.getString(4)+"\n");


                        }
                    }
                }
        );
    }

    public void UpdateData() {
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s0 = getIntent().getStringExtra("EXTRA_SESSION_ID");
                        Intent intent = new Intent(getApplicationContext(), UpdateClass.class);
                        intent.putExtra("EXTRA_SESSION_ID", s0);
                        startActivity(intent);
                }}
        );
    }

    public void Payemnt() {
        payment.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s0 = getIntent().getStringExtra("EXTRA_SESSION_ID");
                        Intent intent = new Intent(getApplicationContext(), payment_details.class);
                        intent.putExtra("EXTRA_SESSION_ID", s0);
                        startActivity(intent);
                    }}
        );
    }

    public void Test() {
        test.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s0 = getIntent().getStringExtra("EXTRA_SESSION_ID");
                        Intent intent = new Intent(getApplicationContext(), test_details.class);
                        intent.putExtra("EXTRA_SESSION_ID", s0);
                        startActivity(intent);
                    }}
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}