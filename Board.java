import java.util.*;
/**
 * Chess board, holds each cell that each holds a byte.
 *
 * @author (Charles Zabelski)
 */
public class Board
{
    //horizontal holds verticals
    private boolean isPaused = false;
    private int[][] cells;
    private ChessPiece[] pieces;
    private int turn = 0;
    public Board(){
        cells = new int[8][8];
        pieces = new ChessPiece[8*8];
    }
    public Board(int width, int height){
        cells = new int[height][width];
        pieces = new ChessPiece[width * height];
    }
    public int[][] getBoard(){
        return cells;
    }
    public int[][] getBoardValue(){
        int[][] b1 = new int[cells.length][cells[0].length];
        for (int i=0;i<cells.length;i++){
            b1[i] = Arrays.copyOfRange(cells[i],0,cells[i].length);
        }
        return b1;
    }
    public int getCell(int x, int y){
        return cells[y][x];
    }
    public boolean getIsPaused(){
        return isPaused;
    }
    public int getTurn(){
        return turn;
    }
    public ChessPiece[] getPieces(){
        return pieces;
    }
    public void setBoard(int[][] cells){
        this.cells = cells;
    }
    public void setCell(int x, int y, int cell){
        this.cells[y][x] = cell;
    }
    public void setPieces(ChessPiece[] pieces){
        this.pieces = pieces;
    }
    public void incrementTurn(){
        turn++;
    }
    public void setTurn(int turn){
        this.turn = turn;
    }
    public void resetBoard(){
        this.pieces = new ChessPiece[this.cells.length *this.cells[0].length ];
        this.cells = new int[this.cells[0].length][this.cells.length];
    }
    public void printError(String errorMessage){
        System.out.println(errorMessage);
        isPaused = true;
    }
    public ChessPiece findPieceByCell(int x, int y){
        ChessPiece piece = null;
        for (ChessPiece p:pieces){
            if (p != null){
                if (p.getX() == x && p.getY() == y){
                    piece = p;
                    break;
                }
            }
        }
        return piece;
    }
    public void removePieceByCell(int x, int y){
        int index = 0;
        for (ChessPiece p:pieces){
            if (p != null){
                if (p.getX() == x && p.getY() == y){
                    break;
                }
            }
            index++;
        }
        if (index!=pieces.length){
            pieces[index] = null;
        }
    }
    
    @Override
    public String toString(){
        String returnString = "Turn #"+this.getTurn();
        //cell Values
        returnString += "\n Board Cell Values\n";
        for (int[] cellColumn: cells){
            returnString += ":";
            for (int cell:cellColumn)
            {
                //returnString += String.format("0x%08X", cell)+":";
                returnString += cell+":";
            }
            returnString += "\n";
        }
        returnString += "\n Board Piece Positions\n";
        String[][] positions = new String[cells.length][cells[0].length];
        for (ChessPiece p:pieces){
            if (p != null){
                String Color = "";
                if (p.getIsBlack()){
                    Color = "B.";
                }else{
                    Color = "W.";
                }
                positions[p.getY()][p.getX()] = Color+((String)(p.getClass().getSimpleName()));
            }
        }
        for (String[] cellColumn: positions){
            returnString += ":";
            for (String cell:cellColumn)
            {
                if (cell == null){
                    returnString += " :";
                }else{
                    returnString += cell+":";
            
                }
            }
            returnString += "\n";
        }
        return returnString;
    }
}


