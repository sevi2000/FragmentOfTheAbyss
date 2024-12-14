package com.formiko.fragmentsoftheabyss.model.entity;

import java.util.regex.Matcher;

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

    @Builder
    public Player(EntityType id, int health, float x, float y, int maxHealth, int speed, String name, int experience, int gold, int attack, int defense, float width, float height) {
        super(id, health, x, y, maxHealth, speed,width,height);
        this.experience = experience;
        this.gold = gold;
        this.attack = attack;
        this.defense = defense;
        this.name = name;
    }
    public Player(){
        super(EntityType.PLAYER,0,0,0,0,0,0,0);
        this.experience = 0;
        this.gold = 0;
        this.attack = 0;
        this.defense = 0;
        this.name = "";
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

    /*public static Player fromString(String line) {

        String regex = "(" + PLAYER + ") size=\\((\\d+),(\\d+)\\) position=\\((\\d+),(\\d+)\\)";

        // Compile the pattern Pattern pattern = Pattern.compile(regex);

        // Match the pattern in the input string
        Matcher matcher = pattern.matcher(strBox);

        // Check if a match is found
        if (matcher.find()) {
            // Print the matched pattern
            System.out.println("Match found: " + matcher.group());
            Player player = Player.builder()
                .x(Integer.parseInt(matcher.group(4)))
                .y(Integer.parseInt(matcher.group(5)))
                .maxHealth(100)
                .width(Integer.parseInt(matcher.group(2)))
                .height(Integer.parseInt(matcher.group(3)))
                .build();
            return player;
        } else {
            System.out.println("No match found.");
            return null;
        }

    }*/
}
