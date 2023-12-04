package assignment1;

public abstract class MilitaryUnit extends Unit {
    private double attackDamage;
    private int attackRange;
    private int armor;

    public MilitaryUnit(Tile position, double hp, int movingRange, String faction, double attackDamage, int attackRange, int armor) {
        super(position, hp, movingRange, faction);
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.armor = armor;
    }

    @Override
    public void takeAction(Tile tile){
        double attackingDistance = Tile.getDistance(tile, this.getPosition());
        System.out.println(attackingDistance);
        if(attackingDistance <= (double) this.attackRange){

            Unit attacked = tile.selectWeakEnemy(this.getFaction());

            double damage =0;
            if(attacked instanceof Unit) {
                System.out.println("Attacking");
                if (this.getPosition().isImproved()) {
                    damage = this.attackDamage * 1.05;
                } else {
                    damage = this.attackDamage;
                }
                attacked.receiveDamage(damage);
            }
        }
    }

    @Override
    public boolean receiveDamage(double damage) {
        double modifier = (100 / (100 + (double)this.armor));
        double realDamage = damage * modifier;
        return super.receiveDamage(realDamage);
    }
}
