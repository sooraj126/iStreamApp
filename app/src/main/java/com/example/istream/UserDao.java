package com.example.istream;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void insertUser(user user);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    user loginUser(String username, String password);

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    user getUserByUsername(String username);
}