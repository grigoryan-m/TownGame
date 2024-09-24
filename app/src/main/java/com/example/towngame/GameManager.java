package com.example.towngame;

import android.content.Context;
import android.util.Log;
import android.widget.GridLayout;

import com.example.towngame.playerSelection.Player;
import com.example.towngame.roles.Role;
import com.example.towngame.roles.RoleManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GameManager {
    public static List<Player> players;
    private List<Role> roles; // All the roles that are in the game at the moment
    public Context context;

    public static int villagerScore;
    private static int VILLAGER_GOAL;

    public static final int TOWER_NUMBER = 3;

    public static Tower[] towers = new Tower[TOWER_NUMBER];

    public static int currentPlayerID, nightNumber;
    static{
        currentPlayerID = -1;
        nightNumber = 1;
        towers[0] = new Tower();
        towers[1] = new Tower();
        towers[2] = new Tower();
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
        for(Tower tower : towers){
            if(tower.hp > 0){
                return false;
            }
        }
        return true;
    }

    public static boolean checkForVillagerWin(){
        return villagerScore >= VILLAGER_GOAL;
    }

    public static class Tower {
        public int hp;
        public int iconResId;
        private static final HashMap<Integer, Integer> icons = new HashMap<>(); // hp => icon

        static{
            icons.put(0, R.drawable.destruction);
            icons.put(1, R.drawable.tower);
            icons.put(2, R.drawable.skyscraper);
        }

        public Tower(){
            hp = 1;
            iconResId = R.drawable.tower;
        }

        public void robotUpgrade(){
            if(hp == 1) hp = 2;
            updateIcon();
        }

        public void damage(){
            hp--;
            updateIcon();
        }
        public void damage(int damage){
            hp -= damage;
            updateIcon();
        }

        public void updateIcon(){
            if(icons.containsKey(hp)) iconResId = icons.get(hp);
            else iconResId = R.drawable.tower;
        }
    }
}
