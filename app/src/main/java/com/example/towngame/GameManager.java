package com.example.towngame;

import android.util.Log;

import com.example.towngame.playerSelection.Player;
import com.example.towngame.roles.Role;
import com.example.towngame.roles.RoleManager;

import java.util.Collections;
import java.util.List;

public class GameManager {
    public static List<Player> players;
    private List<Role> roles; // All the roles that are in the game at the moment

    public static int currentPlayerID;

    static{
        currentPlayerID = -1;
    }

    public GameManager(List<Player> _players){
        players = _players;
        roles = RoleManager.allowRoles(players.size());
        distributeRoles();
        Log.d("INF", players.toString());
    }

    private void distributeRoles(){
        Collections.shuffle(roles);
        for(int i = 0; i < players.size(); i++){
            players.get(i).role = roles.get(i);
        }
    }
}
