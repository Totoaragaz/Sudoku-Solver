package Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Cell of a Sudoku Grid
 */

public class Square {
    private int value;
    private List<Integer> potentialValues;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Integer> getPotentialValues() {
        return potentialValues;
    }

    public void setPotentialValues(List<Integer> potentialValues) {
        this.potentialValues = potentialValues;
    }

    public Square(int value) {
        this.value = value;
        if (this.value==0) initializePotentialValues();
        else this.potentialValues=new ArrayList<>();
    }
    /**
     * initializes the potential values of the square with
     * [1,2,3,4,5,6,7,8,9]
     */
    public void initializePotentialValues(){
        this.potentialValues=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<9;i++){
            list.add(i+1);
        }
        this.potentialValues=list;
    }

    /**
     * Solves the square, assuming there is only one value in potential values
     * (CONDITION SHOULD BE TESTED FIRST!)
     */
    public void solveSquare(){
        this.value=this.potentialValues.get(0);
        this.potentialValues=new ArrayList<>();
    }

    /**
     * Solves the square
     * @param value value of the square
     */
    public void solveSquareWithValue(int value){
        this.value=value;
        this.potentialValues=new ArrayList<>();
    }

    /**
     * Removes some values from the list of potential values
     * @param values the values that need to be removed
     */
    public void removePotentialValues(List<Integer> values){
        this.potentialValues.removeAll(values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        if (value == square.getValue() && value!=0) return true;
        return Objects.equals(potentialValues, square.potentialValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, potentialValues);
    }

    @Override
    public String toString() {
        return "Square{" +
                "value=" + value +
                ", potentialValues=" + potentialValues +
                '}';
    }
}
