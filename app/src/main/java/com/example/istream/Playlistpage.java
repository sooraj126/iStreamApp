package com.example.istream;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class Playlistpage extends AppCompatActivity {
    RecyclerView recyclerView;
    PlaylistAdapter adapter;
    Databaseistream db;

    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlistpage);

        recyclerView = findViewById(R.id.recyclerPlaylist);

        userId = getIntent().getIntExtra("userId", -1);

        db = Room.databaseBuilder(getApplicationContext(),
                        Databaseistream.class, "iStreamDB")
                .allowMainThreadQueries()
                .build();

        List<Playlist> playlistList = db.playlistDao().getUserPlaylist(userId);

        adapter = new PlaylistAdapter(playlistList, new PlaylistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Playlist playlist) {
                Intent intent = new Intent(Playlistpage.this, Homepage.class);
                intent.putExtra("userId", userId);
                intent.putExtra("videoUrl", playlist.videoUrl);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}