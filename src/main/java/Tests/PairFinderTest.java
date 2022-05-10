package Tests;

import Grid.NineSquareUnit;
import Grid.Square;
import Grid.Sudoku;
import Solver.Pair;
import Solver.PairFinder;
import Solver.ReadTransformer;
import Solver.SetUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PairFinderTest {

    @Test
    void testFindPairIndexGood(){
        List<Pair> pairs= List.of(new Pair(Arrays.asList(5, 7)));
        assertEquals(0, PairFinder.findPairIndex(pairs,new Pair(Arrays.asList(5,7))));
    }

    @Test
    void testFindPairIndexBad(){
        List<Pair> pairs= List.of(new Pair(Arrays.asList(5, 7)));
        assertEquals(-1, PairFinder.findPairIndex(pairs,new Pair(Arrays.asList(5,6))));
    }

    @Test
    void testFindPairsInNineSquareUnit(){
        Square square1=new Square(0);
        square1.setPotentialValues(Arrays.asList(1,2));

        Square square2=new Square(0);
        square2.setPotentialValues(Arrays.asList(5,6,7));

        Square square3=new Square(0);

        NineSquareUnit unit=new NineSquareUnit(Arrays.asList(square1,square2,square3,square3,square2,square1,square3,square2,square3));

        List<Pair> expected=new ArrayList<>();

        Pair pair1=new Pair(Arrays.asList(1,2));
        pair1.setOccurrences(2);
        expected.add(pair1);

        Pair pair2=new Pair(Arrays.asList(5,6,7));
        pair2.setOccurrences(3);
        expected.add(pair2);

        assertEquals(expected,PairFinder.findPairsInNineSquareUnit(unit));
    }
}