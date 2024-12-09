package com.formiko.fragmentsoftheabyss.model.entity;

import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Entity {

    protected EntityType id;
    protected int health;
    protected float x,y;
    protected int maxHealth;
    protected int speed;

    public void move(float deltaX, float deltaY) {
        this.x += deltaX * speed;
        this.y += deltaY * speed;
    }
}