package com.formiko.fragmentsoftheabyss;

import java.util.Optional;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.formiko.fragmentsoftheabyss.controller.GameController;
import com.formiko.fragmentsoftheabyss.model.Field;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.view.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private Player player = null;
    private GameController gameController;
    private static GameScreen gameScreen;
    private int level = 1;

    @Override
    public void create() {
        gameScreen = new GameScreen(level);
        Field field = gameScreen.getFieldActor().getField();
        Field field2 = new Field();
        System.out.println(field2.toJson());
        player = field.getPlayer();
        setScreen(gameScreen);
        gameController = new GameController(player, gameScreen.getFieldActor());
        Gdx.input.setInputProcessor(gameController);        
        System.out.println(field.getListEntityOnField().stream().filter(e -> e.getId() == EntityType.MONSTER).toList().size());
    }

    @Override
    public void render() {
        Optional<Boolean> nextLevel = gameController.kayPress();
        gameController.animatMonster();
        if (nextLevel.isPresent()) {
            level++;
            if(level > 4) {
                System.out.println("You win");
            } else {
                create();
            }
        super.render();
        }
        // gameView.render(player);
        // boxView.render();
    }

    @Override
    public void dispose() {
        //gameView.dispose();
        //boxView.dispose();
        //playerUIView.dispose();
    }

    public static GameScreen getGameScreen() {
        return gameScreen;
    }
    public static Field getField() {
        return gameScreen.getFieldActor().getField();
    }
}