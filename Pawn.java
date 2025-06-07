
/**
 * Add 1 to cell operator pawn
 *
 * @author (Charles Zabelski)
 */
public class Pawn extends ChessPiece
{
    public Pawn(int x, int y, boolean isBlack){
        super(x,y,isBlack);
    }
    
    public void CellFunction(Board board,int targetX, int targetY){
        board.setCell(targetX,targetY,board.getCell(targetX,targetY)+1);
    } 
    @Override
    public String getPieceType(){
        return "Pawn";
    }   
    @Override
    public void moveTo(Board board,int x, int y){
        boolean CanMoveTo = true;
        CanMoveTo = super.capturePiece(board,x,y);
        if (super.getY() == y){
            CanMoveTo = false;
        }else{
            if (super.getY()+1 != y && super.getY()-1 != y){
                CanMoveTo = false;   
            } else{
                if (super.getIsBlack()){
                    if (super.getY() > y){
                        CanMoveTo = false;
                    }
                }else{
                    if (super.getY() < y){
                        CanMoveTo = false;
                    }
                }
            }
        }
        if (super.getX() != x  && CanMoveTo)
        {
            if (super.getX()+1 != x && super.getX()-1 != x){
                CanMoveTo = false;   
            }
            if (board.findPieceByCell(x,y) == null){
                CanMoveTo = false;
            }
        }
        if (CanMoveTo){
            super.moveTo(board,x,y);
        } else{
            board.printError("[ERROR] Impossible Move: Piece type is Pawn\n turn was #"+board.getTurn()+"\nColor Piece is Black="+super.getIsBlack());
        }
        
    }
}
