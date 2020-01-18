package com.example.myfurniturestore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.cartsViewHolder> {


    Context mContext;
    List<carts> cartsList;
    //    Item itemModel;
    Bitmap bitmap;
    public static final String BASE_URL = "http://10.0.2.2:3000/";


    public CartAdapter(Context mContext, List<carts> cartsList) {
        this.mContext = mContext;
        this.cartsList = cartsList;
    }



    @NonNull
    @Override
    public cartsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_cartview,viewGroup,false);
        return new cartsViewHolder(view);
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull cartsViewHolder cartsViewHolder, final int i) {

        final carts itemModel = cartsList.get(i);


        cartsViewHolder.cartitemname.setText(itemModel.getProduct_name());
        cartsViewHolder.cartitemprice.setText(itemModel.getPrice());
        cartsViewHolder.cartitemdescription.setText(itemModel.getProduct_desc());
        cartsViewHolder.cartquantity.setText(itemModel.getQuantity());
        StrictMode();
        String images = cartsList.get(i).getImage();
        final String path = BASE_URL+"uploads"+"/"+images;
        Log.d("Groceries", "onBindViewHolder: " + cartsList.get(i).getImage());
        System.out.println("Path: " +path);

        try {
            URL uri = new URL(path);
            bitmap = BitmapFactory.decodeStream((InputStream)uri.getContent());
            cartsViewHolder.cartimgview.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cartsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, Cartdetailsactivity.class);
                intent.putExtra("image", itemModel.getImage());
                intent.putExtra("name", itemModel.getProduct_name());
                intent.putExtra("price", itemModel.getPrice());
                intent.putExtra("description", itemModel.getProduct_desc());
                intent.putExtra("quantity", itemModel.getQuantity());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return cartsList.size();
    }

    public class cartsViewHolder extends RecyclerView.ViewHolder{
        CircleImageView cartimgview;
        TextView cartitemname,cartitemprice,cartitemdescription, cartquantity;

        public cartsViewHolder(@NonNull View cartView) {
            super(cartView);

            cartimgview=itemView.findViewById(R.id.cartimgview);
            cartitemname=itemView.findViewById(R.id.cartname);
            cartitemprice=itemView.findViewById(R.id.cartprice);
            cartitemdescription=itemView.findViewById(R.id.cartdescription);
            cartquantity=itemView.findViewById(R.id.cartitemquantity);
        }
    }

}
