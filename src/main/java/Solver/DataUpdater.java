package Solver;

import Grid.Sudoku;

/**
 * Class that updates all data after a square has been solved
 */

public class DataUpdater {

    /**
     * Updates the potential values, as well as the solved square count in the whole grid.
     * Used after a square has been solved in a row
     * @param sudoku the sudoku grid
     * @param rowIndex the row of the solved square
     * @param columnIndex the column of the solved square
     */
    public static void updateEverythingFromRow(Sudoku sudoku,int rowIndex, int columnIndex){
        PotentialValueUpdater.updatePotentialValuesFromRow(sudoku, rowIndex);

        PotentialValueUpdater.updatePotentialValuesFromColumn(sudoku,columnIndex);
        PotentialValueUpdater.updatePotentialValuesFromBigSquare(sudoku,3*(rowIndex/3)+columnIndex/3);

        SolvedSquareUpdater.updateSolvedSquares(sudoku, rowIndex, columnIndex);
    }

    /**
     * Updates the potential values, as well as the solved square count in the whole grid.
     * Used after a square has been solved in a column
     * @param sudoku the sudoku grid
     * @param rowIndex the row of the solved square
     * @param columnIndex the column of the solved square
     */
    public static void updateEverythingFromColumn(Sudoku sudoku,int rowIndex, int columnIndex){
        PotentialValueUpdater.updatePotentialValuesFromColumn(sudoku, columnIndex);

        PotentialValueUpdater.updatePotentialValuesFromRow(sudoku, rowIndex);
        PotentialValueUpdater.updatePotentialValuesFromBigSquare(sudoku,3*(rowIndex/3)+columnIndex/3);

        SolvedSquareUpdater.updateSolvedSquares(sudoku, rowIndex, columnIndex);
    }

    /**
     * Updates the potential values, as well as the solved square count in the whole grid.
     * Used after a square has been solved in a big square
     * @param sudoku the sudoku grid
     * @param rowIndex the row of the solved square
     * @param columnIndex the column of the solved square
     */
    public static void updateEverythingFromBigSquare(Sudoku sudoku,int rowIndex, int columnIndex){
        PotentialValueUpdater.updatePotentialValuesFromBigSquare(sudoku, 3*(rowIndex/3)+columnIndex/3);

        PotentialValueUpdater.updatePotentialValuesFromRow(sudoku, rowIndex);
        PotentialValueUpdater.updatePotentialValuesFromColumn(sudoku,columnIndex);

        SolvedSquareUpdater.updateSolvedSquares(sudoku, rowIndex, columnIndex);
    }
}
