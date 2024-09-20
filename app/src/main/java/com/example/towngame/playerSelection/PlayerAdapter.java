package com.example.towngame.playerSelection;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.towngame.R;
import com.example.towngame.activities.PlayerSelectionActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

        // Обработчик клика для удаления игрока
        holder.itemView.setOnClickListener(v -> {
            // Покажем диалоговое окно для подтверждения удаления
            new AlertDialog.Builder(context)
                    .setTitle("Удалить игрока")
                    .setMessage("Вы уверены, что хотите удалить этого игрока?")
                    .setPositiveButton("Удалить", (dialog, which) -> {
                        // Удаляем игрока из списка и обновляем RecyclerView
                        removePlayer(position);
                    })
                    .setNegativeButton("Отмена", null)
                    .show();
        });
    }

    // Метод для удаления игрока
    private void removePlayer(int position) {
        players.remove(position);
        notifyItemRemoved(position);  // Сообщаем адаптеру, что элемент удален
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView playerName;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
        }
    }

    public int getItemCount() {
        return players.size();
    }

    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(players, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void savePlayers(Context context) {
        try (FileOutputStream fOut = context.openFileOutput("players.dat", Context.MODE_PRIVATE);
             ObjectOutputStream out = new ObjectOutputStream(fOut)) {
            out.writeObject(players);
            Log.d("SUC", "Saved players!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPlayers(Context context) {
        try (FileInputStream fis = context.openFileInput("players.dat");
             ObjectInputStream in = new ObjectInputStream(fis)) {
            players = (ArrayList<Player>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            players = new ArrayList<>();
            e.printStackTrace();
        }
    }



}
    class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView playerName;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
        }
    }
