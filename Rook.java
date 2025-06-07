/**
 * Multiply two cells-assign result to cells between to  Rook operator piece
 *
 * @author (Charles Zabelski)
 */
public class Rook extends ChessPiece
{
    public Rook(int x, int y, boolean isBlack){
        super(x,y,isBlack);
    }
    public void CellFunction(Board board,int targetX, int targetY){
        int[][] cells = super.getHVLineOfCells(board,targetX,targetY);  
        for (int[] cellPos:cells){
            board.setCell(cellPos[0],cellPos[1],board.getCell(targetX,targetY)*board.getCell(super.getX(),super.getY()));
        }
    } 
    @Override
    public String getPieceType(){
        return "Rook";
    }
    @Override
    public void moveTo(Board board,int x, int y){
        boolean CanMoveTo = true;
        if (super.getX() == x && super.getY() == y){
            CanMoveTo=false;
        }
        //rook movement
        if (!(x == super.getX() && y != super.getY()) && !(x != super.getX() && y == super.getY())){
            CanMoveTo = false;
        }
        CanMoveTo = super.capturePiece(board,x,y);
        if (x == super.getX()){
            if (y-super.getY() > 0){
                for (int y1 = super.getY()+1;y1<y;y1++){
                    ChessPiece jumpedOver = board.findPieceByCell(x,y1);
                    if (jumpedOver != null){
                        CanMoveTo = false;
                    }
                }
            }else{
                for (int y1 = super.getY()-1;y1>y;y1--){
                    ChessPiece jumpedOver = board.findPieceByCell(x,y1);
                    if (jumpedOver != null){
                        CanMoveTo = false;
                    }
                }
            }
        }else{
            if (x-super.getX() > 0){
                for (int x1 = super.getX()+1;x1<x;x1++){
                    ChessPiece jumpedOver = board.findPieceByCell(x1,y);
                    if (jumpedOver != null){
                        CanMoveTo = false;
                    }
                }
            }else{
                for (int x1 = super.getX()-1;x1>x;x1--){
                    ChessPiece jumpedOver = board.findPieceByCell(x1,y);
                    if (jumpedOver != null){
                        CanMoveTo = false;
                    }
                }
            }
        }
        //bishop movement
        
        if (CanMoveTo){
            super.moveTo(board,x,y);
        } else{
            board.printError("[ERROR] Impossible Move: Piece type is Rook\n turn was #"+board.getTurn()+"\nColor Piece is Black="+super.getIsBlack()+" target Position was x="+super.getX()+" y="+super.getY());
        }
        
    }
}

