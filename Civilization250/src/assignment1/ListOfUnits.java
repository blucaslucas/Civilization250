package assignment1;

public class ListOfUnits {
    private Unit[] list;
    private int size;

    public ListOfUnits() {
        this.list = new Unit[2000];
        this.size = 0;
    }

    /*
        NOTE:
        there are several instances where a potentially null object attempts to invoke methods, which would result in NullPointerException.
        to combat this, "instanceof Unit" is my go-to operator used to check if the invoking object is null
     */

    public int getSize() {
        // count number of non-null elements in list
        int counter = 0;
        for (Unit u : this.list) {
            if (u instanceof Unit){
                counter++;
            }
        }
        this.size = counter;
        return counter;
    }


    public Unit[] getList() {
        //instantiate array to return
        Unit[] unitList = new Unit[this.getSize()];
        int j = 0;
        // copy non-null elements to target array
        for (int i = 0; i < this.list.length; i++) {
            if (this.list[i] instanceof Unit) {
                unitList[j] = this.list[i];
                j++;
            }
        }
        return unitList;
    }

    public Unit getUnit(int reference) throws IndexOutOfBoundsException {
        // check acceptable index (index starting at 0)
        if (0 > reference || reference >= this.getSize()) {
            System.out.println("Index not in range");
            throw new IndexOutOfBoundsException();
        }
        // return Unit at reference
        return this.getList()[reference];
    }

    public void addUnit(Unit unit) {
        // check if array full
        if (this.list.length == this.getSize()) {
            this.resize();
        }
        // add unit
        this.list[this.size] = unit;
        this.size++;
    }

    private void resize() {
        // grow and copy elements
        Unit[] biggerList = new Unit[(int)(this.list.length * 1.6)];
        for (int i = 0; i < this.getSize(); i++) {
            biggerList[i] = this.list[i];
        }
        this.list = biggerList;
    }

    public int indexOf(Unit unit) {
        // while keeping for loop within this.size, no element list[i] will be null, therefore can invoke .equals()
        for (int i = 0; i < this.size; i++) {
            if (this.list[i].equals(unit)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeUnit(Unit unit) {
        int index = indexOf(unit);
        if (index != -1) {
            // Shift elements
            for (int i = index; i < size - 1; i++) {
                list[i] = list[i + 1];
            }
            list[size - 1] = null;
            size--;
            return true;
        }
        return false;
    }

    // contain all MilitaryUnits that are part of this list. Note that not all units in the list are military units\
    public MilitaryUnit[] getArmy() {
        //count military units
        int counter = 0;
        for (Unit u : this.getList()) {
            if (u instanceof MilitaryUnit) {
                counter++;
            }
        }
        //create array and add military units
        int j = 0;
        MilitaryUnit[] militaryUnits = new MilitaryUnit[counter];
        for (Unit u : this.getList()) {
            if (u instanceof MilitaryUnit) {
                militaryUnits[j] = (MilitaryUnit) u;
                j++;
            }
        }
        return militaryUnits;
    }
}
