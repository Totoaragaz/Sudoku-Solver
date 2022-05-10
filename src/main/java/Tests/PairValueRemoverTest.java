package Tests;

import Grid.NineSquareUnit;
import Grid.Sudoku;
import Solver.PairValueRemover;
import Solver.ReadTransformer;
import Solver.SetUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PairValueRemoverTest {

    Sudoku sudoku;
    List<List<Integer>> expected;

    @BeforeEach
    void setUp(){
        sudoku= ReadTransformer.getSudokuRowsFromText("004050000050000000000000004000000000000000005000000000000000000000000000000000000");
        SetUp.setUp(sudoku);
        expected=initializeExpected();
    }

    List<List<Integer>> getNineSquareUnitPotentialValues(NineSquareUnit unit){
        List<List<Integer>> list = new ArrayList<>();
        for (int i=0;i<9;i++){
            list.add(unit.getSquares().get(i).getPotentialValues());
        }
        return list;
    }

    List<List<Integer>> initializeExpected(){
        return Arrays.asList(Arrays.asList(6,7),Arrays.asList(1,2),new ArrayList<>(),Arrays.asList(8,9)
                ,new ArrayList<>(),Arrays.asList(3,8),Arrays.asList(1,2),List.of(8),Arrays.asList(6,7));
    }

    void initializeValuesInNineSquareUnit(NineSquareUnit unit){
        unit.getSquares().get(0).setPotentialValues(new LinkedList<>(Arrays.asList(6,7)));
        unit.getSquares().get(1).setPotentialValues(new LinkedList<>(Arrays.asList(1,2)));
        unit.getSquares().get(3).setPotentialValues(new LinkedList<>(Arrays.asList(1,7,8,9)));
        unit.getSquares().get(5).setPotentialValues(new LinkedList<>(Arrays.asList(2,3,6,8)));
        unit.getSquares().get(6).setPotentialValues(new LinkedList<>(Arrays.asList(1,2)));
        unit.getSquares().get(7).setPotentialValues(new LinkedList<>(Arrays.asList(1,7,8)));
        unit.getSquares().get(8).setPotentialValues(new LinkedList<>(Arrays.asList(6,7)));
    }

    @Test
    void testRemovePairsFromRow(){
        initializeValuesInNineSquareUnit(sudoku.getRows().get(0));
        PairValueRemover.removePairsFromRow(sudoku,0);

        List<List<Integer>> actual=getNineSquareUnitPotentialValues(sudoku.getRows().get(0));

        assertEquals(expected,actual);
    }

    @Test
    void testRemovePairsFromColumn(){
        initializeValuesInNineSquareUnit(sudoku.getColumns().get(8));
        PairValueRemover.removePairsFromColumn(sudoku,8);

        List<List<Integer>> actual=getNineSquareUnitPotentialValues(sudoku.getColumns().get(8));

        assertEquals(expected,actual);
    }

    @Test
    void testRemovePairsFromBigSquare(){
        initializeValuesInNineSquareUnit(sudoku.getBigSquares().get(0));
        PairValueRemover.removePairsFromBigSquare(sudoku,0);

        List<List<Integer>> actual=getNineSquareUnitPotentialValues(sudoku.getBigSquares().get(0));

        assertEquals(expected,actual);
    }

}