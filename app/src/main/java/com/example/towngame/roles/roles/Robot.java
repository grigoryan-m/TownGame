package com.example.towngame.roles.roles;

import com.example.towngame.GameManager;
import com.example.towngame.R;
import com.example.towngame.roles.ActiveRole;
import com.example.towngame.roles.GoodRole;
import com.example.towngame.roles.Role;

public class Robot extends Role implements GoodRole, ActiveRole {
    public Robot(){
        super();
        name = "Робот";
        firstNightDescription = "Вы — робот. Вы умеете модифицировать башни, чтобы сделать их прочнее!";
        outOfTownDescription = "Пусть у Вас и нет чувств, Вы запрограммированы защищать город. Вперед! Сделайте все, что в Ваших силах!";
        inTownDesciption = "Расчехляйте свои инструменты — выберите какую башню укрепить.";

        iconResId = R.drawable.robot;
    }

    @Override
    public void towerActivity(int index){
        if(GameManager.towers[index] == 1) GameManager.towers[index] = 2;
    }
}
