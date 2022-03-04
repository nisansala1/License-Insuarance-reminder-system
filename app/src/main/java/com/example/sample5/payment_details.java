package com.example.sample5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class payment_details extends AppCompatActivity {
 Button b, b2, viewdetails, reminder;
    DatabaseHelper db;
    TextView t1, t2, t3,t;
    private final String CHANNEL_ID="personal notification";
    private final int NOTIFICATION_ID=001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        db = new DatabaseHelper(this);
        b = (Button)findViewById(R.id.homen);
        t=(TextView)findViewById(R.id.P);
        reminder = (Button) findViewById(R.id.b5);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VehicleProfile.class);
                startActivity(intent);

            }
        });

        t1 = (TextView) findViewById(R.id.revenueLicense);
        t2 = (TextView) findViewById(R.id.revenueInsurance);
        t3 = (TextView) findViewById(R.id.Paymenthistory);
        t3.setMovementMethod((new ScrollingMovementMethod()));
        getData();

    }
    public void getData(){
        String s = getIntent().getStringExtra("EXTRA_SESSION_ID");
        int s0 = 1;
        Cursor res = db.getAllData4(s);

        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

            String dateInStringOriginal = res.getString(1); ;
            String dateInString = res.getString(1);  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dateInString));

            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.YEAR, 1);

            Date resultdate = new Date(c.getTimeInMillis());
            dateInString = sdf.format(resultdate);

            String dateInString2Original = res.getString(2);
            String dateInString2 = res.getString(2);  // Start date
            SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
            Calendar c2 = Calendar.getInstance();
            try {
                c2.setTime(sdf2.parse(dateInString2));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            c2.add(Calendar.YEAR, 1);
            Date resultdate2 = new Date(c2.getTimeInMillis());
            dateInString2 = sdf2.format(resultdate2);

            //Date date = new Date(cursor.getLong(1)*1000);
            t1.setText(dateInString +"\n");
            t2.setText(dateInString2 +"\n");;
            t3.setText( "Registered for Revenue License on: "+ dateInStringOriginal +"\n"
                    +"Registered for Insuarance on: "+dateInString2Original+"\n");

        }
    }

    public void TestClick2(View view) {

        Cursor res = db.getAllData2();
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            String dateInString = res.getString(1);  // Start date
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

            String dateInString2 = res.getString(1);  // Start date
            try {
                c.setTime(sdf.parse(dateInString2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date resultdate2 = new Date(c.getTimeInMillis());
            dateInString2 = sdf.format(resultdate2);


            createNotificationChannel();
            Intent intent = new Intent(this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, intent, 0);

            NotificationCompat.Builder builder = new
                    NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_sms)
                    .setContentTitle("dateInString2")
                    .setContentText("Revenue License Renewal due on:" + dateInString + "Insurance Renewal due on: " + dateInString2)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
        }
    }

    private void createNotificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name ="Personnal Notifications";
            String description ="Include all the personnal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
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