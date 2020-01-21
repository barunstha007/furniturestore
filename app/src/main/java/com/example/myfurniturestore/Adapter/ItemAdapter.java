package com.example.myfurniturestore.Adapter;

import android.content.ClipData;
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

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    Context mContext;
    List<ClipData.Item> itemList;
    //    Item itemModel;
    Bitmap bitmap;
    public static final String BASE_URL = "http://10.0.2.2:3000/";


    public ItemAdapter(Context mContext, List<ClipData.Item> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }



    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view,viewGroup,false);
        return new ItemViewHolder(view);
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int i) {

        final ClipData.Item itemModel = itemList.get(i);


        itemViewHolder.itemname.setText(itemModel.getProduct_name());
        itemViewHolder.itemprice.setText(itemModel.getPrice());
        itemViewHolder.itemdescription.setText(itemModel.getProduct_desc());
        itemViewHolder.quantity.setText(itemModel.getQuantity());
        StrictMode();
        String images = itemList.get(i).getImage();
        final String path = BASE_URL+"uploads"+"/"+images;
        Log.d("Groceries", "onBindViewHolder: " + itemList.get(i).getImage());
        System.out.println("Path: " +path);

        try {
            URL uri = new URL(path);
            bitmap = BitmapFactory.decodeStream((InputStream)uri.getContent());
            itemViewHolder.imgview.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ItemDetailsActivity.class);
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
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgview;
        TextView itemname,itemprice,itemdescription, quantity;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgview=itemView.findViewById(R.id.imgview);
            itemname=itemView.findViewById(R.id.name);
            itemprice=itemView.findViewById(R.id.price);
            itemdescription=itemView.findViewById(R.id.description);
            quantity=itemView.findViewById(R.id.itemquantity);
        }
    }

}

