package Tests;

import Grid.NineSquareUnit;
import Grid.Sudoku;
import Solver.DataUpdater;
import Solver.ReadTransformer;
import Solver.SetUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataUpdaterTest {

    Sudoku sudoku;

    @BeforeEach
    void setUp() {
        sudoku = ReadTransformer.getSudokuRowsFromText("004050000900734600003021049035090480090000030076010920310970200009182003000060100");
        sudoku.turnRowsIntoColumns();
        sudoku.turnRowsIntoBigSquares();
    }

    List<List<Integer>> getNineSquareUnitPotentialValues(NineSquareUnit unit){
        List<List<Integer>> list = new ArrayList<>();
        for (int i=0;i<9;i++){
            list.add(unit.getSquares().get(i).getPotentialValues());
        }
        return list;
    }

    List<List<Integer>> getColumnPotentialValues(int columnIndex){
        return getNineSquareUnitPotentialValues(sudoku.getColumns().get(columnIndex));
    }

    List<List<Integer>> getRowPotentialValues(int rowIndex){
        return getNineSquareUnitPotentialValues(sudoku.getRows().get(rowIndex));
    }

    List<List<Integer>> getBigSquarePotentialValues(int bigSquareIndex){
        return getNineSquareUnitPotentialValues(sudoku.getBigSquares().get(bigSquareIndex));
    }

    void testUpdateSolvedSquares(){
        List<Integer> expected = Arrays.asList(3,9,3);
        List<Integer> actual = Arrays.asList(sudoku.getRows().get(4).getSolvedSquares(),sudoku.getColumns().get(4).getSolvedSquares(),sudoku.getBigSquares().get(4).getSolvedSquares());
        assertEquals(expected,actual);
    }

    void testRowPotentialValues(){
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1,2,8),new ArrayList<>(),Arrays.asList(1,2,8),Arrays.asList(2,5,6,8)
                ,new ArrayList<>(),Arrays.asList(5,6,7,8),Arrays.asList(5,7),new ArrayList<>(),Arrays.asList(1,5,6,7));
        List<List<Integer>> actual = getRowPotentialValues(4);

        assertEquals(expected,actual);
    }

    void testColumnPotentialValues(){
        List<List<Integer>> expected = Arrays.asList(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),
                new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        List<List<Integer>> actual = getColumnPotentialValues(4);

        assertEquals(expected,actual);
    }

    void testBigSquarePotentialValues(){
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(2,6),new ArrayList<>(),Arrays.asList(6,7),Arrays.asList(2,5,6,8),
                new ArrayList<>(),Arrays.asList(5,6,7,8),Arrays.asList(3,5,8),new ArrayList<>(),Arrays.asList(3,5,8));
        List<List<Integer>> actual = getBigSquarePotentialValues(4);

        assertEquals(expected,actual);
    }

    @Test
    void testUpdateEverythingFromRow(){
        SetUp.setUpAllSolvedSquares(sudoku);
        SetUp.setUpAllPotentialValues(sudoku);
        sudoku.getRows().get(4).getSquares().get(4).solveSquare();
        DataUpdater.updateEverythingFromRow(sudoku,4,4);

        testUpdateSolvedSquares();

        testRowPotentialValues();

        testColumnPotentialValues();

        testBigSquarePotentialValues();
    }

    @Test
    void testUpdateEverythingFromColumn(){
        SetUp.setUpAllSolvedSquares(sudoku);
        SetUp.setUpAllPotentialValues(sudoku);
        sudoku.getColumns().get(4).getSquares().get(4).solveSquare();
        DataUpdater.updateEverythingFromColumn(sudoku,4,4);

        testUpdateSolvedSquares();

        testRowPotentialValues();

        testColumnPotentialValues();

        testBigSquarePotentialValues();
    }

    @Test
    void testUpdateEverythingFromBigSquare(){
        SetUp.setUpAllSolvedSquares(sudoku);
        SetUp.setUpAllPotentialValues(sudoku);
        sudoku.getBigSquares().get(4).getSquares().get(4).solveSquare();
        DataUpdater.updateEverythingFromBigSquare(sudoku,4,4);

        testUpdateSolvedSquares();

        testRowPotentialValues();

        testColumnPotentialValues();

        testBigSquarePotentialValues();
    }
}