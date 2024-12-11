package com.formiko.fragmentsoftheabyss.view;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.formiko.fragmentsoftheabyss.model.entity.Box;

public class FieldActor extends Group {
    private Texture mapTexture = new Texture("textures/texture.jpg");
    public FieldActor() {
        loadFromFile("levels/level1.txt");
    }

    /**
     * {@summary Draw the background.}
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        //batch.draw(mapTexture, getX(), getY(), getWidth(), getHeight());
        batch.draw(mapTexture, getX() * getScaleX(), getY() * getScaleY(), getWidth() * getScaleX(), getHeight() * getScaleY());
        System.out.println("Number of children: " + getChildren().size);
        for (int i = 0; i < getChildren().size; i++) {
            getChildren().get(i).draw(batch, parentAlpha);
        }
        super.draw(batch, parentAlpha);
    }

    public void loadFromFile(String path) {
        FileHandle file = Gdx.files.internal(path);
        String [] lines = file.readString().split("\n");
        for (String line : lines) {
            if (line.startsWith("Wall")) {
                Box box = Box.fromString(line);
                BoxActor boxActor = new BoxActor(box);
                boxActor.setSize(box.getWidth(), box.getHeight());
                addActor(boxActor);   
            }
        }
    }

}
