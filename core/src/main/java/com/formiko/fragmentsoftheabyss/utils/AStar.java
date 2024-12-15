package com.formiko.fragmentsoftheabyss.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import com.badlogic.gdx.math.Rectangle;
import com.formiko.fragmentsoftheabyss.Main;
import com.formiko.fragmentsoftheabyss.model.Coordinates;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;

public class AStar {
    public static List<Coordinates> findPath(Entity entity, Entity target) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Set<Node> closedList = new HashSet<>();

        Node start = new Node((int) entity.getCenterX(), (int) entity.getCenterY());
        Node end = new Node((int) target.getCenterX(), (int) target.getCenterY());

        openList.add(start);

        int k = 0;

        while (!openList.isEmpty() && k < 1000000) {
            k++;
            Node current = openList.poll();
            closedList.add(current);

            if (current.equals(end)) {
                return reconstructPath(current);
            }

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x == 0 && y == 0) continue;

                    Node neighbor = new Node(current.x + x, current.y + y);
                    if (closedList.contains(neighbor)) continue;
                    if (isUnreachableCoordinate(neighbor.x, neighbor.y, (int)entity.getWidth(), (int)entity.getHeight())) continue;

                    neighbor.gCost = current.gCost + 1;
                    neighbor.hCost = heuristic(neighbor, end);
                    neighbor.fCost = neighbor.gCost + neighbor.hCost;
                    neighbor.parent = current;


                    if (openList.contains(neighbor)) {
                        Node existing = openList.stream().filter(n -> n.equals(neighbor)).findFirst().get();
                        if (neighbor.gCost < existing.gCost) {
                            existing.gCost = neighbor.gCost;
                            existing.fCost = existing.gCost + existing.hCost;
                            existing.parent = neighbor.parent;
                        }
                    } else {
                        openList.add(neighbor);
                    }

                }
            }
        }
        // if(k >= 10000) {
        //     System.out.println("Path not found");
        // }
        return Collections.emptyList();
    }
    private static List<Coordinates> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        List<Coordinates> coordinates = path.stream().map(n -> new Coordinates(n.x, n.y)).collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        // System.out.println(coordinates);
        return coordinates;

    }
    private static double heuristic(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); // Manhattan Distance
    }
    private static boolean isUnreachableCoordinate(int centerX, int centerY, int width, int height) {
        List<Entity> box = Main.getField().getBoxEntity();
        Rectangle entityBounds = new Rectangle(centerX - (width/2f) +1, centerY - (height/2f) +1, width -1, height -1);
        for (Entity entity : box) {
            if(entity.getBounds().overlaps(entityBounds)) {
                return true;
            }
        }
        return false;
    }
}


class Node implements Comparable<Node> {
    int x, y;
    double gCost, hCost, fCost;
    Node parent;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.fCost, other.fCost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}