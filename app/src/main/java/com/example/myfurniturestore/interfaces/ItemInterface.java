package com.example.myfurniturestore.interfaces;

import java.util.List;

public interface ItemInterface {
    @POST("furnitures")
    Call<Void> addItem(@Body Item item);

//    @FormUrlEncoded
//    @POST("items")
//    Call<Void> addItem(@Field("name") String name, @Field())

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("/furnitures")
    Call<List<Item>> getAllItems();


}
