package com.example.towngame.roles;

import com.example.towngame.R;

public class Villager extends Role implements GoodRole, PassiveRole{
    public Villager(){
        name = "Житель";
        desciption = "Мирному жителю делать нечего. Ложитесь спать!";
        iconResId = R.drawable.villager;
    }
}
