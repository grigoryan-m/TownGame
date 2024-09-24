package com.example.towngame;

import android.content.Context;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.towngame.playerSelection.Player;
import com.example.towngame.roles.Role;
import com.example.towngame.roles.RoleManager;

import java.util.Collections;
import java.util.List;

public class GameManager {
    public static List<Player> players;
    private List<Role> roles; // All the roles that are in the game at the moment
    public Context context;
    public LinearLayout container;

    private static GameManager instance;

    public static final int TOWER_NUMBER = 3;

    public static int[] towers = new int[TOWER_NUMBER];

    public static int currentPlayerID, nightNumber;
    static{
        currentPlayerID = -1;
        nightNumber = 1;
        towers[0] = 1;
        towers[1] = 1;
        towers[2] = 1;
    }

    public GameManager(List<Player> _players){
        players = _players;
        for(int i = 0; i < players.size(); i++){
            players.get(i).setId(i);
        }
        roles = RoleManager.allowRoles(players.size());
        Log.d("ROLES", players.toString());
        distributeRoles();
    }

    private GameManager(){

    }

    public GameManager(Context context, GridLayout container){
        for(Player player : players){
            player.role.context = context;
            player.role.container = container;
        }
    }


    //Singleton
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    public void distributeRoles(){
        Collections.shuffle(roles);
        for(int i = 0; i < players.size(); i++){
            players.get(i).role = roles.get(i);
        }
        for(int i = 0; i < roles.size(); i++){
            roles.get(i).playerID = i;
        }
        for(Player player : players){
            player.role.afterDistribution();
        }
    }

    public void updateTowers(){
        for(int tower : towers){
            if(tower <= 0){

            }
        }
    }
}
