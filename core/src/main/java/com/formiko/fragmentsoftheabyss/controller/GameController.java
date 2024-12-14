package com.formiko.fragmentsoftheabyss.controller;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.model.entity.Monster;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.view.FieldActor;

public class GameController extends InputAdapter {
    private final Player player;
    public FieldActor actor;

    public GameController(Player player, FieldActor actor) {
        this.player = player;
        this.actor = actor;
        MusicController.playMainMusic();
    }

    public void kayPress(){
        
        if (Gdx.input.isKeyPressed(Input.Keys.W) ||
                Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    player.move(0, 1);
                    if (checkCollision()) {
                        player.move(0, - 1);
                    }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) ||
                Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    player.move(0, -1);
                    if (checkCollision()) {
                        player.move(0, 1);
                    }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q) ||
                Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    player.move(-1, 0);
                    if (checkCollision()) {
                        player.move(1, 0);
                    }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) ||
                Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    player.move(1, 0);
                    if (checkCollision()) {
                        player.move(-1, 0);
                    }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.damage(20);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
            player.revive(100);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            int radius = 100;
            Monster monster = actor.getField().getListEntityOnField().stream().filter(e -> e.getId() == EntityType.MONSTER)
                    .map(e -> (Monster) e).filter(e -> e.getX() < player.getX() + radius && e.getX() > player.getX() - radius)
                    .filter(e -> e.getY() < player.getY() + radius && e.getY() > player.getY() - radius).findFirst().orElse(null);
            if (monster != null) {
                monster.damage(player.getAttack());
            }
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

    public void animatMonster(){
        for (Entity item : actor.getField().getListEntityOnField()) {
            if (item.getId() == EntityType.MONSTER) {
                System.out.println("Monster move");
                float deltaX = player.getX() - item.getX();
                float deltaY = player.getY() - item.getY();
                if (!checkCollision()) {
                    if (Math.abs(deltaX) > Math.abs(deltaY)) {
                        if (deltaX > 0) {
                            item.move(1, 0);
                        } else {
                            item.move(-1, 0);
                        }
                    } else {
                        if (deltaY > 0) {
                            item.move(0, 1);
                        } else {
                            item.move(0, -1);
                        }
                    }  
                } else{
                    player.damage(((Monster)item).getAttack());
                }
                
                /*if (checkCollision()) {
                    item.move(-1, 0);
                }
                item.move(1, 0);*/
            }
        }
    }

    public boolean checkCollision(){
        for (Entity item : actor.getField().getListEntityOnField()) {  
           if (player != item && player.collidesWith(item)) {
            return true;
           }
        }
        return false;
    }
}
