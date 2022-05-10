package Tests;

import Grid.Sudoku;
import Solver.ReadTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {

    Sudoku sudoku;

    @BeforeEach
    void setUp(){
        sudoku=ReadTransformer.getSudokuRowsFromText("004050000900734600003021049035090480090000030076010920310970200009182003000060100");
    }

    @Test
    void testTurnRowsIntoColumns(){
        sudoku.turnRowsIntoColumns();
        List<Integer> expected= Arrays.asList(5,3,2,9,0,1,7,8,6);
        List<Integer> actual = new ArrayList<>();

        for (int i=0;i<9;i++){
            actual.add(sudoku.getColumns().get(4).getSquares().get(i).getValue());
        }

        assertEquals(expected,actual);
    }

    @Test
    void testTurnRowsIntoBigSquares(){
        sudoku.turnRowsIntoBigSquares();

        List<Integer> expected=Arrays.asList(0,5,0,7,3,4,0,2,1);
        List<Integer> actual = new ArrayList<>();

        for (int i=0;i<9;i++){
            actual.add(sudoku.getBigSquares().get(1).getSquares().get(i).getValue());
        }

        assertEquals(expected,actual);
    }

    @Test
    void testUpdateColumnsFromRow(){
        sudoku.turnRowsIntoColumns();
        sudoku.getRows().get(0).getSquares().get(6).setValue(1);
        sudoku.getRows().get(0).getSquares().get(7).setValue(2);
        sudoku.updateColumnsFromRow(0);

        List<Integer> expected=Arrays.asList(0,0,4,0,5,0,1,2,0);
        List<Integer> actual = new ArrayList<>();

        for (int i=0;i<9;i++){
            actual.add(sudoku.getColumns().get(i).getSquares().get(0).getValue());
        }

        assertEquals(expected,actual);
    }

    @Test
    void testUpdateBigSquaresFromRow(){
        sudoku.turnRowsIntoBigSquares();
        sudoku.getRows().get(0).getSquares().get(6).setValue(1);
        sudoku.getRows().get(0).getSquares().get(7).setValue(2);
        sudoku.updateBigSquaresFromRow(0);

        List<Integer> expected=Arrays.asList(0,0,4,0,5,0,1,2,0);
        List<Integer> actual = new ArrayList<>();

        for (int i=0;i<9;i++){
            actual.add(sudoku.getBigSquares().get(i/3).getSquares().get(i%3).getValue());
        }

        assertEquals(expected,actual);
    }

    @Test
    void testUpdateRowsFromColumn(){
        sudoku.turnRowsIntoColumns();
        sudoku.getColumns().get(0).getSquares().get(3).setValue(1);
        sudoku.getColumns().get(0).getSquares().get(5).setValue(7);
        sudoku.updateRowsFromColumn(0);

        List<Integer> expected=Arrays.asList(0,9,0,1,0,7,3,0,0);
        List<Integer> actual = new ArrayList<>();

        for (int i=0;i<9;i++){
            actual.add(sudoku.getRows().get(i).getSquares().get(0).getValue());
        }

        assertEquals(expected,actual);
    }

    @Test
    void testUpdateBigSquaresFromColumn(){
        sudoku.turnRowsIntoColumns();
        sudoku.turnRowsIntoBigSquares();
        sudoku.getColumns().get(0).getSquares().get(3).setValue(1);
        sudoku.getColumns().get(0).getSquares().get(5).setValue(7);
        sudoku.updateBigSquaresFromColumn(0);

        List<Integer> expected=Arrays.asList(0,9,0,1,0,7,3,0,0);
        List<Integer> actual = new ArrayList<>();

        for (int i=0;i<9;i++){
            actual.add(sudoku.getBigSquares().get(3*(i/3)).getSquares().get(3*(i%3)).getValue());
        }

        assertEquals(expected,actual);
    }

    @Test
    void testUpdateRowsFromBigSquare(){
        sudoku.turnRowsIntoColumns();
        sudoku.turnRowsIntoBigSquares();
        sudoku.getBigSquares().get(0).getSquares().get(5).setValue(1);
        sudoku.getBigSquares().get(0).getSquares().get(6).setValue(6);
        sudoku.updateRowsFromBigSquare(0);

        List<Integer> expected=Arrays.asList(0,0,4,9,0,1,6,0,3);
        List<Integer> actual = new ArrayList<>();

        for (int i=0;i<9;i++){
            actual.add(sudoku.getRows().get(i/3).getSquares().get(i%3).getValue());
        }

        assertEquals(expected,actual);
    }


    @Test
    void testUpdateColumnsFromBigSquare(){
        sudoku.turnRowsIntoColumns();
        sudoku.turnRowsIntoBigSquares();
        sudoku.getBigSquares().get(0).getSquares().get(5).setValue(1);
        sudoku.getBigSquares().get(0).getSquares().get(6).setValue(6);
        sudoku.updateColumnsFromBigSquare(0);

        List<Integer> expected=Arrays.asList(0,0,4,9,0,1,6,0,3);
        List<Integer> actual = new ArrayList<>();

        for (int i=0;i<9;i++){
            actual.add(sudoku.getColumns().get(i%3).getSquares().get(i/3).getValue());
        }

        assertEquals(expected,actual);
    }

    @Test
    void testCheckValidityTrue(){
        sudoku.turnRowsIntoColumns();
        sudoku.turnRowsIntoBigSquares();
        assertTrue(sudoku.checkValidity());
    }

    @Test
    void testCheckValidityFalse(){
        Sudoku invalidSudoku = ReadTransformer.getSudokuRowsFromText("111111111100734600003021049035090480090000030076010920310970200009182003000060100");
        invalidSudoku.turnRowsIntoBigSquares();
        invalidSudoku.turnRowsIntoColumns();
        assertFalse(invalidSudoku.checkValidity());
    }

    @Test
    void testCheckIfSolvedTrue(){
        Sudoku solvedSudoku = ReadTransformer.getSudokuRowsFromText("264859317981734652753621849135297486892546731476318925318975264649182573527463198");
        assertTrue(solvedSudoku.checkIfSolved());
    }

    @Test
    void testCheckIfSolvedFalse(){
        assertFalse(sudoku.checkIfSolved());
    }

}