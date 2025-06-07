import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

/**
 * ChessCode driver
 *
 * @author (Charles Zabelski)
 * @version (a version number or a date)
 */
public class Driver
{

    public static void Main(){
        messengerTesting();
    }
    public static void ExamplePrint(){
        ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
        pieces.add(new Scribe(5,0,false));
        ChessPiece[] top = new ChessPiece[115];
        ChessPiece[] bottom = new ChessPiece[115];
        for(int y =0;y < 115;y++){
            pieces.add(new Pawn(0,y,true));
            top[y] = pieces.get(pieces.size()-1);
        }
        int i2 = 0;
        for(int y =116;y < 231;y++){
            pieces.add(new Pawn(0,y,false));
            bottom[i2] = pieces.get(pieces.size()-1);
            i2++;
        }
        ChessPiece[] piecesA = new ChessPiece[pieces.size()];
        piecesA = pieces.toArray(new ChessPiece[pieces.size()]);
        Board board = SetupBoard(5,231,piecesA);
        int[][] xMoves = new int[3][2];
        int[][] yMoves = new int[3][2];
        
        ArrayList<int[]> xMovesList = new ArrayList<int[]>(); 
        ArrayList<int[]> yMovesList = new ArrayList<int[]>(); 
        
        int whiteI =0;
        for (int i = 114; i >=0; i--){
            for (int z = 0;z < 231-bottom[i].getY();z++){
                //white
                xMovesList.add(new int[]{0,0});
                yMovesList.add(new int[]{bottom[whiteI].getY()-z,bottom[whiteI].getY()-z-1});
                //black
                xMovesList.add(new int[]{0,0});
                yMovesList.add(new int[]{top[i].getY()+z,top[i].getY()+z+1});
                
            }
            whiteI++;
        }
        
        xMovesList.add(new int[]{5,0});
        yMovesList.add(new int[]{0,72});
        
        xMovesList.add(new int[]{0,0});
        yMovesList.add(new int[]{72,101});
        
        xMovesList.add(new int[]{0,0});
        yMovesList.add(new int[]{101,108});
        
        xMovesList.add(new int[]{0,0});
        yMovesList.add(new int[]{108,122});
        
        xMovesList.add(new int[]{0,0});
        yMovesList.add(new int[]{122,111});
        
        xMovesList.add(new int[]{0,0});
        yMovesList.add(new int[]{111,32});
        
        //World
        xMovesList.add(new int[]{0,0});
        yMovesList.add(new int[]{32,87});
        
        xMovesList.add(new int[]{0,0});
        yMovesList.add(new int[]{87,111});
        
        xMovesList.add(new int[]{0,0});
        yMovesList.add(new int[]{111,114});
        
        xMovesList.add(new int[]{0,0});
        yMovesList.add(new int[]{114,108});
        
        xMovesList.add(new int[]{0,0});
        yMovesList.add(new int[]{108,100});
        
        xMovesList.add(new int[]{0,0});
        yMovesList.add(new int[]{100,33});
        
        xMoves = xMovesList.toArray(new int[xMovesList.size()][2]);
        yMoves = yMovesList.toArray(new int[yMovesList.size()][2]);
        
        runMoveScript(board,xMoves,yMoves);
        System.out.print(board);
    }
    public static void ChickenJockey()
    {
        //ascii table
        ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
        pieces.add(new Scribe(5,0,false));
        //90:
        //offX=10
        //offY=10
        for (ChessPiece p:get3Pieces(7,11))
        {
            pieces.add(p);
        }
        pieces.add(new Rook(6,11,true));
        for (ChessPiece p:get3Pieces(13,11))
        {
            pieces.add(p);
        }
        pieces.add(new Rook(8,7,false));
        for (ChessPiece p:get5Pieces(10,7))
        {
            pieces.add(p);
        }
        for (ChessPiece p:get2Pieces(10,12))
        {
            pieces.add(p);
        }
        //35
        //x=30, y =10
        for (ChessPiece p:get5Pieces(28,10))
        {
            pieces.add(p);
        }
        for (ChessPiece p:get7Pieces(32,10))
        {
            pieces.add(p);
        }
        pieces.add(new Rook(32,14,true));
        //infinite delays possible black rook
        pieces.add(new Rook(0,0,true));
        //previous but for passing white turns
        pieces.add(new Rook(0,1,false));
        Board board = SetupBoard(100,100,pieces.toArray(new ChessPiece[pieces.size()]));
        
        ArrayList<int[]> xMovesList = new ArrayList<int[]>(); 
        ArrayList<int[]> yMovesList = new ArrayList<int[]>(); 
        //2 bottom
        for (int[] m:get2Moves(10,12)){
            xMovesList.add(new int[] {m[0],m[1]});
            yMovesList.add(new int[] {m[2],m[3]});
        }
        //left
        for (int[] m:get3Moves(7,11)){
            xMovesList.add(new int[] {m[0],m[1]});
            yMovesList.add(new int[] {m[2],m[3]});
        }
        xMovesList.add(new int[] {0,0});
        yMovesList.add(new int[] {0,1});
        //right
        for (int[] m:get3Moves(13,11)){
            xMovesList.add(new int[] {m[0],m[1]});
            yMovesList.add(new int[] {m[2],m[3]});
        }
        
        //passturn
        xMovesList.add(new int[] {0,0});
        yMovesList.add(new int[] {1,0});
        //top
        for (int[] m : get5Moves(10,7)){
            xMovesList.add(new int[] {m[0],m[1]});
            yMovesList.add(new int[] {m[2],m[3]});
        }
        xMovesList.add(new int[] {6,7});
        yMovesList.add(new int[] {11,11});
        //
        xMovesList.add(new int[] {9,9});
        yMovesList.add(new int[] {7,6});
        //
        xMovesList.add(new int[] {0,0});
        yMovesList.add(new int[] {0,1});
        //top multi setup
        xMovesList.add(new int[] {8,10});
        yMovesList.add(new int[] {7,7});
        //multiply 3 to 3
        xMovesList.add(new int[] {7,13});
        yMovesList.add(new int[] {11,11});
        //multiply 5 * 9
        xMovesList.add(new int[] {10,10});
        yMovesList.add(new int[] {7,11});
        //white pass turn
        xMovesList.add(new int[] {0,1});
        yMovesList.add(new int[] {1,1});
        //prepare multiply 45 * 2
        xMovesList.add(new int[] {10,10});
        yMovesList.add(new int[] {11,12});
        //white pass turn
        xMovesList.add(new int[] {1,0});
        yMovesList.add(new int[] {1,1});
        //prepare multiply 45 * 2
        xMovesList.add(new int[] {10,10});
        yMovesList.add(new int[] {12,9});
        //35
        for (int[] m : get5Moves(28,10)){
            xMovesList.add(new int[] {m[0],m[1]});
            yMovesList.add(new int[] {m[2],m[3]});
        }
        for (int[] m : get7Moves(32,10)){
            xMovesList.add(new int[] {m[0],m[1]});
            yMovesList.add(new int[] {m[2],m[3]});
        }
        //rook 
        xMovesList.add(new int[] {32,32});
        yMovesList.add(new int[] {14,10});
        //white pass turn
        xMovesList.add(new int[] {0,1});
        yMovesList.add(new int[] {1,1});
        //rook 
        xMovesList.add(new int[] {32,28});
        yMovesList.add(new int[] {10,10});
        //scribe print
        xMovesList.add(new int[] {5,10});
        yMovesList.add(new int[] {0,10});
        //
        xMovesList.add(new int[] {0,0});
        yMovesList.add(new int[] {1,0});
        //scribe print
        xMovesList.add(new int[] {10,30});
        yMovesList.add(new int[] {10,10});
        runMoveScript(board,xMovesList.toArray(new int[xMovesList.size()][2]),yMovesList.toArray(new int[yMovesList.size()][2]));
        System.out.println(board);
    }
    //messenger testing
    public static void messengerTesting(){
        ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
        
        pieces.add(new Messenger(0,0,true));
        pieces.add(new Pawn(7,5,false));
        Board board = SetupBoard(8,8,pieces.toArray(new ChessPiece[pieces.size()]));
        
        ArrayList<int[]> xMovesList = new ArrayList<int[]>(); 
        ArrayList<int[]> yMovesList = new ArrayList<int[]>(); 
        
        
        xMovesList.add(new int[] {7,7});
        yMovesList.add(new int[] {5,4});
        
        xMovesList.add(new int[] {0,7});
        yMovesList.add(new int[] {0,4});
        
        xMovesList.add(new int[] {7,7});
        yMovesList.add(new int[] {4,3});
        
        xMovesList.add(new int[] {0,7});
        yMovesList.add(new int[] {0,4});
        
        
        runMoveScript(board,xMovesList.toArray(new int[xMovesList.size()][2]),yMovesList.toArray(new int[yMovesList.size()][2]));
        System.out.println(board);
    }
    //ends with Black pawn in space
    public static ChessPiece[] get2Pieces(int offX, int offY){
        return new ChessPiece[]{new Pawn(offX,offY+1,false),new Pawn(offX+1,offY-1,true)};
    }
    public static int[][] get2Moves(int offX, int offY){
        return new int[][]{new int[] {offX,offX,offY+1,offY}, new int[]{offX+1,offX,offY-1,offY}};
    }
    //ends with white pawn in space
    public static ChessPiece[] get3Pieces(int offX, int offY){
        List<ChessPiece> f = new ArrayList<ChessPiece>();
        ChessPiece[] two = get2Pieces(offX,offY);
        f.add(two[0]);
        f.add(two[1]);
        f.add(new Pawn(offX+1,offY+1,false));
        return f.toArray(new ChessPiece[f.size()]);
    }
    public static int[][] get3Moves(int offX, int offY){
        return new int[][]{new int[] {offX,offX,offY+1,offY}, new int[]{offX+1,offX,offY-1,offY},new int[]{offX+1,offX,offY+1,offY}};
    }
    
    //ends with none
    public static ChessPiece[] get5Pieces(int offX,int offY){
        ChessPiece[] left = get2Pieces(offX-1,offY);
        ChessPiece[] right = get2Pieces(offX+1,offY);
        List<ChessPiece> f = new ArrayList<ChessPiece>();
        f.add(left[0]);
        f.add(left[1]);
        f.add(right[0]);
        f.add(right[1]);
        f.add(new Pawn(offX-2,offY-4,true));
        f.add(new Pawn(offX,offY+1,false));
        f.add(new Rook(offX+2,offY,false));
        return f.toArray(new ChessPiece[f.size()]);
    }
    //moves amount = 13
    public static int[][] get5Moves(int offX,int offY){
        int[][] left = get2Moves(offX-1,offY);
        int[][] right = get2Moves(offX+1,offY);
        List<int[]> f = new ArrayList<int[]>();
        f.add(left[0]);
        f.add(left[1]);
        f.add(right[0]);
        f.add(right[1]);
        //rook 1st move
        f.add(new int[]{offX+2,offX+1,offY,offY});
        //black pawn turn pass
        f.add(new int[]{offX-2,offX-2,offY-4,offY-3});
        //rook final
        f.add(new int[]{offX+1,offX-1,offY,offY});
        //Black pawn pass
        f.add(new int[]{offX-2,offX-2,offY-3,offY-2});
        //pawn white final calc
        f.add(new int[]{offX,offX,offY+1,offY});   
        //Black pawn pass
        f.add(new int[]{offX-2,offX-2,offY-2,offY-1});
        //pawn white final pos
        f.add(new int[]{offX,offX,offY,offY-1});   
        return f.toArray(new int[f.size()][4]);
    }
    public static ChessPiece[] get7Pieces(int offX, int offY){
        ChessPiece[] left = get2Pieces(offX-2,offY);
        ChessPiece[] right = get3Pieces(offX+1,offY);
        List<ChessPiece> f = new ArrayList<ChessPiece>();
        f.add(left[0]);
        f.add(left[1]);
        f.add(right[0]);
        f.add(right[1]);
        f.add(right[2]);
        f.add(new Rook(offX+2,offY,true));
        f.add(new Pawn(offX, offY+3,false));
        return f.toArray(new ChessPiece[f.size()]);
    }
    public static  int[][] get7Moves(int offX, int offY){
        int[][] left = get2Moves(offX-2,offY);
        int[][] right = get3Moves(offX+1,offY);
        List<int[]> f = new ArrayList<int[]>();
        f.add(left[0]);
        f.add(left[1]);
        f.add(right[0]);
        f.add(right[1]);
        f.add(right[2]);
        //black rook
        f.add(new int[]{offX+2,offX+1,offY,offY});
        //white pawn
        f.add(new int[]{offX,offX,offY+3,offY+2});
        //black pawn
        f.add(new int[]{offX-2,offX-2,offY,offY+1});
        //white pass pawn
        f.add(new int[]{offX,offX,offY+2,offY+1});
        //rook calc
        f.add(new int[]{offX+1,offX-2,offY,offY});
        f.add(new int[]{offX,offX,offY+1,offY});
        
        f.add(new int[]{offX,offX,offY,offY-1});
        f.add(new int[]{offX-2,offX-6,offY,offY});
        
        return f.toArray(new int[f.size()][4]);
    }
    public static Board SetupBoard(int width, int height,ChessPiece[] pieces){
        Board board = new Board(width,height);
        if (isPiecePositionsLegal(pieces) != false){
            board.setPieces(pieces);
            return board;
        }
        return null;
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
    
    public static void checkMoveScript(Board board, int[][] xMoves,int[][] yMoves){
        for (int i=0; i < xMoves.length;i++){
            ChessPiece piece = board.findPieceByCell(xMoves[i][0],yMoves[i][0]);
            System.out.println("turn #"+xMoves[i][0]+" "+yMoves[i][0]);
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
                System.out.println(xMoves[i][0]+" "+yMoves[i][0]+" t"+xMoves[i][1]+" t"+yMoves[i][1]);
                piece.moveTo(board,xMoves[i][1],yMoves[i][1]);
            }
        }

    }
}
