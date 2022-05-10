package Tests;

import Grid.NineSquareUnit;
import Grid.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NineSquareUnitTest {

    NineSquareUnit unit;

    @BeforeEach
    void setUp(){
        List<Square> list = new ArrayList<>();
        for (int i=0;i<8;i++){
            Square square=new Square(i+1);
            list.add(square);
        }
        list.add(new Square(0));
        unit=new NineSquareUnit(list);
    }

    @Test
    void testInitialization(){
        List<Integer> expected= Arrays.asList(1,2,3,4,5,6,7,8,0);

        List<Integer> actual= new ArrayList<>();

        for (int i=0;i<9;i++){
            actual.add(unit.getSquares().get(i).getValue());
        }
        assertEquals(expected,actual);
    }

    @Test
    void testUpdateSolvedSquares(){
        unit.updateSolvedSquares();
        assertEquals(8,unit.getSolvedSquares());
    }

    @Test
    void testCheckIfSolvedFalse(){
        unit.updateSolvedSquares();
        assertFalse(unit.checkIfSolved());
    }

    @Test
    void testCheckIfSolvedTrue(){
        unit.updateSolvedSquares();
        unit.getSquares().get(8).setValue(9);
        unit.updateSolvedSquares();
        assertTrue(unit.checkIfSolved());
    }

    @Test
    void testCheckValidityFalse(){
        unit.getSquares().get(8).setValue(8);
        assertFalse(unit.checkValidity());
    }

    @Test
    void testCheckValidityTrue(){
        assertTrue(unit.checkValidity());
    }

    @Test
    void testContainsUnitWithValueTrue(){
        assertTrue(unit.containsSquareWithValue(8));
    }

    @Test
    void testContainsUnitWithValueFalse(){
        assertFalse(unit.containsSquareWithValue(9));
    }

    @Test
    void testSolveSquareWithSinglePotentialValue(){
        unit.solveSquareWithSinglePotentialValue(7);
        assertEquals(7,unit.getSquares().get(8).getValue());
    }

    @Test
    void testSolveByProcessOfEliminationGood(){
        unit.getSquares().get(6).setValue(0);
        unit.getSquares().get(6).setPotentialValues(Arrays.asList(7,8,9));
        unit.getSquares().get(7).setValue(0);
        unit.getSquares().get(7).setPotentialValues(Arrays.asList(8,9));
        unit.getSquares().get(8).setPotentialValues(Arrays.asList(8,9));
        assertEquals(6,unit.solveByProcessOfElimination());
        assertEquals(7,unit.getSquares().get(6).getValue());
    }

    @Test
    void testSolveByProcessOfEliminationBad(){
        unit.getSquares().get(7).setValue(0);
        unit.getSquares().get(7).setPotentialValues(Arrays.asList(8,9));
        unit.getSquares().get(8).setPotentialValues(Arrays.asList(8,9));
        assertEquals(-1,unit.solveByProcessOfElimination());
    }

    @Test
    void testRemovePairFromSquares(){
        unit.getSquares().get(6).setValue(0);
        unit.getSquares().get(6).setPotentialValues(Arrays.asList(8,9));
        unit.getSquares().get(7).setValue(0);
        unit.getSquares().get(7).setPotentialValues(new LinkedList<>(Arrays.asList(7,8,9)));
        unit.getSquares().get(8).setPotentialValues(new LinkedList<>(Arrays.asList(8,9)));
        unit.removePairFromSquares(Arrays.asList(8,9));
        assertEquals(List.of(7),unit.getSquares().get(7).getPotentialValues());
        assertEquals(Arrays.asList(8,9),unit.getSquares().get(8).getPotentialValues());
    }
}