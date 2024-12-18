package com.formiko.fragmentsoftheabyss.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import com.formiko.fragmentsoftheabyss.Main;
import com.formiko.fragmentsoftheabyss.model.entity.Box;
import com.formiko.fragmentsoftheabyss.model.entity.Door;
import com.formiko.fragmentsoftheabyss.model.entity.Player;
import com.formiko.fragmentsoftheabyss.model.entity.Entity;
import com.formiko.fragmentsoftheabyss.model.entity.Item;
import com.formiko.fragmentsoftheabyss.model.entity.Monster;
import com.formiko.fragmentsoftheabyss.model.Field;
import com.formiko.fragmentsoftheabyss.view.interfaceModel.ActorsEntity;
import lombok.Getter;

@Getter
public class FieldActor extends Group {

    private final Texture mapTexture;
    private final Field field;

    public FieldActor(Field field) {
        this.mapTexture = new Texture("textures/level" + +Main.level+"_texture.png");//TextureGenerator.getTexture(TextureGenerator.TextureType.MAP);
        this.field = field;
        addActors();
    }

    /**
     * {@summary Draw the background.}
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(mapTexture, getX(), getY(), getWidth(), getHeight());
        //batch.draw(mapTexture, getX() * getScaleX(), getY() * getScaleY(), getWidth() * getScaleX(), getHeight() * getScaleY());
        for (int i = 0; i < getChildren().size; i++) {
            getChildren().get(i).draw(batch, parentAlpha);
        }
    }

    public void addActors() {
        System.out.println("AddActors");

        for (Entity entity : field.getListEntityOnField()) {
            if (entity instanceof Box) {
                Box box = (Box) entity;
                BoxActor boxActor = new BoxActor(box);//TODO ajouter OPTIONAL
                boxActor.setSize(box.getWidth(), box.getHeight());
                addActor(boxActor);
            } else if (entity instanceof Player) {
                Player player = (Player) entity;
                PlayerActor playerActor = new PlayerActor(player);
                playerActor.setSize(player.getWidth(), player.getHeight());
                addActor(playerActor);
                System.out.println("Ajout d'un nouvelle actor");
            } else if (entity instanceof Monster) {
                Monster monster = (Monster) entity;
                MonsterActor monsterActor = new MonsterActor(monster);
                monsterActor.setSize(monster.getWidth(), monster.getHeight());
                addActor(monsterActor);

            } else if (entity instanceof Item item) {
                ItemActor itemActor = new ItemActor(item);
                itemActor.setSize(item.getWidth(), item.getHeight());
                addActor(itemActor);

            } else if (entity instanceof Door && ((Door) entity).isVisible()) {
                Door door = (Door) entity;
                DoorActor doorActor = new DoorActor(door);
                doorActor.setSize(door.getWidth(), door.getHeight());
                addActor(doorActor);
            }
        }
    }

    public void addDoor(Door door) {
        DoorActor doorActor = new DoorActor(door);
        doorActor.setSize(door.getWidth(), door.getHeight());
        addActorAt(0, doorActor);
    }

    public void addEntity(Entity entity) {
        field.getListEntityOnField().add(entity);
    }
    public void removeEntity(Entity entity){
        field.getListEntityOnField().remove(entity);
    }

    public void addActorAndEntity(Actor actor) {
        addActor(actor);
        if (actor instanceof ActorsEntity actorsEntity)
            addEntity(actorsEntity.getEntity());
    }

    public void removeActorAndEntity(Actor actor) {
        removeActor(actor);
        if (actor instanceof ActorsEntity actorsEntity)
            removeEntity(actorsEntity.getEntity());
    }
}
