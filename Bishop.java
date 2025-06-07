/**
 * Add two cells-assign result to cells between to Bishop operator piece
 *
 * @author (Charles Zabelski)
 */
public class Bishop extends ChessPiece
{
    public Bishop(int x, int y, boolean isBlack){
        super(x,y,isBlack);
    }
    public void CellFunction(Board board,int targetX, int targetY){
        int[][] cells = super.getDiagonalCells(targetX,targetY);  
        for (int[] cellPos:cells){
            board.setCell(cellPos[0],cellPos[1],board.getCell(targetX,targetY)+board.getCell(super.getX(),super.getY()));
        }
    } 
    @Override
    public String getPieceType(){
        return "Bishop";
    }
    @Override
    public void moveTo(Board board,int x, int y){
        boolean CanMoveTo = true;
        if (super.getX() == x && super.getY() == y){
            CanMoveTo=false;
        }
        //bishop
        if (Math.abs(super.getX()-x) != Math.abs(super.getY()-y)){
            CanMoveTo =false;
        }
        CanMoveTo = super.capturePiece(board,x,y);
        
        
        //bishop movement
        int[][] cells = super.getDiagonalCells(x,y); 
        for (int[] cell:cells){
            if (board.findPieceByCell(cell[0],cell[1]) !=null){
                CanMoveTo = false;
            }
        }
        if (CanMoveTo){
            super.moveTo(board,x,y);
        } else{
            board.printError("[ERROR] Impossible Move: Piece type is Bishop\n turn was #"+board.getTurn()+"\nColor Piece is Black="+super.getIsBlack()+" target Position was x="+super.getX()+" y="+super.getY());
        }
        
    }
}

