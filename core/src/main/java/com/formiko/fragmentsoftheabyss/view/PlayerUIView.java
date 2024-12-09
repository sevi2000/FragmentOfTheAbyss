package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.formiko.fragmentsoftheabyss.model.entity.Player;

public class PlayerUIView {
    private final ShapeRenderer shapeRenderer;

    public PlayerUIView() {
        this.shapeRenderer = new ShapeRenderer();
    }

    public void render(Player player) {

        float healPercentage = (float) player.getHealth() / player.getMaxHealth();

        float barWidth = 100;
        float barHeight = 7;
        float x = player.getX();
        float y = player.getY() + 80;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, barWidth, barHeight);

        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x,y, barWidth * healPercentage, barHeight);
        shapeRenderer.end();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
