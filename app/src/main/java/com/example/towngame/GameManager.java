package com.example.towngame;

import android.util.Log;

import com.example.towngame.playerSelection.Player;
import com.example.towngame.roles.Role;
import com.example.towngame.roles.RoleManager;

import java.util.Collections;
import java.util.List;

public class GameManager {
    List<Player> players;
    List<Role> roles; // All the roles that are in the game at the moment

    public GameManager(List<Player> players){
        this.players = players;
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
