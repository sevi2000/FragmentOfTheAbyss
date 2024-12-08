package com.formiko.fragmentsoftheabyss.model;
public abstract class Entity {

    protected int id;
    protected static int nextId = 1;
    protected int health;
    protected float x,y;
    protected int maxHealth;
    protected int speed;

    protected void moveUp() {
        y += speed;
    }

    protected void moveDown() {
        y -= speed;
    }

    protected void moveLeft() {
        x -= speed;
    }

    protected void moveRight() {
        x += speed;
    }
}