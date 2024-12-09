package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.formiko.fragmentsoftheabyss.model.entity.Box;

public class BoxView {

    private ShapeRenderer shapeRenderer;
    public BoxView() {
        this.shapeRenderer = new ShapeRenderer();
    }
    
    public void render(Box box) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.rect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
        shapeRenderer.end();
    }
    public void dispose(){
        shapeRenderer.dispose();
    }
}
