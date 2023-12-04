package org.shmunky.environment;

import org.shmunky.containers.ListOfUnits;
import org.shmunky.sprites.MilitaryUnit;
import org.shmunky.sprites.Unit;

public class Tile {
    private int x;
    private int y;
    private boolean city;
    private boolean improved;
    private ListOfUnits unitList;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.city = false;
        this.improved = false;
        this.unitList = new ListOfUnits();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isCity() {
        return this.city;
    }

    public boolean isImproved() {
        return this.improved;
    }

    // turns the tile into a city if it wasn’t already
    public void buildCity() {
        if (this.isCity()) {
        } else {
            this.city = true;
        }
    }

    // improves the tile if it wasn’t already
    public void buildImprovement() {
        if (this.isImproved()) {
        } else {
            this.improved = true;
        }
    }

    // check military unit, then check fits enemy occupied
    public boolean addUnit(Unit unit) {
        if(!(unit instanceof MilitaryUnit)){
            this.unitList.addUnit(unit);
            return true;
        } else if(!enemyOccupied((MilitaryUnit) unit)){
            this.unitList.addUnit(unit);
            return true;
        }else{
            return false;
        }
    }

    // checks if no military unit of a different faction (the enemies’ army!) is stationed here
    private boolean enemyOccupied(MilitaryUnit munit) {
        for (int i = 0; i < this.unitList.getSize(); i++) {
            if (this.unitList.getUnit(i) instanceof MilitaryUnit && !(this.unitList.getUnit(i).getFaction().equalsIgnoreCase(munit.getFaction()))) {
                return true;
            }
        }
        return false;
    }

    // removes from list, return true if success, false otherwise
    public boolean removeUnit(Unit unit) {
        for(int i = 0; i < this.unitList.getSize(); i++){
            if(unit.equals(this.unitList.getUnit(i))){
                unitList.removeUnit(unit);
                return true;
            }
        }
        return false;
    }

    // returns enemy unit with lowest health appearing first in list on tile, null otherwise
    public Unit selectWeakEnemy(String faction) {
        Unit currentWeakest = null;

        // instantiate with first enemy unit found
        for(int i = 0; i < this.unitList.getSize(); i++){
            if(!this.unitList.getUnit(i).getFaction().equalsIgnoreCase(faction)){
                currentWeakest = this.unitList.getUnit(i);
                break;
            }
        }

        // iterate and compare, exit with lowest health
        for(int i = 0; i < this.unitList.getSize(); i++){
            if( (!this.unitList.getUnit(i).getFaction().equalsIgnoreCase(faction)) && (this.unitList.getUnit(i).getHP() < currentWeakest.getHP()) ){
                currentWeakest = this.unitList.getUnit(i);
            }
        }
        return currentWeakest;
    }

    // returns distance between two tiles with distance formula
    public static double getDistance(Tile a, Tile b) {
        return (double)Math.sqrt(Math.pow((double)(a.getX() - b.getX()), 2) + Math.pow((double)(a.getY() - b.getY()), 2));
    }
}
