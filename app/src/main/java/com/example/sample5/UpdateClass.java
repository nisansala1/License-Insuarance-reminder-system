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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateClass extends AppCompatActivity {
    private final String CHANNEL_ID="personal notification";
    private final int NOTIFICATION_ID=001;
Button b;
    DatabaseHelper db;
    DatePickerDialog picker;
    EditText t1,t2;
    TextView th1,th2,th3;
    RadioButton radio1,radio2,radio3,radio4,radio5,radio6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_class);
        db = new DatabaseHelper(this);
        b=(Button)findViewById(R.id.b);
        t1=(EditText)findViewById(R.id.date1);
        t2=(EditText)findViewById(R.id.date2);
        th1=(TextView)findViewById(R.id.ht1);
        th2=(TextView)findViewById(R.id.ht2);
        th3=(TextView)findViewById(R.id.ht3);
        radio1=(RadioButton)findViewById(R.id.radio1);
        radio2=(RadioButton)findViewById(R.id.radio2);
        radio3=(RadioButton)findViewById(R.id.radio3);
        radio4=(RadioButton)findViewById(R.id.radio4);
        radio5=(RadioButton)findViewById(R.id.radio5);
        radio6=(RadioButton)findViewById(R.id.radio6);


        b.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String s0 = getIntent().getStringExtra("EXTRA_SESSION_ID");
                            boolean isUpdate = db.updateData(s0,
                                    t1.getText().toString(),
                                    t2.getText().toString(), th1.getText().toString(), th2.getText().toString(), th3.getText().toString());
                            if (isUpdate == true){
                                Toast.makeText(UpdateClass.this, "Data Update", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);}
                            else{
                                Toast.makeText(UpdateClass.this, "Data not Updated", Toast.LENGTH_LONG).show();
                            }

                        }
                    }
            );

        t1.setInputType(InputType.TYPE_NULL);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(UpdateClass.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        t1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        t2.setInputType(InputType.TYPE_NULL);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(UpdateClass.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        t2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio1:
                if (checked)
                    th1.setText(" Done the Eco Test ");
                EcoNotif(view);
                break;
            case R.id.radio2:
                if (checked)
                    th1.setText(" Not Done the Eco Test ");
                EcoNotif(view);
                break;

            case R.id.radio3:
                if (checked)
                    th2.setText(" Done the Carbon Test ");
                CarbonNotif(view);
                break;
            case R.id.radio4:
                if (checked)
                    th2.setText(" Not Done the Carbon Test ");
                CarbonNotif(view);
                break;

            case R.id.radio5:
                if (checked)
                    th3.setText(" Done the Fitness Test ");
                FitnessNotif(view);
                break;
            case R.id.radio6:
                if (checked)
                    th3.setText(" Not Done the Fitness Test ");
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