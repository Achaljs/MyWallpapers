package com.example.mywallpapers.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mywallpapers.R;
import com.example.mywallpapers.volleysingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {

    RecyclerView bestRV,colorRV,categaryRV;
    homeRVadapder rVadapder;
    String your_API_key="";
    demoRVadapter colorRVadapter,catRVadapter;
    ArrayList <bestmodleclass> wallpaperarray=new ArrayList<>();
    ArrayList <bestmodleclass> colorarray=new ArrayList<>();
    ArrayList <bestmodleclass> catarray=new ArrayList<>();
    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);
         bestRV=v.findViewById(R.id.BestRV);
         colorRV=v.findViewById(R.id.colorRV);
        categaryRV=v.findViewById(R.id.catRV);


         bestRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
         rVadapder=new homeRVadapder(getContext(),wallpaperarray,R.layout.rvcard);
        bestRV.setAdapter(rVadapder);
            fetchwallpaper();




        setcolorDemo();

        catarray.add(new bestmodleclass(R.drawable.ab,"Abstract"));
        catarray.add(new bestmodleclass(R.drawable.nature,"Nature"));
        catarray.add(new bestmodleclass(R.drawable.cars,"Cars"));
        catarray.add(new bestmodleclass(R.drawable.blak,"Background"));
        catarray.add(new bestmodleclass(R.drawable.classic,"Classic"));
        catarray.add(new bestmodleclass(R.drawable.animals,"Animals"));
        catarray.add(new bestmodleclass(R.drawable.girl,"Girl"));
        catarray.add(new bestmodleclass(R.drawable.man,"Man"));

        categaryRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        catRVadapter=new demoRVadapter( getContext(),catarray,R.layout.catogarypallet);
        categaryRV.setAdapter(catRVadapter);



         return v;
    }







    public void fetchwallpaper(){

        StringRequest request=new StringRequest(Request.Method.GET, "https://api.pexels.com/v1/curated?page=1&per_page=80", new Response.Listener<String>() {
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
                        wallpaperarray.add(bst);
                        rVadapder.notifyDataSetChanged();
                    }



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();

                params.put("Authorization",your_API_key);


                return params;
            }
        };

        RequestQueue rque= volleysingleton.getInstance(getContext()).getRqueue();
         rque.add(request);
    }









    public void setcolorDemo(){

        colorarray.add(new bestmodleclass(R.drawable.red,"Red"));
        colorarray.add(new bestmodleclass(R.drawable.gren,"green"));
        colorarray.add(new bestmodleclass(R.drawable.yellow,"yellow"));
        colorarray.add(new bestmodleclass(R.color.black,"black"));
        colorarray.add(new bestmodleclass(R.drawable.white,"white"));
        colorarray.add(new bestmodleclass(R.drawable.blue,"blue"));
        colorarray.add(new bestmodleclass(R.drawable.pinkd,"pink"));
        colorarray.add(new bestmodleclass(R.color.orange,"orange"));
        colorarray.add(new bestmodleclass(R.color.purple,"purple"));


        colorRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        colorRVadapter=new demoRVadapter( getContext(),colorarray,R.layout.colorcard);
        colorRV.setAdapter(colorRVadapter);

    }




}



