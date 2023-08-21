package com.example.mywallpapers;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mywallpapers.fragments.bestmodleclass;

import java.util.List;

@Dao
public interface taskDao {


    @Insert()
    void addid(bestmodleclass numbers);
    @Update()
    void updateid(bestmodleclass update);
    @Query( "DELETE FROM roomdatabase WHERE roomid = :id")
    void delete(int id);

    @Query("Select * from roomdatabase")
    List<bestmodleclass> getall();


}
