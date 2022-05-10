package Solver;

import java.util.List;
import java.util.Objects;

/**
 * A pair of multiple Squares with the same potential values
 */

public class Pair {

    private List<Integer> potentialValues;
    private int occurrences;

    public void setPotentialValues(List<Integer> potentialValues) {
        this.potentialValues = potentialValues;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    public List<Integer> getPotentialValues() {
        return potentialValues;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public Pair(List<Integer> potentialValues) {
        this.potentialValues = potentialValues;
        this.occurrences = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(potentialValues, pair.potentialValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(potentialValues, occurrences);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "potentialValues=" + potentialValues +
                ", occurrences=" + occurrences +
                '}';
    }
}
