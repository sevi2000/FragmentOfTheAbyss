package com.formiko.fragmentsoftheabyss.view.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.formiko.fragmentsoftheabyss.Main;
import com.formiko.fragmentsoftheabyss.view.GameScreen;
import com.github.tommyettinger.textra.TextraLabel;

public class LabelActor extends Table {
    private static BitmapFont bmf;
    private static Label.LabelStyle labelStyle;

    private TextraLabel label;
    private Color color;

    public LabelActor(String text) {
        if(bmf == null) {
            bmf = new BitmapFont(Gdx.files.internal("fonts/dominican.fnt"));
            labelStyle = new Label.LabelStyle(bmf, Color.BLACK);
            {
                SpriteBatch batch = new SpriteBatch();
                Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
                pixmap.setColor(Color.WHITE);
                pixmap.drawPixel(0, 0);
                Texture texture = new Texture(pixmap);
                pixmap.dispose();
                TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);
            }
        }
        label = new TextraLabel(text, labelStyle);
        label.setAlignment(Align.center);
        label.setSize(label.getPrefWidth(), label.getPrefHeight());

        Actor backgroundActor = new BackgroundActor(new Color(1, 1, 1, 0.9f));
        backgroundActor.setSize(label.getWidth(), label.getHeight());
        addActor(backgroundActor);
        addActor(label);
    }
}


class BackgroundActor extends Actor {
    public Color clearColor;
    public BackgroundActor(Color clearColor) {
        this.clearColor = clearColor;
        setPosition(0, 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        Main.shapeRenderer.setProjectionMatrix(GameScreen.getCamera().combined);
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.shapeRenderer.setColor(Color.RED);
        Main.shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
        Main.shapeRenderer.end();
        batch.begin();
    }
}