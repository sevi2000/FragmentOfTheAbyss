package com.formiko.fragmentsoftheabyss.model.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Box.class, name = "BOX"),
    @JsonSubTypes.Type(value = Player.class, name = "PLAYER")
})
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
        System.out.println("x: " + this.x + " y: " + this.y);
        this.x += deltaX * speed;
        this.y += deltaY * speed;
        System.out.println("x1: " + this.x + " y1: " + this.y);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean collidesWith(Entity entity) {
        return this.getBounds().overlaps(entity.getBounds());
    }
}