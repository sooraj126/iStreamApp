package com.example.istream;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "playlist")
public class Playlist {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String videoUrl;
    public int userId;
}