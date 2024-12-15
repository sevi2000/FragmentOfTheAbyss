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
    @JsonSubTypes.Type(value = Player.class, name = "PLAYER"),
    @JsonSubTypes.Type(value = Monster.class, name = "MONSTER")
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
        // Do not move more on higher FPS
        float delta = Gdx.graphics.getDeltaTime();
        deltaX *= delta*60;
        deltaY *= delta*60;

        // // Do not move out of the screen
        // if ((this.x + deltaX * speed > Gdx.graphics.getWidth() - getWidth() || this.x + deltaX * speed < 0)) {
        //     deltaX = 0;
        // }
        // if (this.y + deltaY * speed > Gdx.graphics.getHeight() - getWidth()|| this.y + deltaY * speed < 0) {
        //     deltaY = 0;
        // }
        // System.out.println("x: " + this.x + " y: " + this.y);
        this.x += deltaX * speed;
        this.y += deltaY * speed;
        // System.out.println("x1: " + this.x + " y1: " + this.y);

        moveInsideScreenIfNeeded();
        // System.out.println("Move of " + deltaX + " " + deltaY + " " + speed + " in "+ Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
        Gdx.app.log("Entity", "Move of " + deltaX + " " + deltaY + " " + speed + " in "+ Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean collidesWith(Entity entity) {
        return this.getBounds().overlaps(entity.getBounds());
    }

    public void moveInsideScreenIfNeeded(){
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.x > Gdx.graphics.getWidth() - this.width) {
            this.x = Gdx.graphics.getWidth() - this.width;
        }
        if (this.y < 0) {
            this.y = 0;
        }
        if (this.y > Gdx.graphics.getHeight() - this.height) {
            this.y = Gdx.graphics.getHeight() - this.height;
        }
    }

    public void setX(float x) {
        this.x = x;
        moveInsideScreenIfNeeded();
    }
    public void setY(float y) {
        this.y = y;
        moveInsideScreenIfNeeded();
    }

}