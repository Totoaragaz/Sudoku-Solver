package Solver;

import Grid.NineSquareUnit;
import Grid.Sudoku;

/**
 * The actual sudoku solving class
 */

public class Solver {

    /**
     * Attempts solving by process of elimination in a NineSquareUnit
     * @param unit the unit in which the solving is attempted
     * @return the index of the solved square if successful, -1 otherwise
     */
    private static int trySolvingInNineSquareUnitByProcessOfElimination (NineSquareUnit unit){
        return unit.solveByProcessOfElimination();
    }

    /**
     * Attempts solving by process of elimination in a row
     * If successful, updates the table
     * @param sudoku the sudoku grid
     * @param rowIndex the index of the row
     * @return true if successful, false otherwise
     */
    public static boolean trySolvingInRowByProcessOfElimination (Sudoku sudoku,int rowIndex){
        int columnIndex=trySolvingInNineSquareUnitByProcessOfElimination(sudoku.getRows().get(rowIndex));
        if (columnIndex!=-1){
            DataUpdater.updateEverythingFromRow(sudoku,rowIndex,columnIndex);
            return true;
        }
        return false;
    }

    /**
     * Attempts solving by process of elimination in a column
     * If successful, updates the table
     * @param sudoku the sudoku grid
     * @param columnIndex the index of the column
     * @return true if successful, false otherwise
     */
    public static boolean trySolvingInColumnByProcessOfElimination (Sudoku sudoku,int columnIndex){
        int rowIndex=trySolvingInNineSquareUnitByProcessOfElimination(sudoku.getColumns().get(columnIndex));
        if (rowIndex!=-1){
            DataUpdater.updateEverythingFromColumn(sudoku,rowIndex,columnIndex);
            return true;
        }
        return false;
    }

    /**
     * Attempts solving by process of elimination in a big square
     * If successful, updates the table
     * @param sudoku the sudoku grid
     * @param bigSquareIndex the index of the big square
     * @return true if successful, false otherwise
     */
    public static boolean trySolvingInBigSquareByProcessOfElimination (Sudoku sudoku,int bigSquareIndex){
        int smallSquareIndex=trySolvingInNineSquareUnitByProcessOfElimination(sudoku.getBigSquares().get(bigSquareIndex));
        if (smallSquareIndex!=-1){
            DataUpdater.updateEverythingFromBigSquare(sudoku,3*(bigSquareIndex/3)+smallSquareIndex/3,3*(bigSquareIndex%3)+smallSquareIndex%3);
            return true;
        }
        return false;
    }

    /**
     * Solves in the sudoku by process of elimination, until nothing more can be solved by this method
     * @param sudoku the sudoku grid
     * @return true if anything was solved, false otherwise
     */
    public static boolean trySolvingByProcessOfElimination(Sudoku sudoku){
        boolean anyUpdateHappened=false;
        boolean loopUpdateHappened=true;
        while (loopUpdateHappened) {
            loopUpdateHappened = false;
            for (int i = 0; i < 9; i++) {
                if (!sudoku.getRows().get(i).checkIfSolved()) {
                    if (trySolvingInRowByProcessOfElimination(sudoku, i)) {
                        i--;
                        loopUpdateHappened = true;
                        anyUpdateHappened=true;
                        continue;
                    }
                }
                if (!sudoku.getColumns().get(i).checkIfSolved()) {
                    if (trySolvingInColumnByProcessOfElimination(sudoku, i)) {
                        i--;
                        loopUpdateHappened = true;
                        anyUpdateHappened=true;
                        continue;
                    }
                }
                if (!sudoku.getBigSquares().get(i).checkIfSolved()) {
                    if (trySolvingInBigSquareByProcessOfElimination(sudoku, i)) {
                        i--;
                        loopUpdateHappened = true;
                        anyUpdateHappened=true;
                    }
                }
            }
        }
        return anyUpdateHappened;
    }

    /**
     * Attempts solving in a row by looking for single potential values.
     * @param sudoku the sudoku grid
     * @param rowIndex the index of the row
     * @return true if successful, false otherwise
     */
    public static boolean trySolvingSquareInRow(Sudoku sudoku,int rowIndex){
        for (int i=0;i<9;i++){
            if (sudoku.getRows().get(rowIndex).getSquares().get(i).getPotentialValues().size()==1){
                sudoku.getRows().get(rowIndex).getSquares().get(i).solveSquare();
                DataUpdater.updateEverythingFromRow(sudoku, rowIndex, i);
                return true;
            }
        }
        return false;
    }

    /**
     * Solves in the sudoku by looking for single potential values, until nothing more can be solved by this method
     * @param sudoku the sudoku grid
     * @return true if anything was solved, false otherwise
     */
    public static boolean trySolvingWithPotentialValues(Sudoku sudoku){
        boolean anyUpdateHappened=false;
        boolean loopUpdateHappened=true;
        while (loopUpdateHappened) {
            loopUpdateHappened = false;
            for (int i = 0; i < 9; i++) {
                if (!sudoku.getRows().get(i).checkIfSolved()) {
                    if (trySolvingSquareInRow(sudoku, i)) {
                        i--;
                        loopUpdateHappened = true;
                        anyUpdateHappened=true;
                    }
                }
            }
        }
        return anyUpdateHappened;
    }

    /**
     * Attempts solving the sudoku grid
     * @param sudoku the sudoku that needs to be solved
     */
    public static void solve(Sudoku sudoku){
        SetUp.setUp(sudoku);
        boolean somethingsHappening = true;
        while (somethingsHappening){
            PairValueRemover.removePairsFromSudoku(sudoku);
            somethingsHappening= trySolvingWithPotentialValues(sudoku)|| trySolvingByProcessOfElimination(sudoku);
        }
        if (sudoku.checkIfSolved()) System.out.println("Solved!");
        else {
            System.out.println("Sorry this is beyond me at this point in time. :(");
            System.out.println("This is how far I got:");
        }
    }
}
