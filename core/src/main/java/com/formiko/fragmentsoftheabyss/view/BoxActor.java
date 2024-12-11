package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.formiko.fragmentsoftheabyss.model.entity.Box;

public class BoxActor extends Actor {
    private final Texture texture;
    private final Box box;
    private final SpriteBatch batch;

    public BoxActor(Box box) {
        this.texture = new Texture("textures/wall.jpg");
        this.box = box;
        this.batch = new SpriteBatch();
        setBounds(box.getX(), box.getY(), box.getWidth(), box.getHeight());
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        // batch.draw(texture, getX()*getScaleX(), getY()*getScaleY(), getWidth()*getScaleX(), getHeight()*getScaleY());
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        System.out.println("Drawing box at: " + getX() + ", " + getY());
    }
    public void dispose(){
        texture.dispose();
        batch.dispose();
    }
}
