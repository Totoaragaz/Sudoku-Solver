package Tests;

import Grid.Sudoku;
import Solver.ReadTransformer;
import Solver.SetUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetUpTest {

    Sudoku sudoku;

    @BeforeEach
    void setUp() {
        sudoku = ReadTransformer.getSudokuRowsFromText("004050000900734600003021049035090480090000030076010920310970200009182003000060100");
        sudoku.turnRowsIntoColumns();
        sudoku.turnRowsIntoBigSquares();
    }

    @Test
    void testUpdatePotentialValues(){
        SetUp.setUpAllPotentialValues(sudoku);
        List<Integer> potentialValues= Arrays.asList(4,5,7,8);

        assertEquals(sudoku.getRows().get(8).getSquares().get(8).getPotentialValues(),potentialValues);
        assertEquals(sudoku.getColumns().get(8).getSquares().get(8).getPotentialValues(),potentialValues);
        assertEquals(sudoku.getBigSquares().get(8).getSquares().get(8).getPotentialValues(),potentialValues);
    }
}