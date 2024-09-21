package com.example.towngame.roles;

public abstract class Role {
    protected String name;
    protected String desciption;
    protected int iconResId;

    protected String firstNightDescription;

    public String getName() {
        return name;
    }

    public String getDesciption(){
        return desciption;
    }

    public String getFirstNightDescription() {
        return firstNightDescription;
    }

    public int getIconResId(){ return iconResId; }
    @Override
    public String toString(){
        return name;
    }
}
