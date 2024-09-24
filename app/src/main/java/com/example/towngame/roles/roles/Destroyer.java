package com.example.towngame.roles.roles;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.towngame.GameManager;
import com.example.towngame.R;
import com.example.towngame.playerSelection.Player;
import com.example.towngame.roles.ActiveRole;
import com.example.towngame.roles.BadRole;
import com.example.towngame.roles.Role;

public class Destroyer extends Role implements BadRole, ActiveRole {
    public Destroyer(){
        super();
        name = "Разрушитель";
        desciption = "Эти болваны пустили Вас в город! Выберите какую башню разрушить!";
        firstNightDescription = "Вы — разрушитель. Ваша цель — сломать все башни города. Но для этого сначала надо как-то попасть внутрь... Ниже Вы видите список своих напарников: ";

        iconResId = R.drawable.destroyer;
    }

    public String findTeam(){

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < GameManager.players.size(); i++){
            if(GameManager.players.get(i).role instanceof BadRole && GameManager.players.get(i).role != this){
                result.append(GameManager.players.get(i).getName()).append("\n");
            }
        }
        Log.d("ROLE", result.toString());
        return result.toString();
    }

    @Override
    public void doOnMyTurn() {
        super.doOnMyTurn();
        if (GameManager.nightNumber == 1) {
            displayPlayers(BadRole.class, this);
        }
    }


}
