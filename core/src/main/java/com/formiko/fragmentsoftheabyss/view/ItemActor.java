package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.model.entity.Item;
import com.formiko.fragmentsoftheabyss.utils.TextureGenerator;
import com.formiko.fragmentsoftheabyss.view.interfaceModel.ActorsEntity;

public class ItemActor extends Actor implements ActorsEntity {

    private final Texture texture;
    private final SpriteBatch batch;
    private final Item item;

    public ItemActor(Item item) {
        this.texture = textureByItemType(item);
        this.batch = new SpriteBatch();
        setBounds(item.getX(), item.getY(), item.getWidth(), item.getHeight());
        this.item = item;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX()*getScaleX(), getY()*getScaleY(), getWidth()*getScaleX(), getHeight()*getScaleY());
    }
    public void dispose(){
        texture.dispose();
        batch.dispose();
    }

    @Override public Entity getEntity() {
        return item;
    }

    private Texture textureByItemType(Item item){
        return switch (item.getType()) {
            case KEY -> TextureGenerator.getTexture(TextureGenerator.TextureType.KEY);
            case POTION_HEALTH -> TextureGenerator.getTexture(TextureGenerator.TextureType.POTION_HEALTH);
            case POTION_SPEED -> TextureGenerator.getTexture(TextureGenerator.TextureType.POTION_SPEED);
            default -> null;
        };
    }
}
