package me.braysen.goodwin.game.entities;

import java.io.Serializable;
import java.util.UUID;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int x, y;
    private UUID uuid;

    public Entity(UUID uuid, int x, int y) {
        this.x = x;
        this.y = y;
        this.uuid = uuid;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public UUID getUUID() {
        return uuid;
    }
}
