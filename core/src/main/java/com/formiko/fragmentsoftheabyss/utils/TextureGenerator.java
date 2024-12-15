package com.formiko.fragmentsoftheabyss.utils;

import com.badlogic.gdx.graphics.Texture;
import java.util.Map;
public class TextureGenerator {
    private TextureGenerator(){}
    public enum TextureType {
        PLAYER,
        MONSTER,
        BOX, DOOR
    }

   public static Map<TextureType, Texture> textures = Map.of(
        TextureType.PLAYER, new Texture("player.png"),
        TextureType.MONSTER, new Texture("enemy.png"),
        TextureType.BOX, new Texture("textures/wall.jpg"),
        TextureType.DOOR, new Texture("door.png")
    );
    public static Texture getTexture(TextureType textureType){
        return textures.get(textureType);
    }
}
