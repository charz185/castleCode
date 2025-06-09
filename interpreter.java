import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintWriter;

/**
 * Interprets given moves and pieces into usable Castel Code objects and logic
 *
 * @author (Charles Zabelski)
 * @version (a version number or a date)
 */
public class interpreter
{
    
    public static void main(String[] args){
        Board b= RunScript(args[0]);
        if (args.length >1){
            try {
                PrintWriter out = new PrintWriter(args[1]);
                out.println(b.toString());
                out.close();
            }catch (FileNotFoundException e) {
               System.out.println(e);
            }
        }
    }
    /**
     * Runs the script given at the path.
     * returns completed board.
     */
    public static Board RunScript(String Path){
        ArrayList<String> commands = new ArrayList<String>();
        String[] B = null;
        try {
          File Script = new File(Path);
          Scanner myReader = new Scanner(Script);
          String BoardSizeCmd = myReader.nextLine();
          B = BoardSizeCmd.split(" ");
          
          while (myReader.hasNextLine()) {
            String cmd = myReader.nextLine();
            commands.add(cmd);
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        Boolean PastPieces = false;
        
        ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
        ArrayList<int[]> xMovesList = new ArrayList<int[]>(); 
        ArrayList<int[]> yMovesList = new ArrayList<int[]>(); 
        for (String cmd: commands){
            if (cmd.trim().equals("Moves")){
                PastPieces = true;
            } else{
                if (!PastPieces){
                    pieces.add(CmdToPiece(cmd));
                } else{
                    int[] move = CmdToMove(cmd);
                    xMovesList.add(new int[] {move[0],move[2]});
                    yMovesList.add(new int[] {move[1],move[3]});
                }
            }
        }
        Board board = SetupBoard(Integer.parseInt(B[0]),Integer.parseInt(B[1]),pieces.toArray(new ChessPiece[pieces.size()]));
        runMoveScript(board,xMovesList.toArray(new int[xMovesList.size()][2]),yMovesList.toArray(new int[yMovesList.size()][2]));
        return board;
    }
    public static int[] CmdToMove(String cmd){
        String[] cmdSplit = cmd.split(" ");
        return new int[]{Integer.parseInt(cmdSplit[0]),Integer.parseInt(cmdSplit[1]),Integer.parseInt(cmdSplit[2]),Integer.parseInt(cmdSplit[3])};
    }
    public static ChessPiece CmdToPiece(String cmd){
        String[] splitCmd = cmd.trim().split(" ");
        ChessPiece returnPiece = null;
        switch (splitCmd[0]){
            case "Pawn":
                returnPiece = new Pawn(Integer.parseInt(splitCmd[1]),Integer.parseInt(splitCmd[2]),Boolean.parseBoolean(splitCmd[3]));
                break;
            case "Knight":
                returnPiece = new Knight(Integer.parseInt(splitCmd[1]),Integer.parseInt(splitCmd[2]),Boolean.parseBoolean(splitCmd[3]));
                break;
            case "Bishop":
                returnPiece = new Bishop(Integer.parseInt(splitCmd[1]),Integer.parseInt(splitCmd[2]),Boolean.parseBoolean(splitCmd[3]));
                break;
            case "Rook":
                returnPiece = new Rook(Integer.parseInt(splitCmd[1]),Integer.parseInt(splitCmd[2]),Boolean.parseBoolean(splitCmd[3]));
                break;
            case "Oracle":
                returnPiece = new Oracle(Integer.parseInt(splitCmd[1]),Integer.parseInt(splitCmd[2]),Boolean.parseBoolean(splitCmd[3]));
                break;
            case "Messenger":
                returnPiece = new Messenger(Integer.parseInt(splitCmd[1]),Integer.parseInt(splitCmd[2]),Boolean.parseBoolean(splitCmd[3]));
                break;
            case "Jester":
                returnPiece = new Jester(Integer.parseInt(splitCmd[1]),Integer.parseInt(splitCmd[2]),Boolean.parseBoolean(splitCmd[3]));
                break;
            case "Scribe":
                returnPiece = new Scribe(Integer.parseInt(splitCmd[1]),Integer.parseInt(splitCmd[2]),Boolean.parseBoolean(splitCmd[3]));
                break;
        }
        if (returnPiece == null){
             System.out.println("Given Command can not make new piece: "+cmd);
        }
        return returnPiece;
    }
    public static boolean isPiecePositionsLegal(ChessPiece[] pieces){
        boolean legal = true;
        for (ChessPiece piece: pieces){
            int samePositions = 0;
            for (ChessPiece piece2: pieces){
                if (piece2.getX() == piece.getX() && piece2.getY() == piece.getY()){
                    samePositions++;
                }
            }
            if (samePositions != 1){
                legal = false;
                System.out.println("[ERROR] Board Piece's position is illegal");
                System.out.println("position of pieces is X="+piece.getX()+", y="+piece.getY());
            }
        }
        return legal;
    }
    public static Board SetupBoard(int width, int height,ChessPiece[] pieces){
        Board board = new Board(width,height);
        if (isPiecePositionsLegal(pieces) != false){
            board.setPieces(pieces);
            return board;
        }
        return null;
    }
    public static void checkMoveScript(Board board, int[][] xMoves,int[][] yMoves){
        for (int i=0; i < xMoves.length;i++){
            ChessPiece piece = board.findPieceByCell(xMoves[i][0],yMoves[i][0]);
            //System.out.println("turn #"+xMoves[i][0]+" "+yMoves[i][0]);
            //messengers
            if (piece != null && piece.getPieceType()=="Messenger"){
                int[][] tempXMoves;
                int[][] tempYMoves;
                ChessPiece[] pieces = Arrays.copyOfRange(board.getPieces(),0,board.getPieces().length);
                
                int z = 1;
                while(piece != board.findPieceByCell(xMoves[i+z][0],yMoves[i+z][0]))
                {
                    z++;
                    if (i+z > xMoves.length){
                        board.printError("[ERROR] Unclosed Loop: Piece type is Messenger\n turn was #"+(i+1));
                    }
                }
                tempXMoves = new int[z][2];
                tempYMoves = new int[z][2];
                for (int w = 1; w <= z;w++){
                    if (xMoves[i+w][0] !=-1){
                        tempXMoves[w-1] = Arrays.copyOfRange(xMoves[i+w],0,2);
                        tempYMoves[w-1] = Arrays.copyOfRange(yMoves[i+w],0,2);
                        xMoves[i+w] = new int[]{-1, -1};
                        yMoves[i+w] = new int[]{-1, -1};
                    }
                }
                Messenger MPiece = (Messenger)piece;
                int[][] b1 = new int[board.getBoard().length][board.getBoard()[0].length];
                for (int l=0;i<board.getBoard().length;i++){
                    b1[i] = Arrays.copyOfRange(board.getBoard()[l],0,board.getBoard()[l].length);
                }
                MPiece.setInstructions(tempXMoves,tempYMoves,pieces,b1);
                piece = MPiece;
            }
        }
    }
    public static void runMoveScript(Board board, int[][] xMoves,int[][] yMoves){
        checkMoveScript(board,xMoves,yMoves);
        for (int i=0; i < xMoves.length;i++){
            if (board.getIsPaused()){
                break;
            }
            if (xMoves[i][0] != -1){
                ChessPiece piece = board.findPieceByCell(xMoves[i][0],yMoves[i][0]);
                //System.out.println(xMoves[i][0]+" "+yMoves[i][0]+" t"+xMoves[i][1]+" t"+yMoves[i][1]);
                piece.moveTo(board,xMoves[i][1],yMoves[i][1]);
            }
        }

    }
}
