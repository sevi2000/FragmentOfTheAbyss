package com.formiko.fragmentsoftheabyss.model.entity;

import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Player extends Entity{

    private static final String PLAYER = "Player";
    /**
     * The name of the player
     */
    private final String name;
    /**
     * It will be used to calculate the level of the player
     */
    private final int experience;
    private final int gold;
    private final int attack;
    private final int defense;
    private final int hitRadius;

    @Builder
    public Player(int health, float x, float y, int maxHealth, int speed, String name, int experience, int gold, int attack, int defense, float width, float height, int hitRadius) {
        super(EntityType.PLAYER, health, x, y, maxHealth, speed,width,height);
        this.experience = experience;
        this.gold = gold;
        this.attack = attack;
        this.defense = defense;
        this.name = name;
        this.hitRadius = hitRadius;
    }
    public Player(){
        super(EntityType.PLAYER,0,0,0,0,0,0,0);
        this.experience = 0;
        this.gold = 0;
        this.attack = 0;
        this.defense = 0;
        this.name = "";
        this.hitRadius = 0;
    }

    public void damage(int damage){
        if (this.health > damage)
            this.health-=damage;
        else this.health = 0;
    }

    public void revive(int life){
        if (this.health < this.maxHealth + life)
            this.health+=life;
        else this.health = maxHealth;
    }

}
