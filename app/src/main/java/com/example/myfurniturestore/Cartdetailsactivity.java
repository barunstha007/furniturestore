package com.example.myfurniturestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cartdetailsactivity extends AppCompatActivity {
    TextView cartname, cartdescription, cartprice, cartquantity;
    CircleImageView circleImageView;

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
                Intent logoutIntent = new Intent(Cartdetailsactivity.this, LoginActivity.class);
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
        setContentView(R.layout.activity_cart_details);

        cartname = findViewById(R.id.cartname);
        cartdescription = findViewById(R.id.cartdescription);
        cartprice = findViewById(R.id.cartprice);
        cartquantity = findViewById(R.id.cartitemquantity);
        circleImageView = findViewById(R.id.detailImgView);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String image = bundle.getString("image");
            Picasso.with(this).load("http://10.0.2.2:3000/uploads/" + image).into(circleImageView);
            cartname.setText(bundle.getString("name"));
            cartprice.setText(bundle.getString("price"));
            cartquantity.setText(bundle.getString("quantity"));
            cartdescription.setText(bundle.getString("description"));
        }
    }
}
