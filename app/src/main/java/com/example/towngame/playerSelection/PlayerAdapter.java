package com.example.towngame.playerSelection;

import static android.content.Context.MODE_PRIVATE;
import static android.os.ParcelFileDescriptor.MODE_READ_WRITE;
import static android.os.ParcelFileDescriptor.MODE_WORLD_READABLE;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.towngame.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    public List<Player> players;
    private Context context;

    public PlayerAdapter(Context context, List<Player> players) {
        this.context = context;
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.player_item, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = players.get(position);
        holder.playerName.setText(player.getName());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView playerName;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        // Меняем элементы местами
        Collections.swap(players, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void savePlayers(Context context) {
        File file = new File(context.getFilesDir(), "players.dat");

        try {

            FileOutputStream fOut = context.openFileOutput("players.dat", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            out.writeObject(players);
            out.close();
            fOut.close();
            Log.d("SUC", "Saved players!");
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void loadPlayers(Context context) {
        try {
            FileInputStream fis = context.openFileInput("players.dat");
            ObjectInputStream in = new ObjectInputStream(fis);
            players = (ArrayList<Player>) in.readObject();
            in.close(); // Close the input stream
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            players = new ArrayList<>();
            e.printStackTrace(); // Handle the exception properly
        }
    }

}
