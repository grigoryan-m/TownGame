package com.example.towngame.roles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.towngame.R;
import com.example.towngame.playerSelection.Player;

import java.util.List;

public class PlayerDisplayAdapter extends RecyclerView.Adapter<PlayerDisplayAdapter.ViewHolder> {
    private List<Player> players;

    public PlayerDisplayAdapter(List<Player> players) {
        this.players = players; // Initialize with the list of players
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each player item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player player = players.get(position);
        holder.playerName.setText(player.getName()); // Set the player's name
        // You can set the player's icon here if applicable
        // holder.playerIcon.setImageResource(player.getIconResourceId()); // Example for setting an icon
    }

    @Override
    public int getItemCount() {
        return players.size(); // Return the number of players
    }

    // Method to update the player list and notify changes
    public void updatePlayers(List<Player> newPlayers) {
        this.players.clear();
        this.players.addAll(newPlayers);
        notifyDataSetChanged(); // Notify the adapter of data changes
    }

    // ViewHolder class to hold player views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView playerName;
        // ImageView playerIcon; // Uncomment if you have an icon

        public ViewHolder(View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            // playerIcon = itemView.findViewById(R.id.playerIcon); // Uncomment if you have an icon
        }
    }
}
