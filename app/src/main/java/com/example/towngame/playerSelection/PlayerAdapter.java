package com.example.towngame.playerSelection;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.towngame.GameManager;
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
    public static List<Player> staticPlayers;
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
            // Покажем диалоговое окно для редактирования и удаления игрока
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Редактировать или удалить игрока");

            // Создаем поле ввода для редактирования имени
            EditText input = new EditText(context);
            input.setText(player.getName());
            builder.setView(input);

            // Устанавливаем кнопки
            builder.setPositiveButton("Сохранить", (dialog, which) -> {
                String newName = input.getText().toString();
                if (!newName.isEmpty()) {
                    player.setName(newName); // Update the player's name
                    notifyItemChanged(position); // Notify that the item has changed
                }
            });

            builder.setNegativeButton("Удалить", (dialog, which) -> {
                removePlayer(position); // Remove the player
            });

            builder.setNeutralButton("Отмена", null);

            builder.show();
        });
    }

    // Метод для удаления игрока
    public void removePlayer(int position) {
        if (position >= 0 && position < players.size()) {
            players.remove(position);
            GameManager.players.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, players.size());
        }
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

    public void savePlayers(Context context) {
        try (FileOutputStream fOut = context.openFileOutput("players.dat", Context.MODE_PRIVATE);
             ObjectOutputStream out = new ObjectOutputStream(fOut)) {
            out.writeObject(players);
            Log.d("SUC", "Saved players!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            staticPlayers = new ArrayList<>(players);
        }

    }

    public void loadPlayers(Context context) {
        try {
            FileInputStream fis = context.openFileInput("players.dat");
            ObjectInputStream in = new ObjectInputStream(fis);
            players = (ArrayList<Player>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            notifyDataSetChanged();
        }
    }
}
