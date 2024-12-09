package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.formiko.fragmentsoftheabyss.model.entity.Player;

public class GameView {
    private final Texture playerTexture;
    private final Texture mapTexture;

    public GameView() {
        playerTexture = new Texture("player.png"); // Assurez-vous d'avoir une image player.png
        mapTexture = new Texture("textures/texture.jpg"); // Une image de fond
    }

    public void render(SpriteBatch batch, Player player) {
        batch.draw(mapTexture, 0, 0);
        batch.draw(playerTexture, player.getX(), player.getY());
    }

    public void dispose() {
        playerTexture.dispose();
        mapTexture.dispose();
    }
}
