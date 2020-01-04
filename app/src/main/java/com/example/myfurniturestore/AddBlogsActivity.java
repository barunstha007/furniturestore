package com.example.myfurniturestore;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AddBlogsActivity {
    public EditText topic, description, firstname;
    public Button add;
    Uri uri;
    Bitmap bitmap;
    Retrofit retrofit;
    BlogInterface blogInterface;



    private NotificationManagerCompat notificationManager;
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
                Intent logoutIntent = new Intent(AddBlogsActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
                Toast.makeText(this,"Logged out successfully", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public  String BASE_URL = "http://10.0.2.2:3000/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addblog);
        notificationManager =NotificationManagerCompat.from(this);
        topic = findViewById(R.id.addblogtopic);
        description = findViewById(R.id.addblogdescription);
        firstname=findViewById(R.id.addfirstname);

        add = findViewById(R.id.btnAddblogs);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
                sendOnChannel1();
            }
        });
    }

    public void sendOnChannel1(){
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.profile)
                .setContentTitle("Blog added!! ")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1,notification);
    }


    public void addItem(){
        if (nullValidation()) {

            String sname = topic.getText().toString();
            String sdescription = description.getText().toString();
            String sfirstname = firstname.getText().toString();

            blog blog = new blog(sname,sdescription, sfirstname);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            BlogInterface blogInterface = retrofit.create(BlogInterface.class);

            Call<Void> call = blogInterface.addblog(blog);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("VAL", "success ");

                    if(response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Blog ADDED", Toast.LENGTH_SHORT).show();

                        Log.d("VAL", "success response ");

                        startActivity(new Intent(getApplicationContext(), BlogActivity.class));
                    }else {
                        try{
                            Log.d("VAL", response.toString());

                            Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });

        }
    }

    public boolean nullValidation(){
        if (TextUtils.isEmpty(topic.getText().toString())){
            topic.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(description.getText().toString())){
            description.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(firstname.getText().toString())){
            firstname.setError("Required Field");
            return false;
        }

        return true;
    }



}
