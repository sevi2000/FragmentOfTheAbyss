package com.formiko.fragmentsoftheabyss.model.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
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
    protected float width;
    protected float height;

    public void move(float deltaX, float deltaY) {
        if ((this.x + deltaX * speed > Gdx.graphics.getWidth() - 80 || this.x + deltaX * speed < 0)) {
            deltaX = 0;
        }
        if (this.y + deltaY * speed > Gdx.graphics.getHeight() - 80|| this.y + deltaY * speed < 0) {
            deltaY = 0;
            
        }
        this.x += deltaX * speed;
        this.y += deltaY * speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean collidesWith(Entity entity) {
        return this.getBounds().overlaps(entity.getBounds());
    }
}