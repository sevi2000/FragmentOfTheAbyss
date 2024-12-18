package com.formiko.fragmentsoftheabyss.utils;

import com.badlogic.gdx.graphics.Texture;
import com.formiko.fragmentsoftheabyss.Main;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;

import java.util.Map;

public class TextureGenerator {
    private TextureGenerator(){}

   public static Map<EntityType, Texture> textures = Map.of(
           EntityType.PLAYER, new Texture("textures/player.png"),
           EntityType.MONSTER, new Texture("textures/enemy.png"),
           EntityType.BOX, new Texture("textures/box.png"),
           EntityType.POTION_HEALTH, new Texture("textures/potion_life.png"),
           EntityType.POTION_SPEED, new Texture("textures/potion_speed.png"),
           EntityType.KEY, new Texture("textures/key.png"),
           EntityType.DOOR, new Texture("textures/door.png"),
           EntityType.MAP, new Texture("textures/level" + +Main.level+"_texture.png")
    );
    public static Texture getTexture(EntityType entityType){
        return textures.get(entityType);
    }
}
