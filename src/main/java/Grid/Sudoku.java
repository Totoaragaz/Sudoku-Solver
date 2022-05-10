package Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Sudoku Grid
 */

public class Sudoku {
    private List<NineSquareUnit> rows;
    private List<NineSquareUnit> columns;
    private List<NineSquareUnit> bigSquares;

    public Sudoku(List<NineSquareUnit> rows, List<NineSquareUnit> columns, List<NineSquareUnit> bigSquares) {
        this.rows = rows;
        this.columns = columns;
        this.bigSquares = bigSquares;
    }

    public List<NineSquareUnit> getRows() {
        return rows;
    }

    public void setRows(List<NineSquareUnit> rows) {
        this.rows = rows;
    }

    public List<NineSquareUnit> getColumns() {
        return columns;
    }

    public Sudoku(List<NineSquareUnit> rows) {
        this.rows = rows;
        this.columns=new ArrayList<>();
        this.bigSquares= new ArrayList<>();
    }

    public void setColumns(List<NineSquareUnit> columns) {
        this.columns = columns;
    }

    public List<NineSquareUnit> getBigSquares() {
        return bigSquares;
    }

    public void setBigSquares(List<NineSquareUnit> bigSquares) {
        this.bigSquares = bigSquares;
    }

    /**
     * Initializes the column values, using the row values from the sudoku text code.
     */
    public void turnRowsIntoColumns(){
        for (int i=0;i<9;i++) {
            List<Square> squareList=new ArrayList<>();
            for (int j=0;j<9;j++) {
                Square square = new Square(this.rows.get(j).getSquares().get(i).getValue());
                squareList.add(square);
            }
            columns.add(new NineSquareUnit(squareList));
        }
    }

    /**
     * Initializes the big square values, using the row values from the sudoku text code.
     */
    public void turnRowsIntoBigSquares(){
        for (int i=0;i<9;i++) {
            List<Square> squareList=new ArrayList<>();
            for (int j=0;j<9;j++) {
                Square square = new Square(this.rows.get(3 * (i / 3) + j / 3).getSquares().get(3 * (i % 3) + j % 3).getValue());
                squareList.add(square);
            }
            bigSquares.add(new NineSquareUnit(squareList));
        }
    }

    /**
     * Checks the validity of all rows.
     * @return true if valid, false otherwise
     */
    private boolean checkRowsValidity(){
        for (int i=0;i<9;i++){
            if (!rows.get(i).checkValidity()) return false;
        }
        return true;
    }

    /**
     * Checks the validity of all columns.
     * @return true if valid, false otherwise
     */
    private boolean checkColumnsValidity(){
        for (int i=0;i<9;i++){
            if (!columns.get(i).checkValidity()) return false;
        }
        return true;
    }

    /**
     * Checks the validity of all big squares.
     * @return true if valid, false otherwise
     */
    private boolean checkBigSquaresValidity(){
        for (int i=0;i<9;i++){
            if (!bigSquares.get(i).checkValidity()) return false;
        }
        return true;
    }

    /**
     * Checks the validity of the sudoku.
     * @return true if valid, false otherwise
     */
    public boolean checkValidity(){
        return checkRowsValidity() && checkColumnsValidity() && checkBigSquaresValidity();
    }

    /**
     * Updates big squares from rows and rows from big squares
     * @param index the index of the unit that was updated
     * @param toUpdate the unit that needs to be updated
     * @param updateFrom the unit that was updated
     */
    private void updateRowsAndBigSquares(int index, List<NineSquareUnit> toUpdate, List<NineSquareUnit> updateFrom) {
        for (int i=0;i<9;i++){
            toUpdate.get(3*(index/3)+i/3).getSquares().get(3*(index%3)+i%3).setValue(updateFrom.get(index).getSquares().get(i).getValue());
            toUpdate.get(3*(index/3)+i/3).getSquares().get(3*(index%3)+i%3).setPotentialValues(updateFrom.get(index).getSquares().get(i).getPotentialValues());
        }
    }

    /**
     * Updates columns from rows and rows from columns
     * @param index the index of the unit that was updated
     * @param toUpdate the unit that needs to be updated
     * @param updateFrom the unit that was updated
     */
    private void updateRowsAndColumns(int index, List<NineSquareUnit> toUpdate, List<NineSquareUnit> updateFrom){
        for (int i=0;i<9;i++){
            toUpdate.get(i).getSquares().get(index).setValue(updateFrom.get(index).getSquares().get(i).getValue());
            toUpdate.get(i).getSquares().get(index).setPotentialValues(updateFrom.get(index).getSquares().get(i).getPotentialValues());
        }
    }

    /**
     * Updates columns using the values from a row.
     * Used after solving a square in that row, in order to keep the grid updated
     * @param rowIndex the index of the row that had a solved square
     */
    public void updateColumnsFromRow(int rowIndex){
        updateRowsAndColumns(rowIndex,columns,rows);
    }

    /**
     * Updates big squares using the values from a row.
     * Used after solving a square in that row, in order to keep the grid updated
     * @param rowIndex the index of the row that had a solved square
     */
    public void updateBigSquaresFromRow(int rowIndex){
        updateRowsAndBigSquares(rowIndex, bigSquares, rows);
    }

    /**
     * Updates rows using the values from column.
     * Used after solving a square in that column, in order to keep the grid updated
     * @param columnIndex the index of the column that had a solved square
     */
    public void updateRowsFromColumn(int columnIndex){
        updateRowsAndColumns(columnIndex,rows,columns);
    }

    /**
     * Updates bigSquares using the values from column.
     * Used after solving a square in that column, in order to keep the grid updated
     * @param columnIndex the index of the column that had a solved square
     */
    public void updateBigSquaresFromColumn(int columnIndex){
        for (int i=0;i<9;i++){
            bigSquares.get(3*(i/3)+columnIndex/3).getSquares().get(3*(i%3)+columnIndex%3).setValue(columns.get(columnIndex).getSquares().get(i).getValue());
            bigSquares.get(3*(i/3)+columnIndex/3).getSquares().get(3*(i%3)+columnIndex%3).setPotentialValues(columns.get(columnIndex).getSquares().get(i).getPotentialValues());
        }
    }

    /**
     * Updates rows using the values from big square.
     * Used after solving a square in that big square, in order to keep the grid updated
     * @param bigSquareIndex the index of the big square that had a solved square
     */
    public void updateRowsFromBigSquare(int bigSquareIndex){
        updateRowsAndBigSquares(bigSquareIndex, rows, bigSquares);
    }

    /**
     * Updates columns using the values from big square.
     * Used after solving a square in that big square, in order to keep the grid updated
     * @param bigSquareIndex the index of the big square that had a solved square
     */
    public void updateColumnsFromBigSquare(int bigSquareIndex){
        for (int i=0;i<9;i++){
            columns.get(3*(bigSquareIndex%3)+i%3).getSquares().get(3*(bigSquareIndex/3)+i/3).setValue(bigSquares.get(bigSquareIndex).getSquares().get(i).getValue());
            columns.get(3*(bigSquareIndex%3)+i%3).getSquares().get(3*(bigSquareIndex/3)+i/3).setPotentialValues(bigSquares.get(bigSquareIndex).getSquares().get(i).getPotentialValues());
        }
    }

    /**
     * Checks if the Sudoku is solved
     * @return true if solved, false otherwise
     */
    public boolean checkIfSolved(){
        for (int i=0;i<9;i++){
            rows.get(i).updateSolvedSquares();
            if (!rows.get(i).checkIfSolved()) return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sudoku that = (Sudoku) o;
        return Objects.equals(this.rows,that.rows) && Objects.equals(this.columns,that.columns) && Objects.equals(this.bigSquares,that.bigSquares);
    }


}
