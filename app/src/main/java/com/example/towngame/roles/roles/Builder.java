package com.example.towngame.roles.roles;

import com.example.towngame.GameManager;
import com.example.towngame.R;
import com.example.towngame.roles.ActiveRole;
import com.example.towngame.roles.GoodRole;
import com.example.towngame.roles.Role;

public class Builder extends Role implements ActiveRole, GoodRole {
    public Builder(){
        super();
        name = "Строитель";
        firstNightDescription = "Вы — примерный работяга! Поборитесь же за свой город!";
        outOfTownDescription = "Эх... Вот бы они пустили Вас в город. Вы бы там все залатали.";
        inTownDesciption = "Чего же Вы ждете?! У вас всего ночь, чтобы починить башню!";
        iconResId = R.drawable.builder;
    }

    @Override
    public void towerActivity(int index){
        if(GameManager.towers[index].hp < 1) GameManager.towers[index].hp = 1;
    }
}
