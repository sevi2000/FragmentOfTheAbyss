package com.formiko.fragmentsoftheabyss.model.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formiko.fragmentsoftheabyss.utils.Parser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Item, Integer> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    public void addItem(Item item, int quantity) {
        if (quantity <= 0)return;
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public boolean removeItem(Item item, int quantity) {
        if (!items.containsKey(item)) return false;


        int currentQuantity = items.get(item);
        if (currentQuantity < quantity) return false;

        if (currentQuantity == quantity) {
            items.remove(item);
        } else {
            items.put(item, currentQuantity - quantity);
        }
        return true;
    }

    public void showInventory() {
        if (items.isEmpty()) {
            System.out.println("L'inventaire est vide.");
        } else {
            System.out.println("Inventaire:");
            for (Map.Entry<Item, Integer> entry : items.entrySet()) {
                System.out.println("- " + entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    public void saveToFile(String filePath) {
        try {
            ObjectMapper mapper = Parser.getObjectMapper();
            mapper.writeValue(new File(filePath), this);
            System.out.println("Inventaire sauvegardé dans " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Inventory loadFromFile(String filePath) {
        try {
            ObjectMapper mapper = Parser.getObjectMapper();
            return mapper.readValue(new File(filePath), Inventory.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Inventory();
        }
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }
}
