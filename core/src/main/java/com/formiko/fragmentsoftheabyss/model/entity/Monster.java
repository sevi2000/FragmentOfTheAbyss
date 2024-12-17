package com.formiko.fragmentsoftheabyss.model.entity;

import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Monster extends Entity {

    private int attack;
    private int hitRadius;
    
    @Builder
    public Monster(int health, float x, float y, int maxHealth, int speed, int attack, int hitRadius) {
        super(EntityType.MONSTER, health, x, y, maxHealth, speed, 150, 200);
        this.attack = attack;
        this.hitRadius = hitRadius;
    }
    
    public Monster() {
        super(EntityType.MONSTER, 0, 0, 0, 0, 0, 0, 0);
    }

    public void damage(int attack2) {
            if (this.health > attack2) {
                this.health -= attack2;
            } else {
                this.health = 0;
            }
    }
}
