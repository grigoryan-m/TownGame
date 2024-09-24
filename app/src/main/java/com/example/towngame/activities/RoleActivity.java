package com.example.towngame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
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

    private ScrollView scrollView2;
//    private ImageView tower1, tower2;
    private ImageView[] towers = new ImageView[GameManager.TOWER_NUMBER];
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
        Log.d("TOWERSNUM", "-1");
        continueButton = findViewById(R.id.continueButton);
        TextView roleName = findViewById(R.id.roleName);
        TextView roleDescription = findViewById(R.id.roleDescription);
        ImageView roleIcon = findViewById(R.id.roleIcon);
        GridLayout playersContainer = findViewById(R.id.playersContainer);
        ScrollView scrollView = findViewById(R.id.scrollView);
        Player currentPlayer = GameManager.players.get(GameManager.currentPlayerID);
        roleIcon.setImageResource(currentPlayer.role.getIconResId());
        scrollView2 = findViewById(R.id.scrollView2);
        Log.d("TOWERSNUM", "0");
        roleName.setText(GameManager.players.get(GameManager.currentPlayerID).role.getName());


        // Highly EXPEREMENTAL

        for(Player player : GameManager.players){
            player.role.container = playersContainer;
            player.role.context = this;
        }
        Log.d("TOWERSNUM", "00");
        GameManager.players.get(GameManager.currentPlayerID).role.doOnMyTurn();

        //
        towers[0] = findViewById(R.id.tower1); // Initialize tower1
        towers[1] = findViewById(R.id.tower2); // Initialize tower2
        towers[2] = findViewById(R.id.tower3);
        initiateTowers(GameManager.players.get(GameManager.currentPlayerID).role.isCurrentlyInTown);
        Log.d("TOWERSNUM", "01");

        if(GameManager.nightNumber > 1) {
            Log.d("TOWERSNUM", "1");
            scrollView2.setVisibility(View.VISIBLE);
            playersContainer.setVisibility(View.GONE);
            scrollView.setVisibility(View.GONE);
            if (continueButton != null) {
                continueButton.setEnabled(false); // Disable initially
            }
            Log.d("TOWERSNUM", "2");
             // Intitialize tower3

            towers[0].setBackgroundResource(R.drawable.radio_bg);
            towers[1].setBackgroundResource(R.drawable.radio_bg);
            towers[2].setBackgroundResource(R.drawable.radio_bg);
            roleDescription.setText(GameManager.players.get(GameManager.currentPlayerID).role.getDescription());

            towers[0].setOnClickListener(v -> selectImage(0));
            towers[1].setOnClickListener(v -> selectImage(1));
            towers[2].setOnClickListener(v -> selectImage(2));
        }
        else{
            Log.d("TOWERSNUM", "3");
            continueButton.setEnabled(true);
            playersContainer.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.VISIBLE);
            scrollView2.setVisibility(View.GONE);
            roleDescription.setText(GameManager.players.get(GameManager.currentPlayerID).role.getFirstNightDescription());
        }
    }

    public void nextPlayer(View view){
        GameManager.players.get(GameManager.currentPlayerID).role.towerActivity(selectedTowerIndex);

        if(GameManager.currentPlayerID + 1 < GameManager.players.size()){
            GameManager.currentPlayerID++;
            Intent intent = new Intent(RoleActivity.this, NightProfile.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(RoleActivity.this, WelcomeActivity.class);
            intent.putExtra("WELCOME_MESSAGE", "Наступает день. Положите устройство на стол и начинайте обсуждение");
            intent.putExtra("TITLE_MESSAGE", "День " + GameManager.nightNumber);
            intent.putExtra("NEXT_ACTIVITY", "DISCUSSION_ACTIVITY");
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

        for(int i = 0; i < towers.length; i++){
            if(i == index){
                towers[i].setBackgroundResource(R.drawable.radio_bg_selected);
                continue;
            }
            towers[i].setBackgroundResource(R.drawable.radio_bg);
        }


        // Change background resource
//        if (index == 0) {
//            tower1.setBackgroundResource(R.drawable.radio_bg_selected);
//            tower2.setBackgroundResource(R.drawable.radio_bg);
//        } else {
//            tower1.setBackgroundResource(R.drawable.radio_bg);
//            tower2.setBackgroundResource(R.drawable.radio_bg_selected);
//        }
    }


    public void initiateTowers(boolean isInTown){
        if(isInTown){
            for(int i = 0; i < GameManager.towers.length; i++){
                if(GameManager.towers[i] > 0){
                    towers[i].setImageResource(R.drawable.tower);
                } else {
                    towers[i].setImageResource(R.drawable.destruction);
                }
            }
        } else {
            for(int i = 0; i < GameManager.towers.length; i++){
                towers[i].setImageResource(R.drawable.tower);
            }
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RoleActivity.this, MainActivity.class);
        startActivity(intent);
    }
}