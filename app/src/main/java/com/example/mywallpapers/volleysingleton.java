package com.example.mywallpapers;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class volleysingleton {

    private static volleysingleton instance;
    private RequestQueue rqueue;


    private volleysingleton(Context context){
              rqueue= Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized volleysingleton getInstance(Context context){
          if(instance==null){
              instance=new volleysingleton(context);
          }
          return instance;
    }

    public RequestQueue getRqueue() {
        return rqueue;
    }
}
