package com.formiko.fragmentsoftheabyss.controller;

import java.util.List;
import java.util.Optional;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.formiko.fragmentsoftheabyss.model.entity.Door;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.model.entity.Monster;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.view.FieldActor;
import com.formiko.fragmentsoftheabyss.view.LabelActor;
import com.formiko.fragmentsoftheabyss.view.MonsterActor;

public class GameController extends InputAdapter {
    private final Player player;
    public FieldActor actor;
    public static int FIELD_SIZE = 1000;
    public LabelActor labelActor;

    public GameController(Player player, FieldActor actor) {
        this.player = player;
        this.actor = actor;
        MusicController.playMainMusic();
    }

    public Optional<Boolean> kayPress() {
        if (player.getHealth() <= 0) {
            return Optional.of(false);
            
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W) ||
                Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.move(0, 1);
            if (checkCollisionWithBox(player).isPresent()) {
                player.move(0, -1);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) ||
                Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.move(0, -1);
            if (checkCollisionWithBox(player).isPresent()) {
                player.move(0, 1);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q) ||
                Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.move(-1, 0);
            if (checkCollisionWithBox(player).isPresent()) {
                player.move(1, 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) ||
                Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.move(1, 0);
            if (checkCollisionWithBox(player).isPresent()) {
                player.move(-1, 0);
            }
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
            player.revive(100);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            Monster monster = actor.getField().getListEntityOnField().stream()
                    .filter(e -> e.getId() == EntityType.MONSTER)
                    .map(e -> (Monster) e)
                    .filter(e -> e.getX() < player.getX() + player.getHitRadius() && e.getX() > player.getX() - player.getHitRadius())
                    .filter(e -> e.getY() < player.getY() + player.getHitRadius() && e.getY() > player.getY() - player.getHitRadius()).findFirst()
                    .orElse(null);
            if (monster != null) {
                MusicController.playSoundEffect(EffectType.MonsterAttack);
                monster.damage(player.getAttack());
                if (monster.getHealth() <= 0) {
                Actor monsterActor  = List.of(actor.getChildren()
                .toArray())
                .stream().filter(e -> e instanceof MonsterActor).map(e -> (MonsterActor) e).filter(e -> e.getMonster().equals(monster)).findFirst().orElse(null);
                if (monsterActor != null) {
                    actor.getField().removeEntity(monster);
                    actor.removeActor(monsterActor);
                    if (!actor.getField().isThereMonster()) {
                        actor.getField().openDoor();
                        actor.addDoor(actor.getField().getDoor());
                    }
                }
            }
        }
    }
    if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
            player.revive(100);
        }
        if (checkCollisionWithDoor(player).isPresent() && actor.getField().getDoor().isVisible()) {
            MusicController.playSoundEffect(EffectType.PortalTravel);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Optional.of(true);
        }
        return Optional.empty();
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
            if (monster.getId() == EntityType.MONSTER && monster.canSee(player)) {
                monster.moveToTarget(player);
            }
        }
        Monster hittingPlayerMonster = (Monster) checkCollisionWithMonster(player).orElse(null);
        if(hittingPlayerMonster != null){
            player.damage(hittingPlayerMonster.getAttack());
            MusicController.playSoundEffect(EffectType.MonsterAttack);
        }
    }
    
    public Optional<Entity> checkCollisionWithMonster(Entity current) {
        for (Monster item : actor.getField().getMonsterEntity()) {
            if (current != item && (current.collidesWith(item) || (current.getX() < item.getX() + item.getHitRadius() && current.getX() > item.getX() - item.getHitRadius()) && (current.getY() < item.getY() + item.getHitRadius() && current.getY() > item.getY() - item.getHitRadius()))) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }
    public Optional<Entity> checkCollisionWithBox(Entity current) {
        for (Entity item : actor.getField().getBoxEntity()) {
            if (current != item && current.collidesWith(item)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public Optional <Door> checkCollisionWithDoor(Entity current) {
        for (Entity item : actor.getField().getListEntityOnField()) {
            if (item instanceof Door && current.collidesWith(item)) {
                return Optional.of((Door) item);
            }
        }
        return Optional.empty();
    }
    public boolean isColliding(Entity current) {
        return checkCollisionWithBox(current).isPresent();
    }
    public boolean canMoveThere(Entity current) {
        if(isColliding(current)){
            return false;
        }
        return current.getX() >= 0 && current.getY() >= 0 && current.getWidth() < FIELD_SIZE && current.getHeight() < FIELD_SIZE;
    }
}
