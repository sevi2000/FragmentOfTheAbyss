package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

public class FieldActor extends Group {
    private Texture mapTexture = new Texture("textures/texture.jpg");
    public FieldActor() {

    }

    /**
     * {@summary Draw the background.}
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(mapTexture, getX(), getY(), getWidth(), getHeight());
        // batch.draw(mapTexture, getX() * getScaleX(), getY() * getScaleY(), getWidth() * getScaleX(), getHeight() * getScaleY());
        super.draw(batch, parentAlpha);
    }

}
