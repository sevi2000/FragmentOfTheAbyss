package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.formiko.fragmentsoftheabyss.model.entity.Monster;

public class MonsterActor extends Actor {
    private final Texture monsterTexture;
    private final Monster monster;
    private final ShapeRenderer shapeRenderer;

    public MonsterActor(Monster monster) {
        monsterTexture = new Texture("enemy.png");
        this.monster = monster;
        shapeRenderer = new ShapeRenderer();
        setBounds(monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(monster.getX());
        setY(monster.getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //batch.draw(playerTexture, getX(), getY(), getWidth(), getHeight());
        batch.draw(monsterTexture, getX()*getScaleX(), getY()*getScaleY(), getWidth()*getScaleX(), getHeight()*getScaleY());

        float healPercentage = (float) monster.getHealth() / monster.getMaxHealth();

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

        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.end();

        // Debugging hitbox
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(getX()*getScaleX(), getY()*getScaleY(), getWidth()*getScaleX(), getHeight()*getScaleY());
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Point);
        shapeRenderer.setColor(Color.BLUE);
        for (int i = 0; i < monster.getPath().size(); i++) {
            shapeRenderer.point(monster.getPath().get(i).x(), monster.getPath().get(i).y(), 0);
        }
        shapeRenderer.end();
        batch.begin();
    }

    public Object getMonster() {
        return this.monster;
    }
}
