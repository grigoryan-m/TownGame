package com.example.towngame.roles;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.towngame.GameManager;
import com.example.towngame.playerSelection.Player;
import com.example.towngame.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.towngame.GameManager;
import com.example.towngame.R;
import com.example.towngame.playerSelection.Player;
import com.example.towngame.activities.RoleActivity;

public abstract class Role {
    public int playerID;

    protected String name;
    protected String desciption;
    protected int iconResId;

    public Context context;
    public GridLayout container;

    protected String firstNightDescription;

    public String getName() {
        return name;
    }

    public String getDesciption(){
        return desciption;
    }

    public String getFirstNightDescription() {
        return firstNightDescription;
    }

    public int getIconResId(){ return iconResId; }
    @Override
    public String toString(){
        return name;
    }

    public void afterDistribution(){

    }

    public static void displayPlayers(Context context, GridLayout container) {
        container.removeAllViews(); // Clear previous views

        for (Player player : GameManager.players) {
            View playerView = LayoutInflater.from(context).inflate(R.layout.player_item, container, false);

            TextView playerName = playerView.findViewById(R.id.playerName);

            playerName.setText(player.getName());

            container.addView(playerView);
        }
    }


    public void displayPlayers(Context context, GridLayout container, Role roleInstance) {
        container.removeAllViews(); // Clear previous views

        for (Player player : GameManager.players) {
            if (player.role.equals(roleInstance)) {
                continue;
            }
            View playerView = LayoutInflater.from(context).inflate(R.layout.player_item, container, false);

            TextView playerName = playerView.findViewById(R.id.playerName);

            playerName.setText(player.getName());

            container.addView(playerView);
        }
    }

    public void displayPlayers(Context context, RadioGroup container, Role roleInstance) {
        container.removeAllViews(); // Clear previous views

        for (Player player : GameManager.players) {
            if (player.role.equals(roleInstance)) {
                continue;
            }
            View playerView = LayoutInflater.from(context).inflate(R.layout.player_item, container, false);

            TextView playerName = playerView.findViewById(R.id.playerName);

            playerName.setText(player.getName());

            container.addView(playerView);
        }
    }
    public void displayPlayers() {
        Log.d("ROLES", "Started function");
        if (container != null && context != null) {
            container.removeAllViews(); // Очистка предыдущих views
            Log.d("ROLES", "In the if statement");
            for (Player player : GameManager.players) {
                try {
                    Log.d("ROLES", "In the for loop");
                    // Inflate the player item layout
                    View playerView = LayoutInflater.from(context).inflate(R.layout.player_item, container, false);

                    TextView playerName = playerView.findViewById(R.id.playerName);

                    if (playerName != null) {
                        playerName.setText(player.getName());
                    } else {
                        Log.e("ROLES", "playerName TextView is null");
                    }

                    container.addView(playerView);
                } catch (Exception e) {
                    Log.e("ROLES", "Error inflating player view: " + e.getMessage());
                }
            }
        } else {
            Log.e("ROLES", "Context or container is null");
        }
    }
    public void displayPlayers(Role roleInstance) {
        Log.d("ROLES", "Started function");
        if (container != null && context != null) {
            container.removeAllViews(); // Очистка предыдущих views
            Log.d("ROLES", "In the if statement");
            for (Player player : GameManager.players) {
                if(!player.role.equals(roleInstance)) {
                    try {
                        Log.d("ROLES", "In the for loop");
                        // Inflate the player item layout
                        View playerView = LayoutInflater.from(context).inflate(R.layout.player_item, container, false);

                        TextView playerName = playerView.findViewById(R.id.playerName);

                        if (playerName != null) {
                            playerName.setText(player.getName());
                        } else {
                            Log.e("ROLES", "playerName TextView is null");
                        }

                        container.addView(playerView);
                    } catch (Exception e) {
                        Log.e("ROLES", "Error inflating player view: " + e.getMessage());
                    }
                }
            }
        } else {
            Log.e("ROLES", "Context or container is null");
        }
    }
    public void displayPlayers(Class<?> role, Role roleInstance){
        Log.d("ROLES", "Started function");
        if (container != null && context != null) {
            container.removeAllViews(); // Очистка предыдущих views
            Log.d("ROLES", "In the if statement");
            for (Player player : GameManager.players) {
                if(role.isAssignableFrom(player.role.getClass()) && !player.role.equals(roleInstance)) {
                    try {
                        Log.d("ROLES", "In the for loop");
                        // Inflate the player item layout
                        View playerView = LayoutInflater.from(context).inflate(R.layout.player_item, container, false);

                        TextView playerName = playerView.findViewById(R.id.playerName);

                        if (playerName != null) {
                            playerName.setText(player.getName());
                        } else {
                            Log.e("ROLES", "playerName TextView is null");
                        }

                        container.addView(playerView);
                    } catch (Exception e) {
                        Log.e("ROLES", "Error inflating player view: " + e.getMessage());
                    }
                }
            }
        } else {
            Log.e("ROLES", "Context or container is null");
        }
    }

    public void doOnMyTurn(){

    }
}
