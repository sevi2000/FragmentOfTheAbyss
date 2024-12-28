package com.formiko.fragmentsoftheabyss.view.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;
import com.formiko.fragmentsoftheabyss.model.entity.Monster;
import com.formiko.fragmentsoftheabyss.view.GameScreen;

public class MonsterActor extends EntityActor{

    private final ShapeRenderer shapeRenderer;

    public MonsterActor(Monster monster) {
        super(monster);
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(entity.getX());
        setY(entity.getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        float healPercentage = (float) entity.getHealth() / entity.getMaxHealth();

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
        shapeRenderer.circle(getX(Align.center), getY(Align.center), ((Monster) entity).getHitRadius());

        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.end();

        batch.begin();
    }

}
