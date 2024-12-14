package com.formiko.fragmentsoftheabyss.view;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formiko.fragmentsoftheabyss.model.entity.Box;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.utils.Parser;
import com.formiko.fragmentsoftheabyss.model.Field;
import lombok.Getter;

@Getter
public class FieldActor extends Group {
    private final Texture mapTexture = new Texture("textures/texture.jpg");
    private final Field field;
    

    public FieldActor(Field field) {
        this.field = field;
        //setBounds(0, 0, field, field.getHeight());
        addActors();
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

    public void addActors() {
        for (Entity entity : field.getListEntityOnField()) {
            if (entity instanceof Box) {
                Box box = (Box) entity;
                BoxActor boxActor = new BoxActor(box);//TODO ajouter OPTIONAL
                boxActor.setSize(box.getWidth(), box.getHeight());
                addActor(boxActor);
            } else if (entity instanceof Player) {
                Player player = (Player) entity;
                PlayerActor playerActor = new PlayerActor(player);
                playerActor.setSize(player.getWidth(), player.getHeight());
                addActor(playerActor);
            }
        }
    }
}
