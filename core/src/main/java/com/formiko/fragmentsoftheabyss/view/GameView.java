package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.formiko.fragmentsoftheabyss.model.entity.Player;

public class GameView {
    private final Texture playerTexture;
    private final Texture mapTexture;
    private final SpriteBatch batch;


    public GameView() {
        batch = new SpriteBatch();
        playerTexture = new Texture("player.png");
        mapTexture = new Texture("textures/texture.jpg");
    }

    public void render(Player player) {
        batch.begin();
        batch.draw(mapTexture, 0, 0);
        batch.draw(playerTexture, player.getX(), player.getY());
        batch.end();
    }

    public void dispose() {
        playerTexture.dispose();
        mapTexture.dispose();
        batch.dispose();
    }
}
