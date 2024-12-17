package com.formiko.fragmentsoftheabyss.utils;

import com.badlogic.gdx.graphics.Texture;
import com.formiko.fragmentsoftheabyss.Main;

import java.util.Map;

public class TextureGenerator {
    private TextureGenerator(){}
    public enum TextureType {
        PLAYER,
        MONSTER,
        BOX,
        DOOR,
        POTION_SPEED,
        POTION_HEALTH,
        KEY,
        MAP
    }

   public static Map<TextureType, Texture> textures = Map.of(
           TextureType.PLAYER, new Texture("textures/player.png"),
           TextureType.MONSTER, new Texture("textures/enemy.png"),
           TextureType.BOX, new Texture("textures/box.png"),
           TextureType.POTION_HEALTH, new Texture("textures/potion_life.png"),
           TextureType.POTION_SPEED, new Texture("textures/potion_speed.png"),
           TextureType.KEY, new Texture("textures/key.png"),
           TextureType.DOOR, new Texture("textures/door.png"),
           TextureType.MAP, new Texture("textures/level" + +Main.level+"_texture.png")
    );
    public static Texture getTexture(TextureType textureType){
        return textures.get(textureType);
    }
}
