package com.example.mywallpapers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mywallpapers.fragments.Randomadapter;
import com.example.mywallpapers.fragments.bestmodleclass;
import com.example.mywallpapers.fragments.demoRVadapter;
import com.example.mywallpapers.fragments.homeRVadapder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class randomWallpaper extends AppCompatActivity {

    ArrayList <bestmodleclass>  array=new ArrayList<>();
    Randomadapter RVadapter;
    RecyclerView Rview;
    String your_API_Key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_wallpaper);
        TextView title=findViewById(R.id.title);
         Intent it=getIntent();
        String search=it.getStringExtra("cat");
        title.setText(search);
        Rview=findViewById(R.id.mainrv);
        Rview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
         RVadapter=new Randomadapter(this,array);

         Rview.setAdapter(RVadapter);
             fetchRandom(search);
    }

    public void fetchRandom(String search){

        StringRequest request=new StringRequest(Request.Method.GET, "https://api.pexels.com/v1/search?query="+search+"&page=1&per_page=80", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jOB=new JSONObject(response);
                    JSONArray jarry=jOB.getJSONArray("photos");

                    for(int i=0;i<jarry.length();i++) {

                        JSONObject object = jarry.getJSONObject(i);

                        int id=object.getInt("id");
                        JSONObject sourse = object.getJSONObject("src");

                        String portraiturl=sourse.getString("original");
                        String tinyurl=sourse.getString("portrait");

                        bestmodleclass bst=new bestmodleclass(portraiturl,id,tinyurl);
                        array.add(bst);

                    }
                    RVadapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("Authorization",your_API_Key);
                return params;
            }
        };

        RequestQueue rque= volleysingleton.getInstance(randomWallpaper.this).getRqueue();
        rque.add(request);
    }
}