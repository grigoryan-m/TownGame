package com.example.towngame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.towngame.GameManager;
import com.example.towngame.R;

import com.example.towngame.VotingSystem;
import com.example.towngame.playerSelection.Player;
import com.example.towngame.roles.Role;

public class VoteActivity extends AppCompatActivity {


    private static boolean isFirstLaunch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vote);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainContainerVote), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if(isFirstLaunch) {
            GameManager.currentPlayerID = 0;
            isFirstLaunch = false;
            VotingSystem.initialize();
        }
        TextView voteText = findViewById(R.id.voteText);
        voteText.setText(getIntent().getStringExtra("VOTE_TEXT"));
        displaySettings();

    }

    @Override
    protected void onResume(){
        super.onResume();
        displaySettings();
    }

    public void displaySettings() {
        Role role = GameManager.players.get(GameManager.currentPlayerID).role;
        RadioGroup container = findViewById(R.id.containerPlayers);
        container.removeAllViews(); // Очищаем предыдущие элементы

        TextView playerName = findViewById(R.id.votingPlayerName);
        playerName.setText(GameManager.players.get(GameManager.currentPlayerID).getName());

        Button voteButton = findViewById(R.id.voteButton);
        voteButton.setEnabled(false);
        voteButton.setAlpha(0.5f);

        // Перебираем всех игроков, кроме текущего
        for (Player player : GameManager.players) {
            if (player.role != role) {
                // Создаем layout для игрока
                View playerView = LayoutInflater.from(this).inflate(R.layout.player_item, container, false);
//                playerView.setLayoutParams(new RadioGroup.LayoutParams(
//                        RadioGroup.LayoutParams.MATCH_PARENT, // Ширина
//                        (int) getResources().getDimension(R.dimen.player_item_height) // Высота, определенная в dimens.xml
//                ));
                playerView.setTag(player);
                TextView playerNameView = playerView.findViewById(R.id.playerName);
                playerNameView.setText(player.getName());

                // Добавляем обводку и выбор игрока
                playerView.setOnClickListener(v -> {
                    // Снимаем обводку со всех остальных игроков
                    for (int i = 0; i < container.getChildCount(); i++) {
                        View child = container.getChildAt(i);
                        child.findViewById(R.id.playerCircle).setBackgroundResource(R.drawable.radio_bg); // Сбрасываем обводку
                    }

                    // Подсчет голосов
                    VotingSystem.vote((Player) playerView.getTag());

                    // Устанавливаем обводку для выбранного игрока
                    playerView.findViewById(R.id.playerCircle).setBackgroundResource(R.drawable.radio_bg_selected); // Ваш стиль обводки
                    voteButton.setEnabled(true);
                    voteButton.setAlpha(1.0f);
                });

                // Добавляем игрока в RadioGroup
                container.addView(playerView);
            }
        }
    }



    public void nextPlayerVote(View view){
        if(GameManager.currentPlayerID + 1 != GameManager.players.size()) {
            GameManager.currentPlayerID++;
            String voteText = getIntent().getStringExtra("VOTE_TEXT");
            Intent intent = new Intent(VoteActivity.this, VoteActivity.class);
            intent.putExtra("VOTE_TEXT", voteText);
            startActivity(intent);
        }else{
            Log.d("WINNER", "Else");
            isFirstLaunch = true;
            Intent intent = new Intent(VoteActivity.this, WelcomeActivity.class);
            Player winner = VotingSystem.getTheWinner();
            String title, description;
            if(winner == null) {
                title = "Никто не отправился в город!";
                description = "Жители не смогли определиться. Сегодня в город никто не отправляется!";
            }else{
                title = winner.getName() + "!";
                description = "В город решили отправить игрока " + winner.getName() + "! " +
                        "\nНачинается дневная активность. Передайте устройство игроку " + GameManager.players.get(0).getName() + ", а затем каждому игроку по часовой стрелке.";
            }
            intent.putExtra("TITLE_MESSAGE", title);
            intent.putExtra("WELCOME_MESSAGE", description);
            intent.putExtra("NEXT_ACTIVITY", "DISCUSSION_ACTIVITY");
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(VoteActivity.this, MainActivity.class);
        startActivity(intent);
    }
}