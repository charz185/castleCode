
/**
 * Moves other pieces that are jumped over
 *
 * @author (Charles Zabelski)
 */
public class Jester extends ChessPiece
{
    public Jester(int x, int y, boolean isBlack){
        super(x,y,isBlack);
    }
    @Override
    public String getPieceType(){
        return "Jester";
    }
    public void CellFunction(Board board,int targetX, int targetY){
        int movePieceAmount = board.getCell(targetX,targetY);
        if (movePieceAmount > 0){
            if (targetX == super.getX()){
                if (targetY-super.getY() > 0){
                    for (int y = super.getY()+1;y<targetY;y++){
                        ChessPiece jumpedOver = board.findPieceByCell(targetX,y);
                        if (jumpedOver != null){
                            jumpedOver.moveTo(board,jumpedOver.getX()+movePieceAmount,jumpedOver.getY());
                        }
                    }
                }else{
                    for (int y = super.getY()-1;y>targetY;y--){
                        ChessPiece jumpedOver = board.findPieceByCell(targetX,y);
                        if (jumpedOver != null){
                            jumpedOver.moveTo(board,jumpedOver.getX()-movePieceAmount,jumpedOver.getY());
                        }
                    }
                }
            }else{
                if (targetX-super.getX() > 0){
                    for (int x = super.getX()+1;x<targetX;x++){
                        ChessPiece jumpedOver = board.findPieceByCell(x,targetY);
                        if (jumpedOver != null){
                            jumpedOver.moveTo(board,jumpedOver.getX(),jumpedOver.getY()+movePieceAmount);
                        }
                    }
                }else{
                    for (int x = super.getX()-1;x>targetX;x--){
                        ChessPiece jumpedOver = board.findPieceByCell(x,targetY);
                        if (jumpedOver != null){
                            jumpedOver.moveTo(board,jumpedOver.getX(),jumpedOver.getY()-movePieceAmount);
                        }
                    }
                }
            }
        }
    }    
    @Override
    public void moveTo(Board board,int x, int y){
        boolean CanMoveTo = true;
        if (super.getX() == x && super.getY() == y){
            CanMoveTo = false;
        }
        if (!(x == super.getX() && y != super.getY()) && !(x != super.getX() && y == super.getY())){
            CanMoveTo = false;
        }
        CanMoveTo = super.capturePiece(board,x,y);
        if (CanMoveTo){
            super.moveTo(board,x,y);
        } else{
            board.printError("[ERROR] Impossible Move: Piece type is Jester\n turn was #"+board.getTurn()+"\nColor Piece is Black="+super.getIsBlack());
        }
        
    }
}
