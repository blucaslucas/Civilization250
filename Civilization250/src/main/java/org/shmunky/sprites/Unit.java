package org.shmunky.sprites;

import org.shmunky.environment.Tile;

public abstract class Unit {
    private Tile position;
    private double HP;
    private int movingRange;
    private String faction;

    public Unit(Tile position, double HP, int movingRange, String faction) {
        this.position = position;
        this.HP = HP;
        this.movingRange = movingRange;
        this.faction = faction;

        if (this.getPosition().addUnit(this)) {
            //System.out.println("Successfully created and added unit");
        } else {
            throw new IllegalArgumentException();
        }
    }

    public final Tile getPosition() {
        return this.position;
    }

    public final double getHP() {
        return this.HP;
    }

    public final String getFaction() {
        return this.faction;
    }

    // use == only to check null reference (no equals method for Tile)
    public boolean moveTo(Tile target) {
        if(target == null) {
            System.out.println("Unable to move");
            return false;
        }

        double distance = Tile.getDistance(this.getPosition(), target);
        boolean wasAdded = false;

        if (distance <= this.movingRange) {
            wasAdded = target.addUnit(this);
        }

        if (wasAdded) {
            this.position.removeUnit(this);
            this.position = target;
            return true;
        } else {
            System.out.println("Unable to move");
            return false;
        }
    }

    public boolean receiveDamage(double damage) {
        if (damage < 0) {
            return false;
        }

        if (this.getPosition().isCity()) {
            this.HP = this.HP - (0.9 * damage);
        } else {
            this.HP = this.HP - damage;
        }

        this.handleDeath();
        return true;
    }

    private void handleDeath() {
        if (this.getHP() <= 0) {
            System.out.println("Unit dead");
            this.getPosition().removeUnit(this);
        }
    }

    public abstract void takeAction(Tile tile);

    // use == only to check null reference and exact same reference
    @Override
    public boolean equals(Object obj) {
        // check reference and class
        if(!(obj instanceof Unit)){
            return false;
        } else if (this == obj){
            return true;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        }

        // compare tile
        if (! this.getPosition().equals(((Unit)obj).getPosition()) ) {
            return false;
        }
        // compare hp
        if ((double)Math.pow((this.getHP() - ((Unit) obj).getHP()), 2) >= 0.001) {
            return false;
        }
        // compare faction
        if (!this.getFaction().equalsIgnoreCase((((Unit) obj).getFaction()))) {
            return false;
        }

        return true;
    }

}
