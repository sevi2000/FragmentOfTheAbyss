package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.view.interfaceModel.ActorsEntity;
public class EntityActor extends Actor implements ActorsEntity {

    protected Entity entity;
    protected final Texture texture;
    protected final SpriteBatch batch;

    public EntityActor(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
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
