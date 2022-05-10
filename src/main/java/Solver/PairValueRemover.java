package Solver;

import Grid.NineSquareUnit;
import Grid.Sudoku;

import java.util.ArrayList;
import java.util.List;

public class PairValueRemover {

    private static List<Pair> removePairsFromNineSquareUnit(NineSquareUnit unit){
        List<Pair> pairs=PairFinder.findPairsInNineSquareUnit(unit);
        List<Pair> removedPairs=new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.getOccurrences() == pair.getPotentialValues().size() && pair.getPotentialValues().size() > 1) {
                unit.removePairFromSquares(pair.getPotentialValues());
                removedPairs.add(pair);
            }
        }
        return removedPairs;
    }

    public static boolean removePairsFromRow(Sudoku sudoku, int rowIndex){
        List<Pair> removedPairs=removePairsFromNineSquareUnit(sudoku.getRows().get(rowIndex));
        if (removedPairs.size()!=0){
            for (int i=0;i<9;i++){
                if (PairFinder.findPairIndex(removedPairs,new Pair(sudoku.getRows().get(rowIndex).getSquares().get(i).getPotentialValues()))!=-1){
                    DataUpdater.updateEverythingFromRow(sudoku,rowIndex,i);
                }
            }
            return true;
        }
        return false;
    }

    public static boolean removePairsFromColumn(Sudoku sudoku, int columnIndex){
        List<Pair> removedPairs=removePairsFromNineSquareUnit(sudoku.getColumns().get(columnIndex));
        if (removedPairs.size()!=0){
            for (int i=0;i<9;i++){
                if (PairFinder.findPairIndex(removedPairs,new Pair(sudoku.getColumns().get(columnIndex).getSquares().get(i).getPotentialValues()))!=-1){
                    DataUpdater.updateEverythingFromColumn(sudoku,i,columnIndex);
                }
            }
            return true;
        }
        return false;
    }

    public static boolean removePairsFromBigSquare(Sudoku sudoku, int bigSquareIndex){
        List<Pair> removedPairs=removePairsFromNineSquareUnit(sudoku.getBigSquares().get(bigSquareIndex));
        if (removedPairs.size()!=0){
            for (int i=0;i<9;i++){
                if (PairFinder.findPairIndex(removedPairs,new Pair(sudoku.getBigSquares().get(bigSquareIndex).getSquares().get(i).getPotentialValues()))!=-1){
                    DataUpdater.updateEverythingFromBigSquare(sudoku,3*(bigSquareIndex/3)+i/3,3*(bigSquareIndex%3)+i%3);
                }
            }
            return true;
        }
        return false;
    }

    public static boolean removePairsFromSudoku(Sudoku sudoku){
        boolean pairFound= false;
        for (int i=0;i<9;i++){
            pairFound=removePairsFromRow(sudoku,i) ||
            removePairsFromColumn(sudoku,i) ||
            removePairsFromBigSquare(sudoku,i);
        }
        return pairFound;
    }
}
