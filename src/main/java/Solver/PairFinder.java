package Solver;

import Grid.NineSquareUnit;

import java.util.ArrayList;
import java.util.List;

public class PairFinder {

    /**
     * Returns the index of a Pair from a list of Pairs
     * @param pairs the list of Pairs
     * @param pair the searched for Pair
     * @return the index of the Pair, -1 if not found
     */
    public static int findPairIndex(List<Pair> pairs, Pair pair){
        for (int i=0;i<pairs.size();i++){
            if (pairs.get(i).equals(pair)) return i;
        }
        return -1;
    }

    /**
     * Finds pairs of Squares in a NineSquareUnit
     * @param unit the searched NineSquareUnit
     * @return a list of all found Pairs in the Unit
     */
    public static List<Pair> findPairsInNineSquareUnit (NineSquareUnit unit){
        List<Pair> pairs=new ArrayList<>();
        for (int i=0;i<9;i++){
            if (unit.getSquares().get(i).getPotentialValues().size()>1 && unit.getSquares().get(i).getPotentialValues().size()< 9-unit.getSolvedSquares()){
                Pair pair=new Pair(unit.getSquares().get(i).getPotentialValues());
                if (!pairs.contains(pair)){
                    pairs.add(pair);
                }
                else {
                    int index=findPairIndex(pairs,pair);
                    pairs.get(index).setOccurrences(pairs.get(index).getOccurrences()+1);
                }
            }
        }
        return pairs;
    }

}
