package com.example.towngame.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.example.towngame.playerSelection.Player;
import com.example.towngame.playerSelection.PlayerAdapter;
import com.example.towngame.R;
import com.example.towngame.playerSelection.PlayerItemTouchHelperCallback;
import com.example.towngame.playerSelection.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectionActivity extends AppCompatActivity {

    // Constants
    private final int MIN_PLAYERS = 1;

    private PlayerAdapter adapter;
    Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_player_selection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryVariant));

        //backToMenuButton

        Button startButton = findViewById(R.id.backToMenuButton); // Assuming this is your button's ID
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayerSelectionActivity.this, MainActivity.class);
                // Start the new activity with an animation
                startActivity(intent);
                overridePendingTransition(R.anim.slide_down, R.anim.slide_out_down);
            }
        });
        RecyclerView playerRecyclerView = findViewById(R.id.playerRecyclerView);
        playerRecyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // 3 колонки

        adapter = new PlayerAdapter(this, new ArrayList<>());
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing); // Define the spacing value in your resources
        playerRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        playerRecyclerView.setAdapter(adapter);
        // Add players button

        nextButton = (Button) findViewById(R.id.nextButton);
        updateNextButtonState();

        // Load players

        adapter.loadPlayers(this);
        adapter.notifyDataSetChanged();
    }


    public void showAddPlayerDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Введите имя игрока");

        // Создаем поле ввода
        final EditText input = new EditText(this);
        builder.setView(input);

        // Устанавливаем кнопки
        builder.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String playerName = input.getText().toString();
                if (!playerName.isEmpty()) {
                    addPlayer(playerName);
                }
            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }




    public void addPlayer(String name) {
        Log.d("YourActivity", "Кнопка нажата");
        // Создайте нового игрока (можно изменить имя или добавлять через ввод)
        Player newPlayer = new Player(name); // Уникальное имя для каждого игрока
        adapter.players.add(newPlayer); // Добавление игрока в список
        adapter.notifyDataSetChanged(); // Уведомление адаптера о том, что данные изменились
        updateNextButtonState();
    }
    private void updateNextButtonState(){
        if (adapter.players.size() < MIN_PLAYERS) {
            nextButton.setEnabled(false);
            nextButton.setAlpha(0.5f); // Сделаем кнопку полупрозрачной
        } else {
            nextButton.setEnabled(true);
            nextButton.setAlpha(1.0f); // Полная непрозрачность
        }
    }

    public void finishedOrganizingPlayers(View view){
        adapter.savePlayers(this);
    }


    public void openEditPlayerDialog(Player player, int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Новое имя:");

        // Создаем поле ввода
        final EditText input = new EditText(this);
        builder.setView(input);

        // Устанавливаем кнопки
        builder.setPositiveButton("Изменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String playerName = input.getText().toString();
                if (!playerName.isEmpty()) {
                    adapter.players.get(position).setName(playerName);
                }
            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView playerName;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
        }
    }
}