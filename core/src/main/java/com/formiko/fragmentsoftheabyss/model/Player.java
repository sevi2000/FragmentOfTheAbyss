package com.formiko.fragmentsoftheabyss.model;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.Builder
public class Player extends Entity{

    /**
     * The name of the player
     */
    private String name;
    /**
     * It will be used to calculate the level of the player
     */
    private int experience;
    private int gold;
    private int attack;
    private int defense;
}