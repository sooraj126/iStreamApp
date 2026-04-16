package com.example.istream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class Homepage extends AppCompatActivity {

    EditText edtVideoUrl;
    Button btnPlay, btnAddToPlaylist, btnMyPlaylist, btnLogout;

    Databaseistream db;
    int userId;
    String username;

    WebView webViewVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_homepage);


        edtVideoUrl = findViewById(R.id.edtVideoUrl);
        btnPlay = findViewById(R.id.btnPlay);
        btnAddToPlaylist = findViewById(R.id.btnAddToPlaylist);
        btnMyPlaylist = findViewById(R.id.btnMyPlaylist);
        btnLogout = findViewById(R.id.btnLogout);

        webViewVideo = findViewById(R.id.webViewVideo);

        WebSettings webSettings = webViewVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webViewVideo.setWebViewClient(new WebViewClient());



        userId = getIntent().getIntExtra("userId", -1);
        username = getIntent().getStringExtra("username");

        String videoUrl = getIntent().getStringExtra("videoUrl");

        if (videoUrl != null) {
            edtVideoUrl.setText(videoUrl);
        }

        db = Room.databaseBuilder(getApplicationContext(),
                        Databaseistream.class, "iStreamDB")
                .allowMainThreadQueries()
                .build();


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoUrl = edtVideoUrl.getText().toString().trim();

                if (videoUrl.isEmpty()) {
                    Toast.makeText(Homepage.this, "Please enter a video URL", Toast.LENGTH_SHORT).show();
                } else {
                    if (videoUrl.contains("v=")) {
                        String videoId = videoUrl.split("v=")[1];

                        String html = "<html><body style='margin:0;padding:0;'>" +
                                "<iframe width='100%' height='100%' " +
                                "src='https://www.youtube.com/embed/" + videoId + "?playsinline=1' " +
                                "frameborder='0' allow='autoplay; encrypted-media' allowfullscreen>" +
                                "</iframe>" +
                                "</body></html>";


                        webViewVideo.loadDataWithBaseURL(
                                "https://www.youtube.com",
                                html,
                                "text/html",
                                "UTF-8",
                                null
                        );
                    } else {
                        Toast.makeText(Homepage.this, "Invalid YouTube URL", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnAddToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoUrl = edtVideoUrl.getText().toString().trim();

                if (videoUrl.isEmpty()) {
                    Toast.makeText(Homepage.this, "Please enter a video URL", Toast.LENGTH_SHORT).show();
                } else {
                    Playlist playlist = new Playlist();
                    playlist.videoUrl = videoUrl;
                    playlist.userId = userId;

                    db.playlistDao().insertVideo(playlist);

                    Toast.makeText(Homepage.this, "Added to playlist", Toast.LENGTH_SHORT).show();
                    edtVideoUrl.setText("");
                }
            }
        });

        btnMyPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, Playlistpage.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}