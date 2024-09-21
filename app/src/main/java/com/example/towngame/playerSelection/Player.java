package com.example.towngame.playerSelection;

import java.io.Serializable;
import com.example.towngame.roles.Role;
import com.example.towngame.roles.Villager;

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
}
