package com.formiko.fragmentsoftheabyss.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.formiko.fragmentsoftheabyss.model.entity.Door;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.model.entity.Monster;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import com.formiko.fragmentsoftheabyss.utils.Parser;

@lombok.AllArgsConstructor
@lombok.Builder
@lombok.Getter
public class Field {

    private int width;
    private int height;
    private ArrayList<Entity> listEntityOnField;

   public Field() {
       listEntityOnField = new ArrayList<>();
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
   public List<Monster> getMonsterEntity() {
      return listEntityOnField.stream().filter(e -> e.getId() == EntityType.MONSTER).map( e -> (Monster)e).toList();
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

    public Optional<Player> getPlayer() {
        for(Entity entity: getListEntityOnField()){
            if(entity instanceof Player player){
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }
}
