package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.formiko.fragmentsoftheabyss.Main;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.utils.TextureGenerator;
import com.formiko.fragmentsoftheabyss.view.interfaceModel.ActorsEntity;

public class PlayerActor extends Actor implements ActorsEntity {
    private final Texture playerTexture;
    private final Player player;
    private final ShapeRenderer shapeRenderer;

    public PlayerActor(Player player) {
        playerTexture = TextureGenerator.getTexture(TextureGenerator.TextureType.PLAYER);
        this.player = player;
        shapeRenderer = new ShapeRenderer();
        setBounds(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(player.getX());
        setY(player.getY());
        if(!Main.isEditor){
            GameScreen.getCamera().position.set(getX(), getY(), 0);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(playerTexture, player.getX(), player.getY(), getWidth()*getScaleX(), getHeight()*getScaleY());
        float healPercentage = (float) player.getHealth() / player.getMaxHealth();

        float barWidth = 100 * getScaleX();
        float barHeight = 7 * getScaleY();
        float x = getX(Align.center) - barWidth / 2;
        float y = getY(Align.top);

        batch.end();
        shapeRenderer.setProjectionMatrix(GameScreen.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, barWidth, barHeight);

        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x, y, barWidth * healPercentage, barHeight);

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.set(ShapeType.Line);
        shapeRenderer.circle(getX(Align.center), getY(Align.center), player.getHitRadius());
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.end();

        // // Debugging hitbox
        // shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        // shapeRenderer.rect(getX()*getScaleX(), getY()*getScaleY(), getWidth()*getScaleX(), getHeight()*getScaleY());
        // shapeRenderer.end();
        batch.begin();
    }

    @Override
    public Entity getEntity() {
        return player;
    }
}
