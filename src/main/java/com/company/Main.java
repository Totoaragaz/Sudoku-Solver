package com.company;

import Grid.Sudoku;
import Solver.ReadTransformer;
import Solver.Solver;
import UI.ConsolePrinter;

public class Main {

    /**
     * This is a programm that solves Sudoku puzzles.
     * In order to use it, you must transform a sudoku grid into text format, then call the
     * ReadTransformer.getSudokuRowsFromText() method.
     * Sudoku grids can be transformed the following way:
     * <p>
     * Reading from left to right, row by row input each cell's value.
     * If the cell is not already solved, input 0 instead.
     * <p>
     * Example:
     *
     *=========================================
     * ||   | 2 | 6 ||   | 3 |   ||   |   | 8 ||
     * -----------------------------------------
     * || 9 |   |   || 6 |   |   || 1 |   |   ||
     * -----------------------------------------
     * ||   |   |   ||   | 1 | 9 ||   | 4 |   ||
     * =========================================
     * ||   |   | 7 || 3 |   | 2 ||   |   |   ||
     * -----------------------------------------
     * ||   |   | 4 ||   | 7 |   || 8 |   |   ||
     * -----------------------------------------
     * ||   |   |   || 8 |   | 6 || 7 |   |   ||
     * =========================================
     * ||   | 5 |   || 7 | 2 |   ||   |   |   ||
     * -----------------------------------------
     * ||   |   | 9 ||   |   | 5 ||   |   | 4 ||
     * -----------------------------------------
     * || 4 |   |   ||   | 6 |   || 2 | 1 |   ||
     * =========================================
     *
     * For the following grid, the sudoku code would be
     * 026030008900600100000019040007302000004070800000806700050720000009005004400060210
     *<p>
     * Here are a few preset sudoku grids you can try out:
     * <ul>
     * <li>Easy: 004050000900734600003021049035090480090000030076010920310970200009182003000060100
     * <li>Medium1: 150209004040006000000040063070000806600000005208000010460080000000600070800501049
     * <li>Medium2: 026030008900600100000019040007302000004070800000806700050720000009005004400060210
     * <li>Medium-Hard: 000500006000870302270300081000034900793050614008790000920003057506087000300005000
     * <li>Hard: 070020900040806000012000300000000870060972050025000000001000290000504030007060010
     * <li>Very Hard: 097000540400000006002000800300060004000293000000804000050309070600070009080000030
     *</ul>
     * Please note that this is still WIP and cannot finish difficult Sudoku puzzles.
     */
    public static void main(String[] args) {
        //ConsolePrinter.printTable(ReadTransformer.getSudokuRowsFromText(Reader.readLine()));
        Sudoku sudoku=ReadTransformer.getSudokuRowsFromText("004050000900734600003021049035090480090000030076010920310970200009182003000060100");

        ConsolePrinter.printTable(sudoku);
        System.out.println();
        System.out.println("Solving...");
        Solver.solve(sudoku);
        ConsolePrinter.printTable(sudoku);
    }
}
