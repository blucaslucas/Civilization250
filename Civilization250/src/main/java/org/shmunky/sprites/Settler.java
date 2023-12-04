package org.shmunky.sprites;

import org.shmunky.environment.Tile;

public class Settler extends Unit {
    public Settler(Tile position, double hp, String faction) {
        super(position, hp, 2, faction);
    }

    @Override
    public void takeAction(Tile tile) {
        boolean isStationedHere = (tile.getX() == this.getPosition().getX() && tile.getY() == this.getPosition().getY());
        if(isStationedHere && !tile.isCity()){
            System.out.println("Building city");
            tile.buildCity();
            tile.removeUnit(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
