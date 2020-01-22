package com.example.myfurniturestore.interfaces;

import java.util.List;

public interface CartInterface {
    @POST("blog")
    Call<Void> addblog(@Body blog blog);

    @GET("/blog")
    Call<List<blog>> getAllblogs();
}
