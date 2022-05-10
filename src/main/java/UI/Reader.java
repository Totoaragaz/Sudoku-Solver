package UI;

import java.util.Scanner;

/**
 * Class for reading Sudoku codes
 */

public class Reader {

    /**
     * Reads a sudoku code
     * @return the sudoku code
     */
    public static String readLine(){
        Scanner scanner=new Scanner(System.in);
        return scanner.nextLine();
    }
}
