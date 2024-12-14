package com.formiko.fragmentsoftheabyss.controller;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

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

    public void kayPress() {

        if (Gdx.input.isKeyPressed(Input.Keys.W) ||
                Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.move(0, 1);
            if (checkCollision(player).isPresent()) {
                player.move(0, -1);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) ||
                Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.move(0, -1);
            if (checkCollision(player).isPresent()) {
                player.move(0, 1);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q) ||
                Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.move(-1, 0);
            if (checkCollision(player).isPresent()) {
                player.move(1, 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) ||
                Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.move(1, 0);
            if (checkCollision(player).isPresent()) {
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
            Monster monster = actor.getField().getListEntityOnField().stream()
                    .filter(e -> e.getId() == EntityType.MONSTER)
                    .map(e -> (Monster) e)
                    .filter(e -> e.getX() < player.getX() + radius && e.getX() > player.getX() - radius)
                    .filter(e -> e.getY() < player.getY() + radius && e.getY() > player.getY() - radius).findFirst()
                    .orElse(null);
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

    private void moveToPosition(Entity item, float deltaX, float deltaY) {

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
    }

    public void animatMonster() {
        for (Entity monster : actor.getField().getListEntityOnField()) {
            if (monster.getId() == EntityType.MONSTER) {
                System.out.println("Monster move");
                Optional<Entity> entity = checkCollision(monster);
                float deltaX = player.getX() - monster.getX();
                float deltaY = player.getY() - monster.getY();
                if (entity.isPresent() && entity.get().getId() == EntityType.BOX) {
                    deltaX = new Random().nextInt(/*actor.getField().getWidth()*/1000) - monster.getX();
                    deltaY = new Random().nextInt(1000)+ 500 - monster.getY();
                    moveToPosition(monster, deltaX, deltaY);
                } else if (entity.isPresent() && entity.get().getId() == EntityType.PLAYER) {
                    player.damage(((Monster) monster).getAttack());
                } else {
                    moveToPosition(monster, deltaX, deltaY);
                }

                /*
                 * if (checkCollision()) {
                 * item.move(-1, 0);
                 * }
                 * item.move(1, 0);
                 */
            }
        }
    }

    public Optional<Entity> checkCollision(Entity current) {
        for (Entity item : actor.getField().getListEntityOnField()) {
            if (current != item && current.collidesWith(item)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }
}
