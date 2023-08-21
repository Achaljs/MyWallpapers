package com.example.mywallpapers.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywallpapers.R;
import com.example.mywallpapers.photo;
import com.example.mywallpapers.randomWallpaper;

import java.util.ArrayList;

public class demoRVadapter extends RecyclerView.Adapter<viewholder2> {



    private Context context;
    ArrayList<bestmodleclass> array;
    int layout;

    public demoRVadapter(Context context,ArrayList<bestmodleclass> array,int layout) {
        this.context = context;
        this.array=array;
        this.layout=layout;
    }

    @NonNull
    @Override
    public viewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(layout,parent,false);
        viewholder2 vh=new viewholder2(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder2 holder, int position) {
             holder.img.setImageResource(array.get(position).getImage());
             if(holder.Cattxt!=null){
                 holder.Cattxt.setText(array.get(position).getCatogary());
             }
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(context, randomWallpaper.class);
                it.putExtra("cat",array.get(holder.getAdapterPosition()).getCatogary());
                context.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }
}
 class viewholder2 extends RecyclerView.ViewHolder{
     ImageView img;
     TextView Cattxt;
     public viewholder2(@NonNull View itemView) {
         super(itemView);
         img=itemView.findViewById(R.id.img);
         Cattxt=itemView.findViewById(R.id.catText);
     }
 }
