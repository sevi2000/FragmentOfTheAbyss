package com.formiko.fragmentsoftheabyss;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.formiko.fragmentsoftheabyss.controller.GameController;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.view.GameView;
import com.formiko.fragmentsoftheabyss.view.PlayerUIView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    private SpriteBatch batch;
    private Player player;
    private GameView gameView;
    private GameController gameController;
    private PlayerUIView playerUIView;


    @Override
    public void create() {
        batch = new SpriteBatch();

        player = Player.builder()
                .id(EntityType.PLAYER)
                .health(1000)
                .maxHealth(1000)
                .speed(1)
                .x(0)
                .y(0)
                .attack(0)
                .defense(0)
                .gold(0)
                .experience(0)
                .build();

        /* View */
        playerUIView = new PlayerUIView();
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
        playerUIView.render(player);
    }

    @Override
    public void dispose() {
        batch.dispose();
        gameView.dispose();
        playerUIView.dispose();
    }
}