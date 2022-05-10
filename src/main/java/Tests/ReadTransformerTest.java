package Tests;

import Grid.NineSquareUnit;
import Grid.Square;
import Grid.Sudoku;
import Solver.ReadTransformer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadTransformerTest {

    @Test
    void testGetSudokuRowsFromText(){
        Square square = new Square(1);

        List<Square> squareList = new ArrayList<>();

        for (int i=0;i<9;i++){
            squareList.add(square);
        }

        NineSquareUnit nineSquareUnit = new NineSquareUnit(squareList);

        List<NineSquareUnit> nineSquareUnitList = new ArrayList<>();

        for (int i=0;i<9;i++){
            nineSquareUnitList.add(nineSquareUnit);
        }

        assertEquals(ReadTransformer.getSudokuRowsFromText("111111111111111111111111111111111111111111111111111111111111111111111111111111111"),new Sudoku(nineSquareUnitList));
    }

}