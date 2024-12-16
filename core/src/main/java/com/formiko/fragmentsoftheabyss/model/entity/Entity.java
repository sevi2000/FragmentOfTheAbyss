package com.formiko.fragmentsoftheabyss.model.entity;

import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.formiko.fragmentsoftheabyss.controller.GameController;
import com.formiko.fragmentsoftheabyss.model.Coordinates;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.utils.AStar;
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
    @JsonSubTypes.Type(value = Monster.class, name = "MONSTER"),
    @JsonSubTypes.Type(value = Door.class, name = "DOOR")
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
    protected List<Coordinates> path;
    protected float seeRadius;

    protected Entity(EntityType id, int health, float x, float y, int maxHealth, int speed, float width, float height) {
        this.id = id;
        this.health = health;
        this.x = x;
        this.y = y;
        this.maxHealth = maxHealth;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.path = List.of();
        seeRadius = 400;
    }

    public void move(float deltaX, float deltaY) {
        // Do not move more on higher FPS
        float delta = Gdx.graphics.getDeltaTime();
        deltaX *= delta*60;
        deltaY *= delta*60;

        this.x += deltaX * speed;
        this.y += deltaY * speed;

        moveInsideScreenIfNeeded();
        // Gdx.app.log("Entity", "Move of " + deltaX + " " + deltaY + " " + speed + " in "+ Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
    }

    private long lastTimePathCalculate;
    private long eachMsTimeToRecalculate = 300;
    public void moveToTarget(Entity target) {
        if (path.isEmpty()) {
            path = AStar.findPath(this, target);
            lastTimePathCalculate = System.currentTimeMillis();
        }
        if (!path.isEmpty()) {
            Coordinates next = path.get(0);
            moveTo(next);
            //  every second clear the path to recalculate it
            if(System.currentTimeMillis() - lastTimePathCalculate > eachMsTimeToRecalculate) {
                path = List.of();
            }
        }
    }

    public void moveTo(Coordinates next) {
        setCenterX(next.x());
        setCenterY(next.y());
        path.remove(0);
        moveInsideScreenIfNeeded();
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
        if (this.x > GameController.FIELD_SIZE - this.width) {
            this.x = GameController.FIELD_SIZE - this.width;
        }
        if (this.y < 0) {
            this.y = 0;
        }
        if (this.y > GameController.FIELD_SIZE - this.height) {
            this.y = GameController.FIELD_SIZE - this.height;
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

    public float getCenterX() {
        return x + width / 2;
    }
    public float getCenterY() {
        return y + height / 2;
    }
    public void setCenterX(float centerX) {
        setX(centerX - width / 2);
    }
    public void setCenterY(float centerY) {
        setY(centerY - height / 2);
    }
    public boolean canSee(Entity target) {
        return Math.sqrt(Math.pow(target.getCenterX() - getCenterX(), 2) + Math.pow(target.getCenterY() - getCenterY(), 2)) < seeRadius;
    }
}