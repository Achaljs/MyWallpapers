package com.example.mywallpapers;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mywallpapers.fragments.bestmodleclass;

@Database(entities = bestmodleclass.class,version = 4)
public abstract class databasehelper extends RoomDatabase {
private static final String dbname="roomdatabase";
    private static databasehelper  instance;

    public static synchronized databasehelper getdb(Context context){
         if(instance==null)
         {
             instance= Room.databaseBuilder(context,databasehelper.class,dbname).fallbackToDestructiveMigration().allowMainThreadQueries().build();
         }
         return instance;
    }


    public abstract taskDao tDao();


}



