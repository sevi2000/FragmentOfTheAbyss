package com.formiko.fragmentsoftheabyss;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.formiko.fragmentsoftheabyss.controller.GameController;
import com.formiko.fragmentsoftheabyss.model.entity.Box;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.view.BoxView;
import com.formiko.fragmentsoftheabyss.view.GameView;
import com.formiko.fragmentsoftheabyss.view.PlayerUIView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    private SpriteBatch batch;
    private Player player;
    private GameView gameView;
    private GameController gameController;
    private BoxView boxView;
    private Box box;
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
                .width(60)
                .height(80)
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
        boxView = new BoxView();
        box = Box.builder()
                .x(100)
                .y(100)
                .maxHealth(100)
                .width(100)
                .height(100)
                .build();
                gameController.items.add(box);
    }

    @Override
    public void render() {
        gameController.kayPress();
        batch.begin();
        gameView.render(batch, player);
        batch.end();
        boxView.render(box);
        playerUIView.render(player);
        boxView.render(box);
        playerUIView.render(player);
    }

    @Override
    public void dispose() {
        batch.dispose();
        gameView.dispose();
        boxView.dispose();
        playerUIView.dispose();
        boxView.dispose();
        playerUIView.dispose();
    }
}