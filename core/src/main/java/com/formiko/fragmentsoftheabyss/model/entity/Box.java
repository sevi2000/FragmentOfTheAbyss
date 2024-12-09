package com.formiko.fragmentsoftheabyss.model.entity;

import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import lombok.Builder;
import lombok.Getter;
@lombok.Getter
public class Box extends Entity{
    @Builder
    public Box(float x, float y, int maxHealth, float width, float height) {
        super(EntityType.BOX, 10, x, y, maxHealth, 0,width,height);
        this.width = width;
        this.height = height;
    }
}
