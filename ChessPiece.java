import java.util.ArrayList;

/**
 * Chess piece abstract class
 *
 * @author (Charles Zabelski)
 */
public abstract class ChessPiece
{
    private int x;
    private int y;
    private boolean isBlack;
    public ChessPiece(int x, int y, boolean isBlack)
    {
        this.x = x;
        this.y = y;
        this.isBlack = isBlack;
    }
    /**
     * Default move function.
     * Moves the piece,removes any piece at target cell, increments turn, and does the piece's cell function.
     */
    public void moveTo(Board b,int x, int y){
        b.removePieceByCell(x,y);
        CellFunction(b,x,y);
        this.x = x;
        this.y = y;
        b.incrementTurn();
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean getIsBlack(){
        return isBlack;
    }
    /**
     * The function the piece will enact when it moves to a new cell.
     */
    public abstract void CellFunction(Board b,int tx, int ty);
    /**
     * Checks if there is a piece in the targeted position that can be captured.
     * Returns true if there is, false if the cell is empty or of the same color.
     */
    public boolean capturePiece(Board b,int x, int y){
        ChessPiece tCellPiece = b.findPieceByCell(x,y);
        if (tCellPiece != null){
            if (tCellPiece.getIsBlack() == this.getIsBlack()){
                return false;
            } else{
                return true;
            }
       }
       return true;
    }
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        ChessPiece objPiece = (ChessPiece)obj;
        if (this.getX() == objPiece.getX() && this.getY() == objPiece.getY()){
            return true;
        }
        return false;
    }
    /**
     * Gets a Horizontal or Vertical line of cell positions, and returns them in a 2d Array.
     * [[x,y],[x,y]] <- each 1d array is a cell holding its positions.
     * Does NOT return actual cell values.
     */
    public int[][] getHVLineOfCells(int tx, int ty){
        ArrayList<int[]> cells = new ArrayList<int[]>();
        if (tx == getX()){
            if (ty-getY() > 0){
                for (int y = getY()+1;y<ty;y++){
                    cells.add(new int[]{tx,y});
                }
            }else{
                for (int y = getY()-1;y>ty;y--){
                    cells.add(new int[]{tx,y});
                }
            }
        }else{
            if (tx-getX() > 0){
                for (int x = getX()+1;x<tx;x++){
                    cells.add(new int[]{x,ty});
                }
            }else{
                for (int x = getX()-1;x>tx;x--){
                   cells.add(new int[]{x,ty});
                }
            }
        }
        return cells.toArray(new int[cells.size()][2]);
    }
    /**
     * Gets a Diagonal line of cell positions, and returns them in a 2d Array.
     * [[x,y],[x,y]] <- each 1d array is a cell holding its positions.
     * Does NOT return actual cell values.
     */
    public int[][] getDiagonalCells(int tx, int ty){
        ArrayList<int[]> cells = new ArrayList<int[]>();
        if (getX()-x >0){
            for (int i = 0; i < getX()-x; i++){
                if (getY()-y >0){
                    cells.add(new int[]{getX()+i,getY()+i});
                } else{
                    cells.add(new int[]{getX()+i,getY()-i});
                }
            }
        } else{
            for (int i = 0; i < Math.abs(getX()-x); i++){
                if (getY()-y >0){
                    cells.add(new int[]{getX()-i,getY()-i});
                } else{
                    cells.add(new int[]{getX()-i,getY()+i});
                }
            }
        }
        return cells.toArray(new int[cells.size()][2]);
    }
    /**
     * Returns subclasses name of piece.
     * Pawn class returns "Pawn".
     */
    public abstract String getPieceType();
}
