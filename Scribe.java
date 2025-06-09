
/**
 * Write to Terminal (ascii) operator piece
 *
 * @author (Charles Zabelski)
 */
public class Scribe extends ChessPiece
{
    public Scribe(int x, int y, boolean isBlack){
        super(x,y,isBlack);
    }
    @Override
    public void CellFunction(Board board,int targetX, int targetY){
        System.out.print((char)(board.getCell(targetX,targetY)));
    }    
    @Override
    public String getPieceType(){
        return "Scribe";
    }
    @Override
    public void moveTo(Board board,int x, int y){
        boolean CanMoveTo = true;
        CanMoveTo = super.capturePiece(board,x,y);
        if (super.getX() == x && super.getY() == y){
            CanMoveTo=false;
        }
        
        if (CanMoveTo){
            super.moveTo(board,x,y);
        } else{
            board.printError("[ERROR] Impossible Move: Piece type is Scribe\n turn was #"+board.getTurn()+"\nColor Piece is Black="+super.getIsBlack()+" target Position was x="+super.getX()+" y="+super.getY());
        }
        
    }
}
