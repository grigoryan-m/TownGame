package com.example.towngame.roles;

import com.example.towngame.roles.roles.Destroyer;
import com.example.towngame.roles.roles.Villager;

import java.util.ArrayList;
import java.util.List;

public class RoleManager {
    // There will be a UI for this later.
    // For now it's just a formula for counting roles based on number of players

    public static List<Role> allowRoles(int numberOfPlayers){
        List<Role> roles = new ArrayList<>();

        /*
            5
            0 - 4
            1 <=

         */
        for(int i = 0; i < numberOfPlayers; i++){
            if((i + 1) <= numberOfPlayers / 2){
                roles.add(new Destroyer());
                continue;
            }
            roles.add(new Villager());
        }
        return roles;
    }
}
