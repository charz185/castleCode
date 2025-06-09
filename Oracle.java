import java.util.Arrays;
import java.util.Scanner;
/**
 * Input piece, can take int input and set a cell to the input.
 * @author (Charles Zabelski)
 */
public class Oracle extends ChessPiece
{
    public Oracle(int x, int y, boolean isBlack){
        super(x,y,isBlack);
    }
    @Override
    public String getPieceType(){
        return "Oracle";
    }
    @Override
    public void CellFunction(Board board,int targetX, int targetY){
        System.out.println("The Oracle requires a Integer to be given.");
        Scanner scan = new Scanner(System.in);
        int in = scan.nextInt();
        board.setCell(targetX,targetY,in);
    }    
    
    @Override
    public void moveTo(Board board,int x, int y){
        boolean CanMoveTo = true;
        if (super.getX() == x && super.getY() == y){
            CanMoveTo = false;
        }
        CanMoveTo = super.capturePiece(board,x,y);
        if (CanMoveTo){
            super.moveTo(board,x,y);
        } else{
            board.printError("[ERROR] Impossible Move: Piece type is Messenger\n turn was #"+board.getTurn()+"\nColor Piece is Black="+super.getIsBlack());
        }
        
    }
}
