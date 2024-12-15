package com.formiko.fragmentsoftheabyss.model;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.formiko.fragmentsoftheabyss.model.entity.Door;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.model.entity.Monster;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.utils.Parser;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.Builder
@lombok.Getter
public class Field {


   private Player player;
   
   private int width;
   private int height;
   private final ArrayList<Entity> listEntityOnField;
   public Field() {
      this.listEntityOnField = new ArrayList<>();
      this.player = Player.builder()
      .x(100)
      .y(100)
      .maxHealth(100)
      .width(50)
      .height(50)
      .build();
      Door door = Door.builder().x(200).y(200).width(100).height(100).build();
      listEntityOnField.add(door);
   }
        
   public String toJson() {
       try {
            return Parser.getObjectMapper().writeValueAsString(this);
        } catch (Exception e) {
            throw new IllegalArgumentException("Flag.toJson(): " + e.getMessage());
        }
    }

    public static Field fromJson(String json) {
      try {
          Field f = Parser.getObjectMapper().readValue(json, Field.class);
          f.listEntityOnField.add(f.player);
          return f;
      } catch (Exception e) {
          throw new IllegalArgumentException("Flag.fromJson(): " + e.getMessage());
      }
   }
   public static Field fromFile(String filePath) {
      return fromJson(Gdx.files.internal(filePath).readString());
   }
   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Field field = (Field) o;
      return width == field.width && height == field.height && listEntityOnField.equals(field.listEntityOnField);
   }
   public List<Entity> getBoxEntity() {
      return listEntityOnField.stream().filter(e -> e.getId() == EntityType.BOX).toList();
   }
   public List<Entity> getMonsterEntity() {
      return listEntityOnField.stream().filter(e -> e.getId() == EntityType.MONSTER).toList();
   }
   public boolean isThereMonster() {
      return listEntityOnField.stream().anyMatch(e -> e.getId() == EntityType.MONSTER);
   }

public void removeEntity(Entity entity) {
   listEntityOnField.remove(entity);
}

public void openDoor() {
   listEntityOnField.stream().filter(e -> e.getId() == EntityType.DOOR).map(e -> (Door) e).findFirst().ifPresent(Door::open);   
}

public Door getDoor() {
   return (Door) listEntityOnField.stream().filter(e -> e.getId() == EntityType.DOOR).findFirst().orElse(null); 
}
}
