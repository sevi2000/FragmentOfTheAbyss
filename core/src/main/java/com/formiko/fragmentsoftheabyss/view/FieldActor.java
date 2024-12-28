package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import com.formiko.fragmentsoftheabyss.Main;
import com.formiko.fragmentsoftheabyss.model.entity.Door;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.model.Field;
import com.formiko.fragmentsoftheabyss.view.actors.*;
import com.formiko.fragmentsoftheabyss.view.interfaceModel.ActorsEntity;
import lombok.Getter;

@Getter
public class FieldActor extends Group {

    private final Texture mapTexture;
    private final Field field;

    public FieldActor(Field field) {
        this.mapTexture = new Texture("textures/level" +Main.level+"_texture.png");//TextureGenerator.getTexture(TextureGenerator.TextureType.MAP);
        this.field = field;
        addActors();
    }
    /**
     * {@summary Draw the background.}
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(mapTexture, getX(), getY(), getWidth(), getHeight());
        for (int i = 0; i < getChildren().size; i++) {
            getChildren().get(i).draw(batch, parentAlpha);
        }
    }

    public void addActors() {
        for (Entity entity : field.getListEntityOnField()) {
            if (entity instanceof Door door) {
                addDoor(door);                
            } else{
                addActor(entity.toActor());
            }
        }
    }

    public void addDoor(Door door) {
        if (!door.isVisible()) return;
        DoorActor doorActor = new DoorActor(door);
        doorActor.setSize(door.getWidth(), door.getHeight());
        addActorAt(0, doorActor);
    }

    public void addEntity(Entity entity) {
        field.getListEntityOnField().add(entity);
    }
    public void removeEntity(Entity entity){
        field.getListEntityOnField().remove(entity);
    }

    public void addActorAndEntity(Actor actor) {
        addActor(actor);
        if (actor instanceof ActorsEntity actorsEntity)
            addEntity(actorsEntity.getEntity());
    }

    public void removeActorAndEntity(Actor actor) {
        removeActor(actor);
        if (actor instanceof ActorsEntity actorsEntity)
            removeEntity(actorsEntity.getEntity());
    }
}
