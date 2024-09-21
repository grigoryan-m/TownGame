package com.example.towngame.roles;

import com.example.towngame.R;

public class Villager extends Role implements GoodRole, PassiveRole{
    public Villager(){
        name = "Житель";
        desciption = "В городе мирному жителю делать нечего. Выберите в какой башне будете спать!";
        firstNightDescription = "Вы — порядочный житель! У вас нет активных умений. Все, что Вы умеете — спать. Вы играете за команду жителей.";
        iconResId = R.drawable.villager;
    }
}
