package UI;

import Grid.NineSquareUnit;
import Grid.Sudoku;

/**
 * Class used for printing in the Console
 */

public class ConsolePrinter {

    /**
     * Prints a thick line
     */
    public static void printThickLine(){
        System.out.println();
        for (int i=0;i<41;i++)
            System.out.print("=");
    }

    /**
     * Prints a thin line
     */
    public static void printRegularLine(){
        System.out.println();
        for (int i=0;i<41;i++)
            System.out.print("-");
    }

    /**
     * Prints a NineSquareUnit.
     * Used for testing
     * @param unit the NineSquareUnit
     */
    public static void printNineSquareUnit(NineSquareUnit unit){
        System.out.println();
        for (int i=0;i<9;i++) {
            System.out.print(unit.getSquares().get(i).getValue() + " ");
        }
    }

    /**
     * Prints a sudoku row, as well as the walls separating the cells
     * @param line the row that needs to be printed
     */
    public static void printSudokuLine(NineSquareUnit line){
        System.out.println();
        for (int i=0;i<9;i++) {
            if (i % 3 == 0) {
                if (line.getSquares().get(i).getValue() == 0)
                    System.out.print("||   ");
                else System.out.print("|| " + line.getSquares().get(i).getValue() + " ");
            }
            else {
                if (line.getSquares().get(i).getValue() == 0)
                    System.out.print("|   ");
                else System.out.print("| " + line.getSquares().get(i).getValue() + " ");
            }
        }
        System.out.print("||");
    }

    /**
     * Prints the potential values of each square in a sudoku row, as well as the walls separating the cells
     * @param line the row that needs to be printed
     */
    public static void printPotentialValueLine(NineSquareUnit line){
        System.out.println();
        for (int i=0;i<9;i++) {
            if (i % 3 == 0) {
                System.out.print("|| " + line.getSquares().get(i).getPotentialValues() + " ");
            }
            else {
                System.out.print("| " + line.getSquares().get(i).getPotentialValues() + " ");
            }
        }
        System.out.print("||");
    }

    /**
     * Prints a sudoku grid
     * @param sudoku the sudoku that needs to be printed
     */

    public static void printTable(Sudoku sudoku){
        for (int i=0;i<9;i++){
            if (i%3==0) printThickLine();
            else printRegularLine();
            printSudokuLine(sudoku.getRows().get(i));
        }
        printThickLine();
    }

    /**
     * Prints a sudoku grid, except with the potential values of each square, instead of the actual values
     * Used for testing
     * @param sudoku the sudoku that needs to be printed
     */
    public static void printPotentialValueTable(Sudoku sudoku){
        for (int i=0;i<9;i++){
            if (i%3==0) printThickLine();
            else printRegularLine();
            printPotentialValueLine(sudoku.getRows().get(i));
        }
        printThickLine();
    }
}
