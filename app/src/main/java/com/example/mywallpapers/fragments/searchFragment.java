package com.example.mywallpapers.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mywallpapers.MainActivity;
import com.example.mywallpapers.R;
import com.example.mywallpapers.randomWallpaper;
import com.example.mywallpapers.volleysingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class searchFragment extends Fragment {

    ArrayList<bestmodleclass> arraySearch=new ArrayList<>();
    RecyclerView searchrv;
    Randomadapter RVadapter;
    EditText searchbar;
    String your_API_key="";

String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public searchFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_search, container, false);
TextView txt=view.findViewById(R.id.searchText);
        searchrv=view.findViewById(R.id.searchRV);
        searchrv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        searchbar=view.findViewById(R.id.editHeadersearch);



        RVadapter=new Randomadapter(getContext(),arraySearch);
        searchrv.setAdapter(RVadapter);

        fetchRandom("random");

        searchbar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH
                || actionId == EditorInfo.IME_ACTION_DONE
                || event.getAction() == KeyEvent.ACTION_DOWN
                ) {
            Toast.makeText(getContext(), "Searching,Please Wait...", Toast.LENGTH_LONG).show();
txt.setText("Search:"+v.getText().toString());
            arraySearch.clear();
            RVadapter.notifyDataSetChanged();



            fetchRandom(v.getText().toString());


            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            return true;
        }
        return false;
    }
});



        return view;
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
                        arraySearch.add(bst);
                        RVadapter.notifyDataSetChanged();
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
}

