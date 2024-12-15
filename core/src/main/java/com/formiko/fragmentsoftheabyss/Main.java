package com.formiko.fragmentsoftheabyss;

import java.util.Optional;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.formiko.fragmentsoftheabyss.controller.GameController;
import com.formiko.fragmentsoftheabyss.model.Field;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.view.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private Player player = null;
    private GameController gameController;
    private static GameScreen gameScreen;
    private int level = 1;
    public static ShapeRenderer shapeRenderer;
    // public LabelActor labelActor;
    // private Stage labelStage;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        gameScreen = new GameScreen(level);
        Field field = gameScreen.getFieldActor().getField();
        Field field2 = new Field();
        // System.out.println(field2.toJson());
        player = field.getPlayer();
        setScreen(gameScreen);
        gameController = new GameController(player, gameScreen.getFieldActor());
        Gdx.input.setInputProcessor(gameController);        
        // System.out.println(field.getListEntityOnField().stream().filter(e -> e.getId() == EntityType.MONSTER).toList().size());
        // labelStage = new Stage();
        // setText("Level " + level);
    }

    @Override
    public void render() {
        Optional<Boolean> nextLevel = gameController.kayPress();
        gameController.animatMonster();
        if (nextLevel.isPresent()) {
            level++;
            if(level > 4) {
                // System.out.println("You win");
                GameScreen.setText("The abyss is closed. You win !");
            } else {
                create();
            }
        } else if (player.getHealth() <= 0) {
            // System.out.println("You lose");
            level = 1;
            create();
            
        }
        super.render(); 
        // gameView.render(player);
        // boxView.render();
        // labelStage.draw();
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