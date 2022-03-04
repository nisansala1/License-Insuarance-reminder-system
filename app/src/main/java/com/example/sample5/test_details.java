package com.example.sample5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test_details extends AppCompatActivity {
    DatabaseHelper db;
    Button b1,b2,b3;
    TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_details);
        db = new DatabaseHelper(this);
        b1 = (Button) findViewById(R.id.home);

        b3 = (Button) findViewById(R.id.b5);
        t1 = (TextView) findViewById(R.id.Eco);
        t2 = (TextView) findViewById(R.id.carbon);
        t3 = (TextView) findViewById(R.id.fitness);
        t4 = (TextView) findViewById(R.id.test);
        t4.setMovementMethod((new ScrollingMovementMethod()));



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);

            }
        });

        String s = getIntent().getStringExtra("EXTRA_SESSION_ID");
        Cursor res = db.getAllData4(s);
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

            t4.setText("On"+res.getString(1)+ " "+res.getString(3)+","+res.getString(4)+","+res.getString(5));


            String dateInString = res.getString(2);  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dateInString));

            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, 365);
            sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date resultdate = new Date(c.getTimeInMillis());
            dateInString = sdf.format(resultdate);
            t1.setText(dateInString);
            t2.setText(dateInString);
            t3.setText(dateInString);


        }
}
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}