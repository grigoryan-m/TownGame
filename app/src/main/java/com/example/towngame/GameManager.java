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

    public static int villagerScore;
    private static int VILLAGER_GOAL;

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
        VILLAGER_GOAL = (players.size() / 2) + 2;
    }

    private GameManager(){

    }

    public GameManager(Context context, GridLayout container){
        for(Player player : players){
            player.role.context = context;
            player.role.container = container;
        }
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

    public static boolean checkForDestroyerWin(){
        for(int tower : towers){
            if(tower > 0){
                return false;
            }
        }
        return true;
    }

    public static boolean checkForVillagerWin(){
        return villagerScore >= VILLAGER_GOAL;
    }
}
