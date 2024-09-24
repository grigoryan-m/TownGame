package com.example.towngame.roles;

import android.util.Log;

import com.example.towngame.roles.roles.Builder;
import com.example.towngame.roles.roles.Destroyer;
import com.example.towngame.roles.roles.Robot;
import com.example.towngame.roles.roles.Villager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoleManager {
    // There will be a UI for this later.
    // For now it's just a formula for counting roles based on number of players
    static List<Role> roles;
    public static List<Role> allowRoles(int numberOfPlayers){
        roles = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++){
            if((i + 1) <= numberOfPlayers / 2){
                roles.add(new Destroyer());
                continue;
            }
            if((numberOfPlayers >= 5 && numberOfPlayers < 8 && i == 4) || (numberOfPlayers >= 8 && i >= 6)) {
                Random random = new Random();
                double randomChance = random.nextDouble() * 100;
                if(randomChance >= 50) {
                    roles.add(new Builder());
                }else{
                    roles.add(new Robot());
                }
            } else {
                roles.add(new Villager());
            }
        }
        Log.d("ROLESDIS", roles.toString());
        return roles;
    }
    private static void addGoodRole(int numberOfPlayers){
        int goodLimit = numberOfPlayers - (numberOfPlayers / 2);
        Random random = new Random();
        double builderChance = random.nextDouble() * 100;
        if(countRole(Villager.class) >= goodLimit / 2){
            if(builderChance >= 50) {
                roles.add(new Builder());
                return;
            }
            roles.add(new Villager());
        } else {
            roles.add(new Villager());
        }
    }

    private static int countRole(Class<?> roleSearch){
        int number = 0;
        for (Role role : roles){
            if(roleSearch.isAssignableFrom(role.getClass())){
                number++;
            }
        }
        return number;
    }
}
