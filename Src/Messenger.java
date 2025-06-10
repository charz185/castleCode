import java.util.Arrays;
/**
 * while loop piece.
 * starts loop on odd # move, ends loops instruction on even # move for the specific piece.
 * When loop instructions need to reset, reset pieces effected and restart to beginning of loop.
 * doesnt move, unless its moved by a jester.
 *
 * @author (Charles Zabelski)
 */
public class Messenger extends ChessPiece
{
    private ChessPiece[] InstructionPieces;
    private int[][] UnaffectedCells;
    private int[][] XMoves;
    private int[][] YMoves;
    private int turn = 0;
    public Messenger(int x, int y, boolean isBlack){
        super(x,y,isBlack);
    }
    @Override
    public String getPieceType(){
        return "Messenger";
    }
    @Override
    public void CellFunction(Board board,int targetX, int targetY){
        int value = board.getCell(targetX,targetY);
        if (board.getCell(targetX,targetY) != 0){
            if (turn % 2 == 1 ){
                for (int i=0; i < XMoves.length;i++){
                    ChessPiece piece = board.findPieceByCell(XMoves[i][0],YMoves[i][0]);
                    piece.moveTo(board,XMoves[i][1],YMoves[i][1]);
                }
            }
            
            else
            {
                if (value != 0){
                    System.out.println("reverse");
                    reverseBoard(board,targetX,targetY);
                    
                    this.CellFunction(board,targetX,targetY);
                }
            }
        }        
    }    
    
    @Override
    public void moveTo(Board board,int x, int y){
        boolean CanMoveTo = true;
        if (CanMoveTo){
            board.incrementTurn();
            this.turn++;
            CellFunction(board,x,y);
        } else{
            board.printError("[ERROR] Impossible Move: Piece type is Messenger\n turn was #"+board.getTurn()+"\nColor Piece is Black="+super.getIsBlack());
        }
        
    }
    /**
     * reverses board to begining of the loop
     */
    public void reverseBoard(Board board, int targetX, int targetY){
        board.setTurn(board.getTurn()-XMoves.length);
        int temp = board.getCell(targetX,targetY);
        board.setBoard(UnaffectedCells);
        board.setCell(targetX,targetY,temp);
        board.setPieces(InstructionPieces);
    }
    
    public int[][] getBoardValue(){
        if (UnaffectedCells == null){
            return null;
        }
        int[][] b1 = new int[UnaffectedCells.length][UnaffectedCells[0].length];
        for (int i=0;i<UnaffectedCells.length;i++){
            b1[i] = Arrays.copyOf(UnaffectedCells[i],UnaffectedCells[i].length);
        }
        return b1;
    }
    /**
     * sets the instructions and needed values for the loop.
     */
    public void setInstructions(int[][] XMoves,int[][] YMoves,ChessPiece[] InstructionPieces,int[][] UnaffectedCells){
        this.InstructionPieces = InstructionPieces;
        this.XMoves = XMoves;
        this.YMoves = YMoves;
        this.UnaffectedCells = UnaffectedCells;
    }
    public int[][] getUnaffectedCells(){
        return UnaffectedCells;
    }
    public int[][] getXMoves(){
        return UnaffectedCells;
    }
    public int[][] getYMoves(){
        return UnaffectedCells;
    }
    public ChessPiece[] getPieces(){
        return InstructionPieces;
    }
}
