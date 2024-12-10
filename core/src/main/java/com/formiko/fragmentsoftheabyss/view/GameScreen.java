package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    private static Viewport viewport;
    private static OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage stage;
    private FieldActor fieldActor;

    public GameScreen() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        // camera.setToOrtho(false, 1000, 1000);
        viewport = new FitViewport(1000, 1000, camera);
        stage = new Stage(viewport, batch);
        fieldActor = new FieldActor();
        fieldActor.setSize(1000, 1000);
        addActor(fieldActor);
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
        batch.setProjectionMatrix(camera.combined);
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

}
