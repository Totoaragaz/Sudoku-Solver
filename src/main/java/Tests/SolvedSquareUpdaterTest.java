package Tests;

import Grid.Sudoku;
import Solver.ReadTransformer;
import Solver.SolvedSquareUpdater;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolvedSquareUpdaterTest {

    Sudoku sudoku;

    @BeforeEach
    void setUp() {
        sudoku = ReadTransformer.getSudokuRowsFromText("004050000900734600003021049035090480090000030076010920310970200009182003000060100");
        sudoku.turnRowsIntoColumns();
        sudoku.turnRowsIntoBigSquares();
    }

    @Test
    void testUpdateRowSolvedSquares(){
        SolvedSquareUpdater.updateRowSolvedSquares(sudoku,0);
        assertEquals(2,sudoku.getRows().get(0).getSolvedSquares());
    }

    @Test
    void testUpdateColumnSolvedSquares(){
        SolvedSquareUpdater.updateColumnSolvedSquares(sudoku,0);
        assertEquals(2,sudoku.getColumns().get(0).getSolvedSquares());
    }

    @Test
    void testUpdateBigSquareSolvedSquares(){
        SolvedSquareUpdater.updateBigSquareSolvedSquares(sudoku,0);
        assertEquals(3,sudoku.getBigSquares().get(0).getSolvedSquares());
    }

}