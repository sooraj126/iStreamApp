package com.example.istream;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlaylistDao {

    @Insert
    void insertVideo(Playlist playlist);

    @Query("SELECT * FROM playlist WHERE userId = :userId")
    List<Playlist> getUserPlaylist(int userId);
}
