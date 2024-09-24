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
    private Player votedPlayer;
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

            for(Player player : GameManager.players){
                player.role.isCurrentlyInTown = false;
            }
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
        GridLayout container = findViewById(R.id.containerPlayers);
        container.removeAllViews(); // Clear previous elements

        TextView playerName = findViewById(R.id.votingPlayerName);
        playerName.setText(GameManager.players.get(GameManager.currentPlayerID).getName());

        Button voteButton = findViewById(R.id.voteButton);
        voteButton.setEnabled(false);
        voteButton.setAlpha(0.5f);

        // Iterate through all players except the current one
        for (Player player : GameManager.players) {
            if (player.role != role) {
                // Inflate the layout for the player
                View playerView = LayoutInflater.from(this).inflate(R.layout.player_item, container, false);
                playerView.setTag(player);
                TextView playerNameView = playerView.findViewById(R.id.playerName);
                playerNameView.setText(player.getName());

                // Set click listener for player selection
                playerView.setOnClickListener(v -> {
                    // Remove highlight from other players
                    for (int i = 0; i < container.getChildCount(); i++) {
                        View child = container.getChildAt(i);
                        child.findViewById(R.id.playerCircle).setBackgroundResource(R.drawable.radio_bg); // Reset highlight
                    }

                    // Count the vote
                    votedPlayer = (Player) playerView.getTag();

                    // Highlight the selected player
                    playerView.findViewById(R.id.playerCircle).setBackgroundResource(R.drawable.radio_bg_selected);
                    voteButton.setEnabled(true);
                    voteButton.setAlpha(1.0f);
                });

                // Add player view to the GridLayout
                container.addView(playerView);
            }
        }
    }


    public void nextPlayerVote(View view){
        VotingSystem.vote(votedPlayer);
        if(GameManager.currentPlayerID + 1 != GameManager.players.size()) {
            GameManager.currentPlayerID++;
            String voteText = getIntent().getStringExtra("VOTE_TEXT");
            Intent intent = new Intent(VoteActivity.this, VoteActivity.class);
            intent.putExtra("VOTE_TEXT", voteText);
            startActivity(intent);
        }else{
            isFirstLaunch = true;
            Intent intent = new Intent(VoteActivity.this, WelcomeActivity.class);
            Player winner = VotingSystem.getTheWinner();
            String title, description;
            GameManager.nightNumber++;
            GameManager.currentPlayerID = 0;
            title = "Ночь " + GameManager.nightNumber;
            if(winner == null) {
                description = "Жители не смогли определиться. Сегодня в город никто не отправляется!";
            }else{
                GameManager.players.get(winner.getId()).role.isCurrentlyInTown = true;
                description = "В город решили отправить игрока " + winner.getName() + "! " +
                        "\nНачинается ночь. Передайте устройство игроку " + GameManager.players.get(0).getName() + ", а затем каждому игроку по часовой стрелке.";
            }
            intent.putExtra("TITLE_MESSAGE", title);
            intent.putExtra("WELCOME_MESSAGE", description);
            intent.putExtra("NEXT_ACTIVITY", "NIGHT_ACTIVITY");
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