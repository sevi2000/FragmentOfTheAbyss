package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.formiko.fragmentsoftheabyss.model.entity.Box;
import com.formiko.fragmentsoftheabyss.model.entity.Door;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.utils.TextureGenerator;
import com.formiko.fragmentsoftheabyss.view.interfaceModel.ActorsEntity;

public class DoorActor extends Actor implements ActorsEntity {
    private final Texture texture;
    private final SpriteBatch batch;
    private final Door door;

    public DoorActor(Door door) {
        this.texture = TextureGenerator.getTexture(TextureGenerator.TextureType.DOOR);
        this.batch = new SpriteBatch();
        setBounds(door.getX(), door.getY(), door.getWidth(), door.getHeight());
        this.door = door;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
         batch.draw(texture, getX()*getScaleX(), getY()*getScaleY(), getWidth()*getScaleX(), getHeight()*getScaleY());
    }
    public void dispose(){
        texture.dispose();
        batch.dispose();
    }

    @Override
    public Entity getEntity() {
        return door;
    }
}
