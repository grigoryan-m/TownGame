package com.example.towngame.roles;

import com.example.towngame.R;

public class Destroyer extends Role implements BadRole, ActiveRole{
    public Destroyer(){
        name = "Разрушитель";
        desciption = "Эти болваны пустили Вас в город! Выберите какую башню разбить!";
        firstNightDescription = "Вы — разрушитель. Ваша цель — сломать все башни города. Но для этого сначала надо как-то попасть внутрь... Ниже Вы видите своих напарников: ";
        iconResId = R.drawable.destroyer;
    }
}
