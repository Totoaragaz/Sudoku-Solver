package Solver;

import Grid.Sudoku;

/**
 * Class that sets up a sudoku, before it can be solved
 */
public class SetUp {

    /**
     * Sets up the solved squares of all NineSquareUnits in a sudoku
     * @param sudoku the sudoku grid
     */
    public static void setUpAllSolvedSquares(Sudoku sudoku){
        for (int i=0;i<9;i++){
            SolvedSquareUpdater.updateRowSolvedSquares(sudoku,i);
            SolvedSquareUpdater.updateColumnSolvedSquares(sudoku,i);
            SolvedSquareUpdater.updateBigSquareSolvedSquares(sudoku,i);
        }
    }

    /**
     * Sets up the potential values of all Squares in a sudoku
     * @param sudoku the sudoku grid
     */
    public static void setUpAllPotentialValues(Sudoku sudoku){
        for (int i=0;i<9;i++){
            PotentialValueUpdater.updatePotentialValuesFromRow(sudoku,i);
            PotentialValueUpdater.updatePotentialValuesFromColumn(sudoku,i);
            PotentialValueUpdater.updatePotentialValuesFromBigSquare(sudoku,i);
        }
    }

    /**
     * Sets up a sudoku to be solved
     * @param sudoku the sudoku grid
     */
    public static void setUp(Sudoku sudoku){
        sudoku.turnRowsIntoColumns();
        sudoku.turnRowsIntoBigSquares();
        if (!sudoku.checkValidity()) throw new RuntimeException("Sudoku is invalid.");
        setUpAllPotentialValues(sudoku);
        setUpAllSolvedSquares(sudoku);
    }
}
