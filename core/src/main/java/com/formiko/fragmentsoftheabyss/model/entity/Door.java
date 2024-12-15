package com.formiko.fragmentsoftheabyss.model.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import lombok.Builder;

@lombok.Getter
public class Door extends Entity{

    private static final String WALL = "Wall";
    private boolean isVisible = false;
    @Builder
    public Door(float x, float y, int maxHealth, float width, float height) {
        super(EntityType.DOOR, 0, x, y, maxHealth, 0,width,height);
        this.isVisible = false;
    }
    public Door(){super(EntityType.BOX,0,0,0,0,0,0,0);}
    public static Box fromString(String strBox) {
        String regex = "(" + WALL + ") size=\\((\\d+),(\\d+)\\) position=\\((\\d+),(\\d+)\\)";

        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);

        // Match the pattern in the input string
        Matcher matcher = pattern.matcher(strBox);

        // Check if a match is found
        if (matcher.find()) {
            // Print the matched pattern
            System.out.println("Match found: " + matcher.group());
            Box box = Box.builder()
                    .x(Integer.parseInt(matcher.group(4)))
                    .y(Integer.parseInt(matcher.group(5)))
                    .maxHealth(100)
                    .width(Integer.parseInt(matcher.group(2)))
                    .height(Integer.parseInt(matcher.group(3)))
                .build();
            return box;
        } else {
            System.out.println("No match found.");
            return null;
        }
    }

    @Override
    public String toString() {
        return "Wall size=(" + (int)width + "," + (int)height + ") position=(" + (int)x + "," + (int)y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Box box = (Box) o;

        if (Float.compare(box.x, x) != 0) {
            return false;
        }
        if (Float.compare(box.y, y) != 0) {
            return false;
        }
        if (Float.compare(box.width, width) != 0) {
            return false;
        }
        return Float.compare(box.height, height) == 0;
    }

    public void open() {
        isVisible = true;
    }
}
