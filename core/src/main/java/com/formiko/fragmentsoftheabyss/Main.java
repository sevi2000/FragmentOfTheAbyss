package com.formiko.fragmentsoftheabyss;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.formiko.fragmentsoftheabyss.controller.GameController;
import com.formiko.fragmentsoftheabyss.model.entity.Box;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.view.BoxActor;
import com.formiko.fragmentsoftheabyss.view.BoxView;
import com.formiko.fragmentsoftheabyss.view.GameScreen;
import com.formiko.fragmentsoftheabyss.view.GameView;
import com.formiko.fragmentsoftheabyss.view.PlayerActor;
import com.formiko.fragmentsoftheabyss.view.PlayerUIView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private Player player;
    private GameView gameView;
    private GameController gameController;
    private BoxView boxView;
    private PlayerUIView playerUIView;
    private GameScreen gameScreen;

    @Override
    public void create() {

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
        // playerUIView = new PlayerUIView();
        // gameView = new GameView();

        gameController = new GameController(player);
        Gdx.input.setInputProcessor(gameController);
        Box box = Box.builder()
                .x(100)
                .y(100)
                .maxHealth(100)
                .build();
                gameController.items.add(box);
        // boxView = new BoxView(box);

        gameScreen = new GameScreen();
        setScreen(gameScreen);

        BoxActor boxActor = new BoxActor(box);
        // boxActor.setSize(box.getWidth(), box.getHeight());
        gameScreen.addActorToField(boxActor);
        PlayerActor playerActor = new PlayerActor(player);
        // playerActor.setSize(player.getWidth(), player.getHeight());
        gameScreen.addActorToField(playerActor);
    }

    @Override
    public void render() {
        gameController.kayPress();
        super.render();
        // gameView.render(player);
        // boxView.render();
        // playerUIView.render(player);
    }

    @Override
    public void dispose() {
        gameView.dispose();
        boxView.dispose();
        playerUIView.dispose();
    }
}