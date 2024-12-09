package com.formiko.fragmentsoftheabyss.model.entity;

import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Player extends Entity{

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

    @Builder
    public Player(EntityType id, int health, float x, float y, int maxHealth, int speed, String name, int experience, int gold, int attack, int defense) {
        super(id, health, x, y, maxHealth, speed);
        this.experience = experience;
        this.gold = gold;
        this.attack = attack;
        this.defense = defense;
        this.name = name;
    }
}