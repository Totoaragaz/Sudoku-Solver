package Solver;

import Grid.NineSquareUnit;
import Grid.Sudoku;

/**
 * Class that updates the amount of solved squares in the NineSquareUnits of a sudoku
 */

public class SolvedSquareUpdater {

    /**
     * Updates the amount of solved squares in a NineSquareUnit
     * @param unit the unit that needs to be updated
     */
    private static void updateNineSquareUnitSolvedSquares(NineSquareUnit unit){
        unit.updateSolvedSquares();
    }

    /**
     * Updates the amount of solved squares in a row
     * @param sudoku the sudoku grid
     * @param rowIndex the index of the row that needs to be updated
     */
    public static void updateRowSolvedSquares(Sudoku sudoku, int rowIndex){
        updateNineSquareUnitSolvedSquares(sudoku.getRows().get(rowIndex));
    }

    /**
     * Updates the amount of solved squares in a column
     * @param sudoku the sudoku grid
     * @param columnIndex the index of the column that needs to be updated
     */
    public static void updateColumnSolvedSquares(Sudoku sudoku,int columnIndex){
        updateNineSquareUnitSolvedSquares(sudoku.getColumns().get(columnIndex));
    }

    /**
     * Updates the amount of solved squares in a big square
     * @param sudoku the sudoku grid
     * @param bigSquareIndex the index of the big square that needs to be updated
     */
    public static void updateBigSquareSolvedSquares(Sudoku sudoku,int bigSquareIndex){
        updateNineSquareUnitSolvedSquares(sudoku.getBigSquares().get(bigSquareIndex));
    }

    /**
     * Updates the amount of solved squares in the sudoku grid, after a square has been solved
     * @param sudoku the sudoku grid
     * @param rowIndex the row of the solved square
     * @param columnIndex the column of the solved square
     */
    public static void updateSolvedSquares(Sudoku sudoku, int rowIndex,int columnIndex){
        updateRowSolvedSquares(sudoku, rowIndex);
        updateColumnSolvedSquares(sudoku, columnIndex);
        updateBigSquareSolvedSquares(sudoku, 3*(rowIndex/3)+columnIndex/3);
    }
}
