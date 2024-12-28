package com.formiko.fragmentsoftheabyss.model.entity;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.model.enumGame.ItemType;
import com.formiko.fragmentsoftheabyss.view.actors.DoorActor;
import com.formiko.fragmentsoftheabyss.view.actors.ItemActor;
import lombok.Builder;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Item extends Entity {


    @Builder
    public Item(float x, float y, EntityType type) {
        super(type, 0, x, y, 0, 0,112,150);
    }
    public Item() {super(EntityType.ITEM, 0, 0, 0, 0, 0, 0, 0);}


    @Override
    public boolean collidesWith(Entity entity) {
        System.out.println(" POTION COLLISION ");
        if (this.getBounds().overlaps(entity.getBounds())){
            if (entity instanceof Player player){
                if(id == EntityType.POTION_SPEED){
                    player.setSpeed(30);
                    scheduleItem(30, () -> {
                        player.setSpeed(10);
                        System.out.println("Effet de la potion terminé. Vitesse restaurée : " + player.getSpeed());
                    });
                }else if(id == EntityType.POTION_HEALTH){
                    player.setHealth(player.getHealth()+new Random().nextInt(50));
                }
                this.destroy();
            }
        }
        return false;
    }

    public void destroy(){
        this.setId(EntityType.NULL);
    }


    private interface UseItem{
        void consume();
    }

    public void scheduleItem(int delay, UseItem item){
        ScheduledExecutorService observer = Executors.newSingleThreadScheduledExecutor();
        observer.schedule(item::consume, delay, TimeUnit.SECONDS);
        observer.shutdown();
    }

    public Actor toActor(){
        return new ItemActor(this);
    }

}