package fr.epsi.jconte.model.impl;

import fr.epsi.jconte.model.IPlayer;

public class Player implements IPlayer {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
