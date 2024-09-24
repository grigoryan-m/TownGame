package com.example.towngame.roles.roles;

import com.example.towngame.R;
import com.example.towngame.roles.GoodRole;
import com.example.towngame.roles.PassiveRole;
import com.example.towngame.roles.Role;

public class Villager extends Role implements GoodRole, PassiveRole {
    public Villager(){
        super();
        name = "Житель";
        inTownDesciption = "В городе мирному жителю делать нечего. Выберите в какой башне будете спать!";
        outOfTownDescription = "Сегодня прекрасная ночь! Выберите какой башней будете любоваться перед сном!";
        firstNightDescription = "Вы — порядочный житель! У вас нет активных умений. Все, что Вы умеете — спать. Вы играете за команду жителей.";
        iconResId = R.drawable.villager;
    }
}
