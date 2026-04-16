package com.example.istream;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    List<Playlist> playlistList;

    OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Playlist playlist);
    }

    public PlaylistAdapter(List<Playlist> playlistList, OnItemClickListener listener) {
        this.playlistList = playlistList;
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtVideoUrl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVideoUrl = itemView.findViewById(R.id.txtVideoUrl);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(playlistList.get(getAdapterPosition()));
                }
            });
        }
    }

    @NonNull
    @Override
    public PlaylistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.ViewHolder holder, int position) {
        Playlist playlist = playlistList.get(position);
        holder.txtVideoUrl.setText(playlist.videoUrl);
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }
}