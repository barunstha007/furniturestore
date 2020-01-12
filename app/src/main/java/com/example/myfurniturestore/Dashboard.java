package com.example.myfurniturestore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListner;
    Button btngroceries, btnaboutus, blogs, addblogs, viewcarts, viewprofile;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item2:
                Intent logoutIntent = new Intent(Dashboard.this, LoginActivity.class);
                startActivity(logoutIntent);
                Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        btngroceries = findViewById(R.id.btnGroceries);
        btnaboutus = findViewById(R.id.btnAboutus);
        blogs = findViewById(R.id.btnblogs);
        addblogs = findViewById(R.id.btnnewblogs);
        viewcarts = findViewById(R.id.btnviewcarts);
        viewprofile=findViewById(R.id.btnviewprofile);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);


        btngroceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShowItemsActivity.class));
            }
        });

        btnaboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AboutusActivity.class));
            }
        });

        blogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BlogActivity.class));
            }
        });

        addblogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddBlogsActivity.class));
            }
        });
        viewcarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShowCartsActivity.class));
            }
        });

        viewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UpdateActivity.class));
            }
        });

        if (proximitySensor == null) {
            Toast.makeText(this, "not availavble", Toast.LENGTH_SHORT).show();
            finish();
        }
        proximitySensorListner = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorevent) {
                if (sensorevent.values[0] < proximitySensor.getMaximumRange()) {
                    Intent logoutIntent = new Intent(Dashboard.this, LoginActivity.class);
                    startActivity(logoutIntent);

                } else {
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(proximitySensorListner,proximitySensor,2*1000*1000);
        Gyroscope();
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(proximitySensorListner);
    }

    public void Gyroscope() {

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        SensorEventListener gyroEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[2] > 0.5f) {        // anticlockwise
                    Intent logoutIntent = new Intent(Dashboard.this, ShowItemsActivity.class);
                    startActivity(logoutIntent);

                    Log.d("gyro", "tilted left");

                    Toast.makeText(Dashboard.this, "tilted left", Toast.LENGTH_SHORT).show();
                } else if (event.values[2] < -0.5f) {     // clockwise
                    Intent logoutIntent = new Intent(Dashboard.this, BlogActivity.class);
                    Toast.makeText(Dashboard.this, "right tilted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
//    register listener
        sensorManager.registerListener(gyroEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
}
