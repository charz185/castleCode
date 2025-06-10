
/**
 * multiplies cell move to by -1
 *
 * @author (Charles Zabelski)
 */
public class Knight extends ChessPiece
{
    public Knight(int x, int y, boolean isBlack){
        super(x,y,isBlack);
    }
    
    public void CellFunction(Board board,int targetX, int targetY){
        board.setCell(targetX,targetY,board.getCell(targetX,targetY)*-1);
    } 
    @Override
    public String getPieceType(){
        return "Knight";
    }   
    @Override
    public void moveTo(Board board,int x, int y){
        boolean CanMoveTo = true;
        CanMoveTo = super.capturePiece(board,x,y);
        if (Math.abs(super.getX()-x) ==0 || Math.abs(super.getY()-y) ==0){
            CanMoveTo = false;
        }
        if (Math.abs(super.getX()-x) >2 || Math.abs(super.getY()-y) >2){
            CanMoveTo = false;
        }
        if (Math.abs(super.getX()-x) ==2 && Math.abs(super.getY()-y) !=1){
            CanMoveTo = false;
        }
        if (Math.abs(super.getX()-x) !=1 && Math.abs(super.getY()-y) ==2){
            CanMoveTo = false;
        }
        if (CanMoveTo){
            super.moveTo(board,x,y);
        } else{
            board.printError("[ERROR] Impossible Move: Piece type is Pawn\n turn was #"+board.getTurn()+"\nColor Piece is Black="+super.getIsBlack());
        }
        
    }
}
