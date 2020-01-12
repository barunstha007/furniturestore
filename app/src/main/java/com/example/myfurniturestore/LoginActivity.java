package com.example.myfurniturestore;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import static android.content.Context.SENSOR_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;
import static androidx.core.content.ContextCompat.startActivity;

public class LoginActivity extends AppCompatActivity {

    SPshow(); public final static String TAG = "LoginActivity";
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private ImageButton btnRegister;
    private boolean isSuccess = false;
    Vibrator vibrator;
    public static final String USER_PREF = "USER_PREF" ;
    public static final String KEY_UNAME = "KEY_UNAME";
    public static final String KEY_PWD = "KEY_PWD";
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        notificationManager =NotificationManagerCompat.from(this);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector shakeDetector = new ShakeDetector(this);
        shakeDetector.start(sensorManager);

        vibrator =(Vibrator) getSystemService(VIBRATOR_SERVICE);
        etUsername = findViewById(R.id.emailLogin);
        etPassword = findViewById(R.id.passwordLogin);
        btnLogin=findViewById(R.id.btnSignin);
        btnRegister=findViewById(R.id.btngotoRegister);

        getNetworkType();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoginBLL bll = new LoginBLL(etUsername.getText().toString(), etPassword.getText().toString());
                StrictModeActivity.StrictMode();
                vibrator.vibrate(100);
                if (bll.checkUser()) {
                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                    sendOnChannel1();
                    SPsave();
                    Toast.makeText(getApplicationContext(), "Login Successfull!!", Toast.LENGTH_SHORT).show();


                } else {

                    Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();

                }
            }
        });





    }
