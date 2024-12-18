package com.formiko.fragmentsoftheabyss;

import java.util.Optional;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.formiko.fragmentsoftheabyss.controller.EditorController;
import com.formiko.fragmentsoftheabyss.controller.GameController;
import com.formiko.fragmentsoftheabyss.model.Field;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.view.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private Player player = null;
    private GameController gameController;
    private EditorController editorController;

    private static GameScreen gameScreen;
    public static ShapeRenderer shapeRenderer;

    public static int level = 1;
    public static int maxLevel = 4;
    public static boolean isEditor = false;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        gameScreen = new GameScreen(level);
        Field field = gameScreen.getFieldActor().getField();
        if(field.getPlayer().isPresent()) {
            player = field.getPlayer().get();
        }
        setScreen(gameScreen);
        gameController = new GameController(player, gameScreen.getFieldActor());
        if(isEditor) {
            editorController = new EditorController(gameScreen.getFieldActor());
            Gdx.input.setInputProcessor(editorController);
        }
        else Gdx.input.setInputProcessor(gameController);
    }

    @Override
    public void render() {
        if(isEditor){
            editorController.touchScreen();
        }else {
            Optional<Boolean> nextLevel = gameController.kayPress();
            gameController.animatMonster();
            if (nextLevel.isPresent() && nextLevel.get()) {
                if (level >= maxLevel) {
                    GameScreen.setText("The abyss is closed. You win !");
                } else {
                    level++;
                    create();
                }
            } else if (player.getHealth() <= 0) {
                // System.out.println("You lose");
                level = 1;
                create();
            }
        }
        super.render();
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