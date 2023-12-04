package org.shmunky.sprites;

import org.shmunky.environment.Tile;

public class Archer extends MilitaryUnit {
    private int numArrowsAvail;

    public Archer(Tile position, double hp, String faction) {
        super(position, hp, 2, faction, 15.0, 2, 0);
        this.numArrowsAvail = 5;
    }

    // use arrow right away
    @Override
    public void takeAction(Tile tile) {
        if(this.numArrowsAvail<=0){
            this.numArrowsAvail=5;
        } else {
            this.numArrowsAvail--;
            super.takeAction(tile);
        }
    }

    // use == only to check null reference and exact same reference
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Archer)){
            return false;
        }else if (this == obj){
            return true;
        }

        // check super .equals() first in order to invoke numArrowsAvail
        boolean unitEquals = super.equals(obj);
        if(unitEquals){
            return (this.numArrowsAvail == ((Archer)obj).numArrowsAvail);
        };
        return false;
    }
}
