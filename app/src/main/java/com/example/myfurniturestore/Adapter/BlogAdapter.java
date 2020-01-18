package com.example.myfurniturestore.Adapter;

import android.content.Context;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.blogViewHolder> {

    Context mContext;
    List<blog> blogList;

    public static final String BASE_URL = "http://10.0.2.2:3000/";


    public BlogAdapter(Context mContext, List<blog> blogList) {
        this.mContext = mContext;
        this.blogList = blogList;
    }