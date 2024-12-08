package com.formiko.fragmentsoftheabyss.model;

public class Item extends Entity {
    enum Type {
        WEAPON,
        ARMOR,
        POTION,
        KEY
    }
    
    private Type type;
    
}