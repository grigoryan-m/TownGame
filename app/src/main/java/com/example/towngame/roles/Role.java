package com.example.towngame.roles;

public abstract class Role {
    protected String name;
    protected String desciption;
    protected int iconResId;

    public String getName() {
        return name;
    }

    public String getDesciption(){
        return desciption;
    }

    @Override
    public String toString(){
        return name;
    }
}
