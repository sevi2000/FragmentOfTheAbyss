package com.formiko.fragmentsoftheabyss.view;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.formiko.fragmentsoftheabyss.model.entity.Box;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import lombok.Getter;

@Getter
public class FieldActor extends Group {
    private final Texture mapTexture = new Texture("textures/texture.jpg");
    private final ArrayList<Entity> listEntityOnField;

    public FieldActor() {
        this.listEntityOnField = new ArrayList<Entity>();
        loadFromFile("levels/level1.txt");
    }

    /**
     * {@summary Draw the background.}
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(mapTexture, getX(), getY(), getWidth(), getHeight());
        //batch.draw(mapTexture, getX() * getScaleX(), getY() * getScaleY(), getWidth() * getScaleX(), getHeight() * getScaleY());
        for (int i = 0; i < getChildren().size; i++) {
            getChildren().get(i).draw(batch, parentAlpha);
        }
    }

    public void loadFromFile(String path) {
        FileHandle file = Gdx.files.internal(path);
        String [] lines = file.readString().split("\n");
        for (String line : lines) {
            if (line.startsWith("Wall")) {
                Box box = Box.fromString(line);
                BoxActor boxActor = new BoxActor(box);//TODO ajouter OPTIONAL
                boxActor.setSize(box.getWidth(), box.getHeight());
                addActor(boxActor);
                listEntityOnField.add(box);
            }
        }
    }
}
