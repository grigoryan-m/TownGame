package com.example.towngame.playerSelection;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

import com.example.towngame.GameManager;
import com.example.towngame.R;
import com.example.towngame.roles.Role;
import com.example.towngame.roles.roles.Villager;

public class Player implements Serializable {
    private String name;
    public transient Role role;



    public Player(String name){
        this.name = name;
        this.role = new Villager();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name + " : " + role;
    }

    protected boolean isMyTurn(){
        return GameManager.players.get(GameManager.currentPlayerID).equals(this);
    }

}
