package com.formiko.fragmentsoftheabyss.model.entity;


import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;

public class Item extends Entity {

    public Item(EntityType id, int health, float x, float y, int maxHealth, int speed) {
        super(id, health, x, y, maxHealth, speed);
    }

    enum Type {
        WEAPON,
        ARMOR,
        POTION,
        KEY
    }
    
    private Type type;
    
}