package com.example.istream;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {user.class, Playlist.class}, version = 1)
public abstract class Databaseistream extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract PlaylistDao playlistDao();
}