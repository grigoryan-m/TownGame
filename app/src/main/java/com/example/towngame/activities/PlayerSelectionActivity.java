package com.example.towngame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.towngame.playerSelection.Player;
import com.example.towngame.playerSelection.PlayerAdapter;
import com.example.towngame.R;
import com.example.towngame.playerSelection.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectionActivity extends AppCompatActivity {

    private List<Player> players = new ArrayList<>();
    private PlayerAdapter adapter;
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
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        adapter = new PlayerAdapter(this, players);

        RecyclerView playerRecyclerView = findViewById(R.id.playerRecyclerView);
        playerRecyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // 3 колонки
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing); // Define the spacing value in your resources
        playerRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        playerRecyclerView.setAdapter(adapter);
        // Addplayers button

        Button addPlayerButton = (Button) findViewById(R.id.addPlayerButton);
        addPlayerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addPlayer();
            }
        });
    }
    public void addPlayer() {
        Log.d("YourActivity", "Кнопка нажата");
        // Создайте нового игрока (можно изменить имя или добавлять через ввод)
        Player newPlayer = new Player("Игрок " + (players.size() + 1)); // Уникальное имя для каждого игрока
        players.add(newPlayer); // Добавление игрока в список
        adapter.notifyDataSetChanged(); // Уведомление адаптера о том, что данные изменились
    }
}