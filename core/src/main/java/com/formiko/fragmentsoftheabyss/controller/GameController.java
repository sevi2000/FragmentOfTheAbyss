package com.formiko.fragmentsoftheabyss.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.formiko.fragmentsoftheabyss.model.entity.Player;

public class GameController extends InputAdapter {
    private final Player player;

    public GameController(Player player) {
        this.player = player;
    }

    public void kayPress(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.move(0, player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.move(0, -player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            player.move(-player.getSpeed(), 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.move(player.getSpeed(), 0);
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
}
