package Tests;

import Grid.Sudoku;
import Solver.ReadTransformer;
import Solver.SetUp;
import Solver.Solver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    Sudoku sudoku;

    @BeforeEach
    void setUp(){
        sudoku= ReadTransformer.getSudokuRowsFromText("204859317900734600003021049035090480090000030076010920310975200009182003000063100");
        SetUp.setUp(sudoku);
    }

    @Test
    void testTrySolvingSquareInRowTrue(){
        assertTrue(Solver.trySolvingSquareInRow(sudoku,0));
        assertEquals(6,sudoku.getRows().get(0).getSquares().get(1).getValue());
    }

    @Test
    void testTrySolvingSquareInRowFalse(){
        assertFalse(Solver.trySolvingSquareInRow(sudoku,7));
    }

    @Test
    void testTrySolvingWithPotentialValues(){
        Sudoku simpleSudoku = ReadTransformer.getSudokuRowsFromText("264059317981734652753621849135297486892546031476318925318975264649182573527063198");
        SetUp.setUp(simpleSudoku);
        Solver.trySolvingWithPotentialValues(simpleSudoku);
        assertTrue(simpleSudoku.checkIfSolved());
    }

    @Test
    void testTrySolvingInRowByProcessOfElimination(){
        assertTrue(Solver.trySolvingInRowByProcessOfElimination(sudoku,0));
        assertEquals(9,sudoku.getRows().get(0).getSquares().get(5).getValue());
    }

    @Test
    void testTrySolvingInColumnByProcessOfElimination(){
        assertTrue(Solver.trySolvingInColumnByProcessOfElimination(sudoku,7));
        assertEquals(9,sudoku.getColumns().get(7).getSquares().get(8).getValue());
    }

    @Test
    void testTrySolvingInBigSquareByProcessOfElimination(){
        assertTrue(Solver.trySolvingInBigSquareByProcessOfElimination(sudoku,7));
        assertEquals(4,sudoku.getBigSquares().get(7).getSquares().get(6).getValue());
    }



}