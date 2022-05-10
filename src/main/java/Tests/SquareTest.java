package Tests;

import Grid.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    Square square;

    @BeforeEach
    void setUp(){
        square=new Square(0);
    }

    @Test
    void testInitializePotentialValues(){
        List<Integer> expected = Arrays.asList(1,2,3,4,5,6,7,8,9);

        assertEquals(expected,square.getPotentialValues());
    }

    @Test
    void testStartValue(){
        assertEquals(0, square.getValue());
    }

    @Test
    void testSolveSquare(){
        square.setPotentialValues(List.of(4));
        square.solveSquare();
        assertEquals(4,square.getValue());
        assertEquals(new ArrayList<>(),square.getPotentialValues());
    }

    @Test
    void testSolveSquareWithValue(){
        square.solveSquareWithValue(5);
        assertEquals(5,square.getValue());
        assertEquals(new ArrayList<>(),square.getPotentialValues());
    }

    @Test
    void testRemovePotentialValues(){
        square.setPotentialValues(new LinkedList<>(Arrays.asList(7,8,9)));
        square.removePotentialValues(new LinkedList<>(Arrays.asList(8,9)));
        assertEquals(Arrays.asList(7),square.getPotentialValues());
    }

    @Test
    void testEquals(){
        Square square2=new Square(0);
        assertEquals(square,square2);
    }
}