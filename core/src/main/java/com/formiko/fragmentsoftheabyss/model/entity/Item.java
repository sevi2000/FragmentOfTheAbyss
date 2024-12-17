package com.formiko.fragmentsoftheabyss.model.entity;


import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.model.enumGame.ItemType;
import lombok.Builder;
import lombok.Getter;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Item extends Entity {

    @Getter
    private ItemType type;

    @Builder
    public Item(float x, float y, ItemType type) {
        super(EntityType.ITEM, 0, x, y, 0, 0,112,150);
        this.type = type;
    }
    public Item() {super(EntityType.ITEM, 0, 0, 0, 0, 0, 0, 0);}


    @Override
    public boolean collidesWith(Entity entity) {
        if (this.getBounds().overlaps(entity.getBounds())){
            if (entity instanceof Player player){
                if(type == ItemType.POTION_SPEED){
                    player.setSpeed(2);
                    scheduleItem(30, () -> {
                        player.setSpeed(1);
                        System.out.println("Effet de la potion terminé. Vitesse restaurée : " + player.getSpeed());
                    });

                }else if(type == ItemType.POTION_HEALTH){
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
}