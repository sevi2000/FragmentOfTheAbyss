package com.formiko.fragmentsoftheabyss;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.formiko.fragmentsoftheabyss.controller.GameController;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.view.GameView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    private SpriteBatch batch;
    private Player player;
    private GameView gameView;
    private GameController gameController;


    @Override
    public void create() {
        batch = new SpriteBatch();

        player = Player.builder()
                .id(EntityType.PLAYER)
                .health(100)
                .maxHealth(100)
                .speed(1)
                .x(0)
                .y(0)
                .attack(0)
                .defense(0)
                .gold(0)
                .experience(0)
                .build();

        gameView = new GameView();

        gameController = new GameController(player);
        Gdx.input.setInputProcessor(gameController);
    }

    @Override
    public void render() {
        gameController.kayPress();
        batch.begin();
        gameView.render(batch, player);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        gameView.dispose();
    }
}