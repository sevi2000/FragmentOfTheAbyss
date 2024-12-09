package com.formiko.fragmentsoftheabyss.controller;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.formiko.fragmentsoftheabyss.model.entity.Box;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.model.enumGame.Direction;

public class GameController extends InputAdapter {
    private final Player player;
    public ArrayList<Entity> items;

    public GameController(Player player) {
        this.player = player;
        this.items = new ArrayList<>();
       
    }

    public void kayPress(){
        
        if (Gdx.input.isKeyPressed(Input.Keys.W) ||
                Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    if (checkCollision()) {
                        player.move(0, - player.getSpeed() - 10);
                    } else {
                        player.move(0, player.getSpeed());
                    }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) ||
                Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    if (checkCollision()) {
                        player.move(0, player.getSpeed() + 10);
                    } else {
                        player.move(0, -player.getSpeed());
                    }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q) ||
                Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    if (checkCollision()) {
                        player.move(player.getSpeed() + 10, 0);
                    } else {
                        player.move(-player.getSpeed(), 0);
                        
                    }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) ||
                Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    if (checkCollision()) {
                        player.move(-player.getSpeed() - 10, 0);
                    } else {
                        player.move(player.getSpeed(), 0);
                        
                    }
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        player.setX(screenX);
        player.setY(screenY);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    public boolean checkCollision(){
        System.out.println(items.size());
        for (Entity item : items) {
           if (player.collidesWith(item)) {
            return true;
           }
        }
        return false;
    }
}
