/**
 * Multiply two cells-assign result to cells between to  Rook operator piece
 *
 * @author (Charles Zabelski)
 */
public class Rook extends ChessPiece
{
    public Rook(int x, int y, boolean isBlack){
        super(x,y,isBlack);
    }
    public void CellFunction(Board board,int targetX, int targetY){
        int[][] cells = super.getHVLineOfCells(targetX,targetY);  
        for (int[] cellPos:cells){
            board.setCell(cellPos[0],cellPos[1],board.getCell(targetX,targetY)*board.getCell(super.getX(),super.getY()));
        }
    } 
    @Override
    public String getPieceType(){
        return "Rook";
    }
    @Override
    public void moveTo(Board board,int x, int y){
        boolean CanMoveTo = true;
        if (super.getX() == x && super.getY() == y){
            CanMoveTo=false;
        }
        //rook movement
        if (!(x == super.getX() && y != super.getY()) && !(x != super.getX() && y == super.getY())){
            CanMoveTo = false;
        }
        CanMoveTo = super.capturePiece(board,x,y);
        int[][] cells = super.getHVLineOfCells(x,y);  
        for (int[] cell:cells){
            if (board.findPieceByCell(cell[0],cell[1]) !=null){
                CanMoveTo = false;
            }
        }
        if (CanMoveTo){
            super.moveTo(board,x,y);
        } else{
            board.printError("[ERROR] Impossible Move: Piece type is Rook\n turn was #"+board.getTurn()+"\nColor Piece is Black="+super.getIsBlack()+" target Position was x="+super.getX()+" y="+super.getY());
        }
        
    }
}

