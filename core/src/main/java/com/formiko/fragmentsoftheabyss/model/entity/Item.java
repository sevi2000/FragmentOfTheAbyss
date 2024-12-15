package com.formiko.fragmentsoftheabyss.model.entity;


import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.model.enumGame.ItemType;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Item extends Entity {

    private final ItemType type;

    public Item(EntityType id, int health, float x, float y, int maxHealth, ItemType type) {
        super(id, health, x, y, maxHealth, 0,30,30);
        this.type = type;
    }

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