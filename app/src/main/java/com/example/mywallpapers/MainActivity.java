package com.example.mywallpapers;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mywallpapers.fragments.HomeFragment;
import com.example.mywallpapers.fragments.downloadFragment;
import com.example.mywallpapers.fragments.searchFragment;

public class MainActivity extends AppCompatActivity {
    LinearLayout bottombar;
    RelativeLayout head;

    int back=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         head=findViewById(R.id.header);
        bottombar=findViewById(R.id.bottomBar);
        LinearLayout mainframe=findViewById(R.id.mailframelayout);
        FrameLayout home=findViewById(R.id.home);
        FrameLayout down=findViewById(R.id.download);
        ImageView homeicon=findViewById(R.id.homeicon);
        ImageView downloadicon=findViewById(R.id.downloadIcon);
        EditText searchbar=findViewById(R.id.editHeader);


        loadFragment(new HomeFragment(),0);





        if(CheckNetwork.isInternetAvailable(MainActivity.this)==true) //returns true if internet available
        {

            mainframe.setVisibility(View.VISIBLE);
            head.setVisibility(View.VISIBLE);

        }
        else
        {
            mainframe.setVisibility(View.INVISIBLE);
            head.setVisibility(View.INVISIBLE);
        }

        searchbar.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        loadFragment(new searchFragment(),1);
        bottombar.setVisibility(View.GONE);
        head.setVisibility(View.GONE);
        back=0;
        return true;
    }
});


//searchbar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//    @Override
//    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//        if (actionId == EditorInfo.IME_ACTION_SEARCH
//                || actionId == EditorInfo.IME_ACTION_DONE
//                || event.getAction() == KeyEvent.ACTION_DOWN
//                ) {
//            Toast.makeText(MainActivity.this, v.getText().toString(), Toast.LENGTH_SHORT).show();
//            searchFragment sh=new searchFragment();
//sh.setStr(v.getText().toString());
//            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//            return true;
//        }
//        return false;
//    }
//});

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new HomeFragment(),1);
                head.setVisibility(View.VISIBLE);
                bottombar.setVisibility(View.VISIBLE);

            }
        });


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new downloadFragment(),1);
                head.setVisibility(View.GONE);

                back=0;
            }

        });



    }


    @Override
    public void onBackPressed() {
        if(back==0){
        loadFragment(new HomeFragment(),1);
        head.setVisibility(View.VISIBLE);
        bottombar.setVisibility(View.VISIBLE);
        back=1;
        }
        else{

            super.onBackPressed();
        }


    }

    public void loadFragment(Fragment fragment, int flag){
       FragmentManager fm=getSupportFragmentManager();
       FragmentTransaction ft=fm.beginTransaction();
       if(flag==0) {
           ft.add(R.id.mainframe, fragment);


       }
       else{
           ft.replace(R.id.mainframe, fragment);
       }



       ft.commit();


   }
}