package com.example.myfurniturestore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText firstname,lastname,username,password;
    ImageButton gotologin;
    Button regbutton;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2:
                Toast.makeText(this,"Logged out successfully", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstname=findViewById(R.id.regFirstname);
        lastname=findViewById(R.id.regLastname);
        username=findViewById(R.id.regEmail);
        password=findViewById(R.id.regPassword);
        regbutton=findViewById(R.id.btnSignup);
        gotologin=findViewById(R.id.gotologin);

        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });


    }


    private void signup(){


        Log.d("hello", "Click bhayo!");


        StrictModeActivity.StrictMode();

        RegisterBLL registerBLL = new RegisterBLL(firstname.getText().toString(),lastname.getText().toString(), username.getText().toString(),password.getText().toString());
        if(registerBLL.registerUser())
        {

            Toast.makeText(getApplicationContext(),"Register Success",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error login",Toast.LENGTH_SHORT).show();
        }
    }


}
