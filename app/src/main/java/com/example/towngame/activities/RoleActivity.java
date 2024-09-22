package com.example.towngame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.towngame.GameManager;
import com.example.towngame.R;
import com.example.towngame.playerSelection.Player;


public class RoleActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private ImageView tower1, tower2;
    private Button continueButton;
    private int selectedTowerIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_role);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainContainer), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        radioGroup = findViewById(R.id.radioGroup);
        continueButton = findViewById(R.id.continueButton);
        TextView roleName = findViewById(R.id.roleName);
        TextView roleDescription = findViewById(R.id.roleDescription);
        ImageView roleIcon = findViewById(R.id.roleIcon);
        Player currentPlayer = GameManager.players.get(GameManager.currentPlayerID);
        roleIcon.setImageResource(currentPlayer.role.getIconResId());

        roleName.setText(GameManager.players.get(GameManager.currentPlayerID).role.getName());


        // Highly EXPEREMENTAL

        for(Player player : GameManager.players){
            player.role.container = findViewById(R.id.playersContainer);
            player.role.context = this;
        }

        GameManager.players.get(GameManager.currentPlayerID).role.doOnMyTurn();

        //



        if(GameManager.nightNumber > 1) {
            radioGroup.setVisibility(View.VISIBLE);
            if (continueButton != null) {
                continueButton.setEnabled(false); // Disable initially
            }

            tower1 = findViewById(R.id.tower1); // Initialize tower1
            tower2 = findViewById(R.id.tower2); // Initialize tower2

            tower1.setBackgroundResource(R.drawable.radio_bg);
            tower2.setBackgroundResource(R.drawable.radio_bg);

            roleDescription.setText(GameManager.players.get(GameManager.currentPlayerID).role.getDesciption());

            tower1.setOnClickListener(v -> selectImage(0));
            tower2.setOnClickListener(v -> selectImage(1));
        }
        else{
            continueButton.setEnabled(true);
            radioGroup.setVisibility(View.GONE);
            roleDescription.setText(GameManager.players.get(GameManager.currentPlayerID).role.getFirstNightDescription());
        }
    }

    public void nextPlayer(View view){
        if(GameManager.currentPlayerID + 1 < GameManager.players.size()){
            GameManager.currentPlayerID++;
            Intent intent = new Intent(RoleActivity.this, NightProfile.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(RoleActivity.this, WelcomeActivity.class);
            intent.putExtra("WELCOME_MESSAGE", "Наступает день. Положите телефон на стол и начинайте обсуждение");
            intent.putExtra("TITLE_MESSAGE", "День " + GameManager.nightNumber);
            intent.putExtra("NEXT_ACTIVITY", "DAY_ACTIVITY");
            startActivity(intent);
        }
    }


    private void selectImage(int index) {
        // Logic for selecting image
        selectedTowerIndex = index;

        // Enable the continue button
        if (continueButton != null) {
            continueButton.setEnabled(true);
        }

        // Change background resource
        if (index == 0) {
            tower1.setBackgroundResource(R.drawable.radio_bg_selected);
            tower2.setBackgroundResource(R.drawable.radio_bg);
        } else {
            tower1.setBackgroundResource(R.drawable.radio_bg);
            tower2.setBackgroundResource(R.drawable.radio_bg_selected);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RoleActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void displayPlayers(){

    }

}