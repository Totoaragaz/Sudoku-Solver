package Solver;

import Grid.NineSquareUnit;
import Grid.Square;
import Grid.Sudoku;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that transforms a sudoku text code into an instance of a Sudoku
 */

public class ReadTransformer {

    /**
     * Checks the validity of the code size
     * @param code the sudoku code
     * @return true if valid, false otherwise
     */
    private static boolean checkCodeSizeValidity(String code){
        return code.length()==81;
    }

    /**
     * Checks the validity of a character in the code
     * @param code the character that needs to be checked
     * @return true if valid, false if not
     */
    private static boolean checkCodeValidity(char code){
        String numbers="0123456789";
        return numbers.indexOf(code) != -1;
    }

    /**
     * Transforms the sudoku code into an instance of a Sudoku
     * @param code the sudoku code
     * @return the instance of the Sudoku
     */
    public static Sudoku getSudokuRowsFromText(String code){

        if (!checkCodeSizeValidity(code)) throw new RuntimeException("Code size is invalid");

        List<NineSquareUnit> lineList=new ArrayList<>();

        for (int i=0;i<9;i++) {

            List<Square> squareList=new ArrayList<>();

            for (int j=0;j<9;j++){

                if (!checkCodeValidity(code.charAt(i*9+j))) throw new RuntimeException("Code is invalid");
                Square square=new Square(Character.getNumericValue(code.charAt(i*9+j)));
                squareList.add(square);
            }

            NineSquareUnit line=new NineSquareUnit(squareList);

            lineList.add(line);
        }

        return new Sudoku(lineList);
    }
}
