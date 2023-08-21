package com.example.mywallpapers.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.mywallpapers.R;
import com.example.mywallpapers.photo;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class Randomadapter extends RecyclerView.Adapter<viewholderround>{

    private Context context;
    ArrayList<bestmodleclass> array;


    public Randomadapter (Context context,ArrayList<bestmodleclass> array) {
        this.context = context;
        this.array=array;

    }

    @NonNull
    @Override
    public viewholderround onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vw= LayoutInflater.from(context).inflate(R.layout.colorcard2,parent,false);
       viewholderround v=new viewholderround(vw);

        return v;


    }

    @Override
    public void onBindViewHolder(@NonNull viewholderround holder, int position) {





            Glide.with(context).load(array.get(position).getTiny()).placeholder(R.drawable.place2222).diskCacheStrategy(DiskCacheStrategy.ALL).transition(DrawableTransitionOptions.withCrossFade()).into(holder.roundImage);
            holder.roundImage.setOnClickListener(new View.OnClickListener() {
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
class viewholderround extends RecyclerView.ViewHolder {


        RoundedImageView roundImage;
    public viewholderround(@NonNull View itemView) {
        super(itemView);


          roundImage=itemView.findViewById(R.id.Roundimage);
    }
}
