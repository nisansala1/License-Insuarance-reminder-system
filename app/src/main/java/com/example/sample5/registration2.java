package com.example.sample5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class registration2 extends AppCompatActivity {
    private final String CHANNEL_ID="personal notification";
    private final int NOTIFICATION_ID=001;
    TextView t1,t2,t3;
    EditText e1,e2;
    DatabaseHelper db;
    RadioButton r1,r2,r3,r4,r5,r6;
    DatePickerDialog picker;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        db=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.date1);
        e2=(EditText)findViewById(R.id.date2);
        r1=(RadioButton)findViewById(R.id.radi01);
        r2=(RadioButton)findViewById(R.id.radio2);
        r3=(RadioButton)findViewById(R.id.radio3);
        r4=(RadioButton)findViewById(R.id.radio4);
        r5=(RadioButton)findViewById(R.id.radio5);
        r6=(RadioButton)findViewById(R.id.radio6);
        b1=(Button)findViewById(R.id.b);

        t1=(TextView)findViewById(R.id.ht1);
        t2=(TextView)findViewById(R.id.ht2);
        t3=(TextView)findViewById(R.id.ht3);


        AddData();


        e1.setInputType(InputType.TYPE_NULL);
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(registration2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        e1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        e2.setInputType(InputType.TYPE_NULL);
        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(registration2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        e2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });


    }
    public  void AddData() {
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String s0 = getIntent().getStringExtra("EXTRA_SESSION_ID");

                        String s1 = e1.getText().toString();
                        String s2 = e2.getText().toString();
                        String s3 = t1.getText().toString();
                        String s4 = t2.getText().toString();
                        String s5 = t3.getText().toString();
                        if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")) {

                            Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();

                        }else{
                        boolean isInserted = db.insertsec(s0, s1, s2,s3,s4,s5);
                        if (isInserted == true){
                            Toast.makeText(registration2.this, "Added the data successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);

                    }
                        else
                            Toast.makeText(registration2.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }}
                }
        );
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radi01:
                if (checked)
                   t1.setText(" Done the Eco Test ");
                EcoNotif(view);
                    break;
            case R.id.radio2:
                if (checked)
                    t1.setText(" Not Done the Eco Test ");
                EcoNotif(view);
                    break;

            case R.id.radio3:
                if (checked)
                    t2.setText(" Done the Carbon Test ");
                CarbonNotif(view);
                    break;
            case R.id.radio4:
                if (checked)
                    t2.setText(" Not Done the Carbon Test ");
                CarbonNotif(view);
                    break;

            case R.id.radio5:
                if (checked)
                    t3.setText(" Done the Fitness Test ");
                FitnessNotif(view);
                break;
            case R.id.radio6:
                if (checked)
                    t3.setText(" Not Done the Fitness Test ");
                FitnessNotif(view);
                break;
        }

    }
    public void EcoNotif(View view) {
        createNotificationChannel();
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_sms)
                        .setContentTitle("Eco Test")
                        .setContentText("Eco Test tests the Vehicle Emission levels")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Eco Tests must be done on an yearly basis by the user." +
                                        "If not, the vehicle emission levels can exceed permissable levels. If this happens the user could be fined. "))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }

    public void CarbonNotif(View view) {
        createNotificationChannel();
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_sms)
                        .setContentTitle("Carbon Test")
                        .setContentText("Carbon Test tests the Vehicle CO levels")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Carbon Tests must be done on an yearly basis by the user." +
                                        "If not, the vehicle CO levels can exceed permissable levels. If this happens the user could be fined. "))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }

    public void FitnessNotif(View view) {
        createNotificationChannel();
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_sms)
                        .setContentTitle("Fitness Test")
                        .setContentText("Tests if vehicle up to standard to use on the road.")
                        .setStyle(new NotificationCompat.BigTextStyle()

                                .bigText("It is applicable to all heavy vehicles." +
                                        "Fitness Tests must be done on an yearly basis by the user to ensure their vehicle is fit to carry passengers."))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }

    private void createNotificationChannel(){

        // the NotificationChannel class is new and not in the support library
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
}