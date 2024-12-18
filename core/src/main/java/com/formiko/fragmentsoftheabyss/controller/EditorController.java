package com.formiko.fragmentsoftheabyss.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.formiko.fragmentsoftheabyss.model.entity.*;
import com.formiko.fragmentsoftheabyss.model.enumGame.ItemType;
import com.formiko.fragmentsoftheabyss.view.*;
import com.formiko.fragmentsoftheabyss.view.interfaceModel.ActorsEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class EditorController extends InputAdapter {

    private FieldActor actor;
    private EntityTypeEditor current;
    private Rectangle cursor;
    private static final int BLOC_SIZE = 190;

    public EditorController(FieldActor actor) {
        this.actor = actor;
        MusicController.playMainMusic();
        this.current = EntityTypeEditor.BOX;
        GameScreen.setText("Editor mode - Item use: " + current.name());
        this.cursor = new Rectangle(Gdx.input.getX(), Gdx.input.getY(), 30, 30);
    }

    private enum EntityTypeEditor {

        PLAYER,
        MONSTER,
        DOOR,
        POTION_HEALTH,
        POTION_SPEED,
        KEY,
        BOX,
        BOX_TOP,
        BOX_BOTTOM,
        BOX_LEFT,
        BOX_RIGHT,
        BOX_10_V_TOP,
        BOX_10_H_TOP
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        screenY = Gdx.graphics.getHeight() - screenY;
        this.cursor.setX(screenX-25);
        this.cursor.setY(screenY);
        return super.mouseMoved(screenX, screenY);
    }

    public void touchScreen() {

        float screenX = Gdx.input.getX() - 25;
        float screenY = Gdx.graphics.getHeight() - Gdx.input.getY();

        if (!Gdx.input.isButtonJustPressed(Input.Keys.CONTROL_LEFT) && (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))) {
            switch (this.current) {
                case PLAYER -> this.actor.addActorAndEntity(new PlayerActor(Player.builder().attack(100).hitRadius(100).health(100).maxHealth(100).x(screenX).y(screenY).speed(5).build()));
                case MONSTER -> this.actor.addActorAndEntity(new MonsterActor(Monster.builder().attack(30).health(100).maxHealth(100).hitRadius(50).x(screenX).y(screenY).speed(4).build()));
                case DOOR -> this.actor.addActorAndEntity(new DoorActor(Door.builder().x(screenX).y(screenY).build()));
                case BOX_10_V_TOP -> {
                    for (int i = 1; i < 11; i++) {
                        this.actor.addActorAndEntity(new BoxActor(Box.builder().x(screenX).y(screenY + BLOC_SIZE*i).build()));
                    }
                }
                case BOX_10_H_TOP -> {
                    for (int i = 1; i < 11; i++) {
                        this.actor.addActorAndEntity(new BoxActor(Box.builder().x(screenX+ BLOC_SIZE*i).y(screenY).build()));
                    }
                }
                case BOX_BOTTOM -> placeBox(0, -BLOC_SIZE);
                case BOX_RIGHT -> placeBox(BLOC_SIZE, 0);
                case BOX_LEFT -> placeBox(-BLOC_SIZE, 0);
                case BOX_TOP -> placeBox(0, 50);
                case POTION_HEALTH -> this.actor.addActorAndEntity(new ItemActor(Item.builder().x(screenX).y(screenX).type(ItemType.POTION_HEALTH).build()));
                case POTION_SPEED-> this.actor.addActorAndEntity(new ItemActor(Item.builder().x(screenX).y(screenX).type(ItemType.POTION_SPEED).build()));
                case KEY -> this.actor.addActorAndEntity(new ItemActor(Item.builder().x(screenX).y(screenX).type(ItemType.KEY).build()));


                default -> {
                    this.actor.addActorAndEntity(new BoxActor(Box.builder().x(screenX).y(screenY).build()));
                    var isPlaced = false;
                    for (Actor actorChild : actor.getChildren()) {
                        if (actorChild instanceof ActorsEntity actorsEntity) {
                            if (cursor.overlaps(actorsEntity.getEntity().getBounds())) {
                                isPlaced = true;
                            }
                        }
                    }
                    if (!isPlaced){
                        this.actor.addActorAndEntity(new BoxActor(Box.builder().x(screenX).y(screenY).build()));
                    }
                }
            }
        }else if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
                for (Actor actorChild : actor.getChildren()) {
                    if (actorChild instanceof ActorsEntity actorsEntity) {
                        if (cursor.overlaps(actorsEntity.getEntity().getBounds())) {
                            actor.removeActorAndEntity(actorChild);
                        }
                    }
                }
            }
        }
    }


    private void placeBox(int i, int j){
        for (Actor actorChild : actor.getChildren()) {
            if (actorChild instanceof ActorsEntity actorsEntity) {
                if (cursor.overlaps(actorsEntity.getEntity().getBounds())) {
                    this.actor.addActorAndEntity(new BoxActor(Box.builder()
                            .x(actorsEntity.getEntity().getX()+i)
                            .y(actorsEntity.getEntity().getY()+j)
                            .build()));
                }
            }
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.N)) {
            nextComposant();
            GameScreen.setText("Editor mode - Item use: " + current.name());
        } else if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            previousComposant();
            GameScreen.setText("Editor mode - Item use: " + current.name());
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            GameScreen.setText("Editor mode - Save file at : " + Gdx.files.internal("levels/" + nextLevelToBeCreated()).path());
            try {
                Files.writeString(Path.of(Gdx.files.internal("levels/" + nextLevelToBeCreated()).path()), actor.getField().toJson());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.U)) {
            if (actor.getChildren().size > 0) {
                GameScreen.setText("Editor mode - Action undo ");
                actor.removeActorAndEntity(actor.getChildren().get(actor.getChildren().size - 1));
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            for (int i = 0; i < 1500; i += BLOC_SIZE) {
                for (int j = 0; j < 1500; j += BLOC_SIZE) {
                    this.actor.addActorAndEntity(new BoxActor(Box.builder()
                            .x(i)
                            .y(j)
                            .build()));
                }
            }
            GameScreen.setText("Editor mode - All wall " + actor.getChildren().size);

        } else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            this.actor.getChildren().clear();
            this.actor.getField().getListEntityOnField().clear();
            GameScreen.setText("Editor mode - Action clear map");
        }
        return super.keyDown(keycode);
    }

    private void nextComposant() {
        int nextIndex = (Arrays.stream(EntityTypeEditor.values()).toList().indexOf(this.current) + 1) % EntityTypeEditor.values().length;
        this.current = EntityTypeEditor.values()[nextIndex];
    }

    private void previousComposant() {
        if ((Arrays.stream(EntityTypeEditor.values()).toList().indexOf(this.current)) == 0){
            int nextIndex = EntityTypeEditor.values().length - 1;
            this.current = EntityTypeEditor.values()[nextIndex];
        }else{
            int nextIndex = (Arrays.stream(EntityTypeEditor.values()).toList().indexOf(this.current) - 1) % EntityTypeEditor.values().length;
            this.current = EntityTypeEditor.values()[nextIndex];
        }
    }

    private String nextLevelToBeCreated() {
        int result = 5;
        File file = new File(Gdx.files.internal("levels").path());
        if (file.isDirectory()) {
            result = Arrays.stream(Objects.requireNonNull(file.list())).map(s -> s.replace("level", "").replace(".json", "")).mapToInt(Integer::parseInt).max().orElse(1) + 1;
        }
        return "level"+result+".json";
    }
}
