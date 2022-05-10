package Solver;

import Grid.NineSquareUnit;
import Grid.Sudoku;

import java.util.List;

/**
 * Class that updates the potential values of the squares in a sudoku grid
 */

public class PotentialValueUpdater {

    /**
     * Updates the potential values of all squares in a NineSquareUnit
     * @param unit the unit that needs to be updated
     */
    private static void updatePotentialValuesInNineSquareUnit(NineSquareUnit unit){
        for (int i=0;i<9;i++){
            for (int j=1;j<=9;j++){
                if (unit.getSquares().get(i).getValue()==0){
                    if (unit.containsSquareWithValue(j)){
                        List<Integer> newPotentialValueList=unit.getSquares().get(i).getPotentialValues();
                        newPotentialValueList.remove(Integer.valueOf(j));
                        unit.getSquares().get(i).setPotentialValues(newPotentialValueList);
                    }
                }
            }
        }
    }

    /**
     * Updates the potential values of all squares in a row
     * @param sudoku the sudoku grid
     * @param rowIndex the index of the row
     */
    private static void updatePotentialValuesInRow(Sudoku sudoku, int rowIndex){
        updatePotentialValuesInNineSquareUnit(sudoku.getRows().get(rowIndex));
    }

    /**
     * Updates the potential values of all squares in a column
     * @param sudoku the sudoku grid
     * @param columnIndex the index of the column
     */
    private static void updatePotentialValuesInColumn(Sudoku sudoku, int columnIndex){
        updatePotentialValuesInNineSquareUnit(sudoku.getColumns().get(columnIndex));
    }

    /**
     * Updates the potential values of all squares in a big square
     * @param sudoku the sudoku grid
     * @param bigSquareIndex the index of the big square
     */
    private static void updatePotentialValuesInBigSquare(Sudoku sudoku, int bigSquareIndex){
        updatePotentialValuesInNineSquareUnit(sudoku.getBigSquares().get(bigSquareIndex));
    }

    /**
     * Updates the potential values of all squares in the sudoku, after a square was solved in a row
     * @param sudoku the sudoku grid
     * @param rowIndex the index of the row
     */
    public static void updatePotentialValuesFromRow(Sudoku sudoku, int rowIndex){
        updatePotentialValuesInRow(sudoku,rowIndex);
        sudoku.updateColumnsFromRow(rowIndex);
        sudoku.updateBigSquaresFromRow(rowIndex);
    }

    /**
     * Updates the potential values of all squares in the sudoku, after a square was solved in a column
     * @param sudoku the sudoku grid
     * @param columnIndex the index of the column
     */
    public static void updatePotentialValuesFromColumn(Sudoku sudoku, int columnIndex){
        updatePotentialValuesInColumn(sudoku,columnIndex);
        sudoku.updateRowsFromColumn(columnIndex);
        sudoku.updateBigSquaresFromColumn(columnIndex);
    }

    /**
     * Updates the potential values of all squares in the sudoku, after a square was solved in a big square
     * @param sudoku the sudoku grid
     * @param bigSquareIndex the index of the big square
     */
    public static void updatePotentialValuesFromBigSquare(Sudoku sudoku, int bigSquareIndex){
        updatePotentialValuesInBigSquare(sudoku,bigSquareIndex);
        sudoku.updateRowsFromBigSquare(bigSquareIndex);
        sudoku.updateColumnsFromBigSquare(bigSquareIndex);
    }
}
