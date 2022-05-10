package Grid;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A unit of nine squares, like rows, columns and those bigger squares with 9 smaller squares in them.
 */

public class NineSquareUnit {
    private List<Square> squares;
    private int solvedSquares;

    public List<Square> getSquares() {
        return squares;
    }

    public void setSquares(List<Square> unit) {
        this.squares = unit;
    }

    public void setSolvedSquares(int solvedSquares) {
        this.solvedSquares = solvedSquares;
    }

    public NineSquareUnit(List<Square> unit) {
        this.squares = unit;
        this.solvedSquares = 0;
    }

    public NineSquareUnit(NineSquareUnit newUnit) {
        this.squares = newUnit.squares;
        this.solvedSquares = newUnit.solvedSquares;
    }

    public NineSquareUnit() {
        this.solvedSquares = 0;
    }

    public int getSolvedSquares() {
        return solvedSquares;
    }

    /**
     * Checks the validity of the NineSquareUnit
     * @return true if valid, false otherwise
     */

    public boolean checkValidity() {
        Set<Integer> valueSet = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (squares.get(i).getValue() != 0) {
                if (valueSet.contains(squares.get(i).getValue()))
                    return false;
                else valueSet.add(squares.get(i).getValue());
            }
        }
        return true;
    }

    public void updateSolvedSquares(){
        solvedSquares=0;
        for (int i=0;i<9;i++){
            if (squares.get(i).getValue()!=0) solvedSquares++;
        }
    }

    /**
     * Solves the only square that can have that value, found by process of elimination, assuming it is the only one with that potential value
     * (CONDITION SHOULD BE TESTED FIRST!)
     * @param value the value unique to that square
     * @return the index of the solved square
     */
    public int solveSquareWithSinglePotentialValue(int value){
        for (int i=0;i<9;i++){
            if (squares.get(i).getPotentialValues().contains(value)){
                squares.get(i).solveSquareWithValue(value);
                return i;
            }
        }
        return -1;
    }

    /**
     * Solves a square in the NineSquareUnit if it is the only one in the unit that can have a value
     * @return the index of the solved square, or -1 if nothing can be solved
     */
    public int solveByProcessOfElimination(){
        for (int i=1;i<=9;i++){
            int occurrences = 0;
            for (int j=0;j<9;j++){
                if (squares.get(j).getPotentialValues().contains(i)) occurrences++;
                if (occurrences>1) break;
            }
            if (occurrences==1){
                return solveSquareWithSinglePotentialValue(i);
            }
        }
        return -1;
    }

    /**
     * Removes a pair of potential values from all squares in the unit
     * @param values the values that need to be removed
     */
    public void removePairFromSquares(List<Integer> values){
        for (int i=0;i<9;i++){
            if (!squares.get(i).getPotentialValues().equals(values)) squares.get(i).removePotentialValues(values);
        }
    }

    /**
     * Checks if the unit is solved
     * @return true if solved, false otherwise
     */
    public boolean checkIfSolved(){
        return this.solvedSquares == 9;
    }

    /**
     * Checks if the unit contains a square with a specific value
     * @param value the searched for value
     * @return true if found, false otherwise
     */
    public boolean containsSquareWithValue(int value){
        for (int i=0;i<9;i++){
            if (this.squares.get(i).getValue()==value) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NineSquareUnit that = (NineSquareUnit) o;
        return solvedSquares == that.solvedSquares && Objects.equals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares, solvedSquares);
    }

    @Override
    public String toString() {
        return "NineSquareUnit{" +
                "unit=" + squares +
                '}';
    }
}
