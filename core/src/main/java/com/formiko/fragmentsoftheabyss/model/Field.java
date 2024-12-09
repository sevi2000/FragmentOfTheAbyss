package com.formiko.fragmentsoftheabyss.model;

import com.formiko.fragmentsoftheabyss.model.entity.Player;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.Builder
@lombok.Getter
public class Field {


   private Player player;
   
   private int width;
   private int height;

   
}
