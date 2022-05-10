package Tests;

import Grid.NineSquareUnit;
import Grid.Sudoku;
import Solver.PotentialValueUpdater;
import Solver.ReadTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PotentialValueUpdaterTest {

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

    List<List<Integer>> getRowPotentialValues(int rowIndex){
        return getNineSquareUnitPotentialValues(sudoku.getRows().get(rowIndex));
    }

    List<List<Integer>> getColumnPotentialValues(int columnIndex){
        return getNineSquareUnitPotentialValues(sudoku.getColumns().get(columnIndex));
    }

    List<List<Integer>> getBigSquarePotentialValues(int bigSquareIndex){
        return getNineSquareUnitPotentialValues(sudoku.getBigSquares().get(bigSquareIndex));
    }

    @Test
    void testUpdatePotentialValuesFromRow(){
        PotentialValueUpdater.updatePotentialValuesFromRow(sudoku,1);
        List<Integer> potentialValues= Arrays.asList(1,2,5,8);
        List<List<Integer>> expected = Arrays.asList(new ArrayList<>(),potentialValues,potentialValues,new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), potentialValues, potentialValues);

        List<List<Integer>> actual = getRowPotentialValues(1);

        assertEquals(expected,actual);
    }

    @Test
    void testUpdatePotentialValuesFromColumn(){
        PotentialValueUpdater.updatePotentialValuesFromColumn(sudoku,4);
        List<Integer> potentialValues= List.of(4);
        List<List<Integer>> expected = Arrays.asList(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),
                potentialValues,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());

        List<List<Integer>> actual = getColumnPotentialValues(4);

        assertEquals(expected,actual);
    }

    @Test
    void testUpdatePotentialValuesFromBigSquare(){
        PotentialValueUpdater.updatePotentialValuesFromBigSquare(sudoku,1);
        List<Integer> potentialValues= Arrays.asList(6,8,9);
        List<List<Integer>> expected = Arrays.asList(potentialValues,new ArrayList<>(), potentialValues,new ArrayList<>(),
                new ArrayList<>(),new ArrayList<>(),potentialValues,new ArrayList<>(),new ArrayList<>());

        List<List<Integer>> actual = getBigSquarePotentialValues(1);

        assertEquals(expected,actual);
    }
}