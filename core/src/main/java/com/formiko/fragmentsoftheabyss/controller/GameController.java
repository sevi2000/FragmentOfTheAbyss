package com.formiko.fragmentsoftheabyss.controller;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.view.FieldActor;

public class GameController extends InputAdapter {
    private final Player player;
    public FieldActor actor;

    public GameController(Player player, FieldActor actor) {
        this.player = player;
        this.actor = actor;
    }

    public void kayPress(){
        
        if (Gdx.input.isKeyPressed(Input.Keys.W) ||
                Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    player.move(0, player.getSpeed());
                    if (checkCollision()) {
                        player.move(0, - player.getSpeed());
                    }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) ||
                Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    player.move(0, -player.getSpeed());
                    if (checkCollision()) {
                        player.move(0, player.getSpeed());
                    }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q) ||
                Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    player.move(-player.getSpeed(), 0);
                    if (checkCollision()) {
                        player.move(player.getSpeed(), 0);
                    }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) ||
                Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    player.move(player.getSpeed(), 0);
                    if (checkCollision()) {
                        player.move(-player.getSpeed(), 0);
                    }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.damage(20);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
            player.revive(100);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.damage(20);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
            player.revive(100);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        player.setX(screenX);
        player.setY(screenY);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    public boolean checkCollision(){
        for (Entity item : actor.getListEntityOnField()) {
           if (player.collidesWith(item)) {
            return true;
           }
        }
        return false;
    }
}
