package com.formiko.fragmentsoftheabyss;

import com.formiko.fragmentsoftheabyss.model.Field;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.formiko.fragmentsoftheabyss.controller.GameController;
import com.formiko.fragmentsoftheabyss.model.entity.Box;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.utils.Parser;
import com.formiko.fragmentsoftheabyss.view.BoxActor;
import com.formiko.fragmentsoftheabyss.view.BoxView;
import com.formiko.fragmentsoftheabyss.view.FieldActor;
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
        gameScreen = new GameScreen("levels/level2.json");
        Field field = gameScreen.getFieldActor().getField();
        player = field.getPlayer();
        setScreen(gameScreen);
        gameController = new GameController(player, gameScreen.getFieldActor());
        Gdx.input.setInputProcessor(gameController);        
        System.out.println(field.getListEntityOnField().stream().filter(e -> e.getId() == EntityType.MONSTER).toList().size());
    }

    @Override
    public void render() {
        gameController.kayPress();
        gameController.animatMonster();
        super.render();
        // gameView.render(player);
        // boxView.render();
    }

    @Override
    public void dispose() {
        //gameView.dispose();
        //boxView.dispose();
        //playerUIView.dispose();
    }
}