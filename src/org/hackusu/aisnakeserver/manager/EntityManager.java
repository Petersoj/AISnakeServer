package org.hackusu.aisnakeserver.manager;

import me.braysen.goodwin.game.entities.Entity;

import java.util.ArrayList;

public class EntityManager {

    private ArrayList<Entity> entities;

    public EntityManager() {
        entities = new ArrayList<>();
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void add(Entity e) {
        entities.add(e);
    }

}
