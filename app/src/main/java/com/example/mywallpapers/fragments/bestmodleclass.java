package com.example.mywallpapers.fragments;

import android.widget.ImageView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "roomdatabase")
public class bestmodleclass {

    @PrimaryKey(autoGenerate = true)
    private int roomid;

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }


    @ColumnInfo(name="url")
    String url;

    @ColumnInfo(name="tinyurl")
    String tiny;






    int Id;
    public bestmodleclass(int roomid,String url, String tiny) {
        this.url = url;
        this.roomid=roomid;
        this.tiny=tiny;
    }


    public bestmodleclass(String url, int id) {
        this.url = url;
        Id = id;
    }


    public String getTiny() {
        return tiny;
    }

    public void setTiny(String tiny) {
        this.tiny = tiny;
    }

    public bestmodleclass(String url, int id, String tiny) {
        this.url = url;
        Id = id;
        this.tiny=tiny;
    }


   @Ignore
    public bestmodleclass(String url, String tiny){
        this.url = url;
        this.tiny=tiny;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }




    int Image;
    String catogary;

    public bestmodleclass(int image,String catogary) {
        Image = image;
        this.catogary=catogary;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getCatogary() {
        return catogary;
    }

    public void setCatogary(String catogary) {
        this.catogary = catogary;
    }
}
