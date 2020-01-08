package com.example.myfurniturestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class BlogdetailsActivity {
    TextView topic, description, blogby;

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
                Intent logoutIntent = new Intent(BlogdetailsActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
                Toast.makeText(this,"Logged out successfully", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_details);

        topic = findViewById(R.id.blogtopic);
        description = findViewById(R.id.blogdescription);
        blogby = findViewById(R.id.blogby);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null){

            topic.setText(bundle.getString("Topic"));
            description.setText(bundle.getString("Description"));
            blogby.setText(bundle.getString("BlogBy"));

        }
    }
}
