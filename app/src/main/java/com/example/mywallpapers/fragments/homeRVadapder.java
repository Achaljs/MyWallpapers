package com.example.mywallpapers.fragments;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.mywallpapers.R;
import com.example.mywallpapers.photo;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class homeRVadapder extends RecyclerView.Adapter<viewholder>{

    private Context context;
    ArrayList<bestmodleclass> array;
    int layout;

    public homeRVadapder(Context context,ArrayList<bestmodleclass> array,int layout) {
        this.context = context;
        this.array=array;
        this.layout=layout;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(layout,parent,false);
       viewholder v=new viewholder(view);

        return v;


    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

            Glide.with(context).load(array.get(position).getTiny())
                    .diskCacheStrategy(DiskCacheStrategy.ALL).transition(DrawableTransitionOptions.withCrossFade()).into(holder.img);
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(context, photo.class);
                    it.putExtra("portraitURL", array.get(holder.getAdapterPosition()).getUrl());
                    it.putExtra("tinyURL", array.get(holder.getAdapterPosition()).getTiny());
                    context.startActivity(it);
                }
            });
        }


    @Override
    public int getItemCount() {
        return array.size();
    }
}
class viewholder extends RecyclerView.ViewHolder {

        ImageView img;

    public viewholder(@NonNull View itemView) {
        super(itemView);

         img=itemView.findViewById(R.id.img);

    }
}
