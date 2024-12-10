package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.formiko.fragmentsoftheabyss.model.entity.Box;

public class BoxView {

    private final Texture texture;
    private final Box box;
    private final SpriteBatch batch;

    public BoxView(Box box) {
        this.texture = new Texture("textures/wall.jpg");
        this.box = box;
        this.batch = new SpriteBatch();
    }
    
    public void render() {
        batch.begin();
        batch.draw(texture,  box.getX(), box.getY());
        batch.end();
    }
    public void dispose(){
        texture.dispose();
        batch.dispose();
    }
}
