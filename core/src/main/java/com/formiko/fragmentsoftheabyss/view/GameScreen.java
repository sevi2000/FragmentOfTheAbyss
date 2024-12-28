package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.formiko.fragmentsoftheabyss.Main;
import com.formiko.fragmentsoftheabyss.model.Field;
import com.formiko.fragmentsoftheabyss.view.actors.LabelActor;
import lombok.Getter;

public class GameScreen implements Screen {
    private static Viewport viewport;
    private static OrthographicCamera camera;
    private final SpriteBatch batch;
    private final Stage stage;
    @Getter
    private FieldActor fieldActor;
    private LabelActor labelActor;
    public static GameScreen instance;

    public GameScreen(int level) {
        instance = this;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        if (Main.isEditor){
            stage = new Stage();
        }else{
            camera.zoom = 0.9f * (1080f / Gdx.graphics.getHeight());
            stage = new Stage(viewport, batch);
        }

        fieldActor = new FieldActor(Field.fromFile("levels/level" + level + ".json"));
        fieldActor.setSize(1500, 1500);
        fieldActor.setPosition(0, 0);
        addActor(fieldActor);

        if(level == 1) {
            setText("Level " + level + ". A breach have been open to the abyss,\n kill all the monster to close it. Move with arrows & hit with space.");
        } else {
            setText("Level " + level);
        }
    }
    public int getMinScreenWidthOrHeight() { return Math.min(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); }
    public float zoomLevel() { return getMinScreenWidthOrHeight() / 1000f; }

    public void addActor(Actor actor) {
        stage.addActor(actor);
    }
    public void addActorToField(Actor actor) {
        fieldActor.addActor(actor);
    }

    public Stage getStage() { return stage; }
    public static OrthographicCamera getCamera() { return camera; }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
       // batch.setProjectionMatrix(camera.combined);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
            viewport.update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        // gameView.dispose();
        // playerUIView.dispose();
    }

    public static void setText(@Null String text){
        if(instance.labelActor != null) {
            instance.labelActor.remove();
        }
        if(text != null) {
            instance.labelActor = new LabelActor(text);
            instance.labelActor.setPosition(0, 0);
            // labelStage.addActor(labelActor);
            instance.addActor(instance.labelActor);
        }
    }
}
