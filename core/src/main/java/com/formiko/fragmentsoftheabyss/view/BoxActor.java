package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.formiko.fragmentsoftheabyss.model.entity.Box;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.utils.TextureGenerator;
import com.formiko.fragmentsoftheabyss.view.interfaceModel.ActorsEntity;

public class BoxActor extends Actor implements ActorsEntity {
    private final Texture texture;
    private final SpriteBatch batch;
    private final Box box;

    public BoxActor(Box box) {
        this.texture = TextureGenerator.getTexture(TextureGenerator.TextureType.BOX);
        this.batch = new SpriteBatch();
        setBounds(box.getX(), box.getY(), box.getWidth(), box.getHeight());
        this.box = box;
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
        return box;
    }
}
