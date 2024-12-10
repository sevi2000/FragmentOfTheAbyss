package com.formiko.fragmentsoftheabyss.model.entity;

import com.formiko.fragmentsoftheabyss.model.enumGame.EntityType;
import lombok.Builder;
import lombok.Getter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@lombok.Getter
public class Box extends Entity{

    private static  final String WALL = "Wall";
    @Builder
    public Box(float x, float y, int maxHealth, float width, float height) {
        super(EntityType.BOX, 10, x, y, maxHealth, 0,width,height);
        this.width = width;
        this.height = height;
    }

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
}
