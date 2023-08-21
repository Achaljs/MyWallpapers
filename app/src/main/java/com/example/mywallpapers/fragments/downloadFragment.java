package com.example.mywallpapers.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mywallpapers.MainActivity;
import com.example.mywallpapers.R;
import com.example.mywallpapers.databasehelper;
import com.example.mywallpapers.photo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class downloadFragment extends Fragment {

    RecyclerView downloadRV;
    ArrayList<String> array = new ArrayList<>();

    Randomadapter likeAdapter;

    ArrayList<bestmodleclass> likearray=new ArrayList<>();
    String url;
    String tiny;
    public downloadFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_download, container, false);




        //ArrayList<bestmodleclass> likearray = photo.array;

       fetch();

        downloadRV = v.findViewById(R.id.downloadRV);

        downloadRV.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        likeAdapter=new Randomadapter(getContext(),likearray);

        downloadRV.setAdapter(likeAdapter);



        return v;
    }

//    public void addDownload() {
//
//
//        String DIR_NAME = "MyWallpapers";
//        File targetpath =
//                new File(Environment
//                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//                        .getAbsolutePath() + "/" + DIR_NAME + "/");
//
//        File[] allfiles = targetpath.listFiles();
//        for (int i = 0; i < allfiles.length; i++) {
//                array.add(allfiles[i].getAbsolutePath());
//                rvadapter.notifyDataSetChanged();
//        }

    public void fetch(){
        databasehelper db=databasehelper.getdb(getContext());

        List<bestmodleclass> dataarr=db.tDao().getall();


        for(int i = 0; i<dataarr.size(); i++) {

            likearray.add(dataarr.get(i));
        }
    }


    }


