package com.formiko.fragmentsoftheabyss.model.entity;

import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Monster extends Entity {

    private int attack;
    
    @Builder
    public Monster(int health, float x, float y, int maxHealth, int speed, float width, float height, int attack) {
        super(EntityType.MONSTER, health, x, y, maxHealth, speed, width, height);
        this.attack = attack;
    }
    
    public Monster() {
        super(EntityType.MONSTER, 0, 0, 0, 0, 0, 0, 0);
    }
}
