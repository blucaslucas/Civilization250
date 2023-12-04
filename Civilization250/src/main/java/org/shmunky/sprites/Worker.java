package org.shmunky.sprites;

import org.shmunky.environment.Tile;

public class Worker extends Unit {
    private int numJobsPerformed;

    public Worker(Tile position, double hp, String faction) {
        super(position, hp, 2, faction);
        this.numJobsPerformed = 0;
    }

    @Override
    public void takeAction(Tile tile) {
        boolean isStationedHere = (tile.getX() == this.getPosition().getX() && tile.getY() == this.getPosition().getY());
        if(isStationedHere && !tile.isImproved()){
            System.out.println("Improving");
            tile.buildImprovement();
            this.numJobsPerformed++;
            handleExpended();
        }
    }

    private void handleExpended(){
        if(this.numJobsPerformed>=10){
            System.out.println("Worker expended");
            this.getPosition().removeUnit(this);
        }
    }

    // use == only to check null reference and exact same reference
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Worker)){
            return false;
        }else if (this == obj){
            return true;
        }

        // check super .equals() first in order to invoke numJobsPerformed
        boolean unitEquals = super.equals(obj);
        if(unitEquals){
            return (this.numJobsPerformed == ((Worker)obj).numJobsPerformed);
        };
        return false;
    }

}
