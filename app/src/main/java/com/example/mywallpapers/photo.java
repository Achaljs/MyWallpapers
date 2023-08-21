package com.example.mywallpapers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.mywallpapers.fragments.bestmodleclass;
import com.example.mywallpapers.fragments.downloadFragment;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class photo extends AppCompatActivity {
    PhotoView pview;
    FloatingActionButton set;
    String url,tinyurl;

boolean chek=false;
int position=0;

    public static ArrayList<bestmodleclass> array=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
         pview=findViewById(R.id.photoview);
        Intent it=getIntent();
        url=it.getStringExtra("portraitURL");
        tinyurl=it.getStringExtra("tinyURL");


        Glide.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(pview);

        set=findViewById(R.id.setButton);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WallpaperManager wallpaperManager=WallpaperManager.getInstance(photo.this);
                BitmapDrawable bitmapDrawable=(BitmapDrawable)pview.getDrawable();
                Bitmap bmp= bitmapDrawable.getBitmap();
                try {
                    wallpaperManager.setBitmap(bmp);
                    Toast.makeText(photo.this, "Done", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        pview.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(@NonNull MotionEvent motionEvent) {

                return false;
            }

            @Override
            public boolean onDoubleTap(@NonNull MotionEvent motionEvent) {
for(int i=0;i<array.size();i++) {
    if (array.get(i).getUrl().equals(url)) {
        chek = true;
         position=i;
        break;
    } else {

        chek = false;
    }

}

if(chek==true){

    ondelete(array.get(position).getRoomid());
    Toast.makeText(photo.this, "Already Added!", Toast.LENGTH_SHORT).show();
}
else {

    Toast.makeText(photo.this,"Added to favourite!", Toast.LENGTH_SHORT).show();
array.add(new bestmodleclass(url,url));

    addtodatabase(url,url);

}

                return true;
            }

            @Override
            public boolean onDoubleTapEvent(@NonNull MotionEvent motionEvent) {

                return false;
            }
        });


    }


    public void download(View view) {



        String DIR_NAME="MyWallpapers";
        File direct =
                new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .getAbsolutePath() + "/" + DIR_NAME + "/");


        if (!direct.exists()) {
            direct.mkdir();

        }
        String name=url.substring(40,59);

        DownloadManager db=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri=Uri.parse(url);
         DownloadManager.Request request= new DownloadManager.Request(uri);
         request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)  .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,
                 File.separator + DIR_NAME+File.separator+"Wallpaper.jpg");
         db.enqueue(request);

        Toast.makeText(this, "Downloading....", Toast.LENGTH_SHORT).show();


    }
    public void addtodatabase(String time,String task){
        databasehelper db=databasehelper.getdb(photo.this);
        bestmodleclass rdb=new bestmodleclass(time,task);
        db.tDao().addid(rdb);
    }


    public void ondelete(int id) {
        databasehelper db=databasehelper.getdb(photo.this);
        db.tDao().delete(id);
    }
}