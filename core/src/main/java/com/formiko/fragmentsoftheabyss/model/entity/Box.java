package com.formiko.fragmentsoftheabyss.model.entity;

import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import lombok.Builder;

@lombok.Getter
public class Box extends Entity{
    @Builder
    public Box(float x, float y, int maxHealth) {
        super(EntityType.BOX, 0, x, y, maxHealth, 0,50,50);
    }
}
