package me.braysen.goodwin.game.entities;


import java.io.Serializable;
import java.util.UUID;

public class Food extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    public Food(int x, int y, UUID uuid) {
        super(uuid, x, y);
    }

}
