/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;
/**
 *
 * @author roberto
 * 
 *
 */
public class Labyrinth {
    
    //-----------------------Variables-----------------------
    
    static private final char BLOCK_CHAR='X';
    static private final char EMPTY_CHAR='-';
    static private final char MONSTER_CHAR='M';
    static private final char COMBAT_CHAR='C';
    static private final char EXIT_CHAR='E';
    static private final int ROW=0;
    static private final int COL=1;
    
    private int nRow;
    private int nCol;
    private int exitRow;
    private int exitCol;
    
    static private Monster[][] monsters;
    static private char[][] labyrinth;
    static private Player[][] players;
  
    //-----------------------Constructor-----------------------
    
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        nRow=nRows;
        nCol=nCols;
        this.exitRow=exitRow;
        this.exitCol=exitCol;
        
        labyrinth= new char[nRows][nCols];
        monsters = new Monster[nRows][nCols];
        players = new Player[nRows][nCols];
        
        for(int i = 0; i< nRows; i++){
            for(int j = 0; j< nCols; j++){
                set(i,j,EMPTY_CHAR); //Relleno el tablero
            }
        }
        set(exitRow,exitCol,EXIT_CHAR);
    }
    
    //-----------------------Metodos-----------------------
    
    
    //esparce jugadores por el mapa
    //------------MODO DEBUUUUG------------
    
    public void spreadPlayers(ArrayList<Player> players, boolean debug){
        if (!debug) {
            for (int i = 0; i < players.size(); i++) {
                Player p = players.get(i);
                int[] pos = this.randomEmptyPos();
                this.putPlayer2D(-1, -1, pos[ROW], pos[COL], p);

            }
        } else {
            
        
        Player p = players.get(0);
        this.putPlayer2D(-1, -1, 0, 0, p);
        }
    }
    
    
    //Hay ganador
    public boolean haveAWinner(){
        return(labyrinth[exitRow][exitCol]!=EXIT_CHAR); 
    //Es decir, ya se ha puesto un jugador en la casilla
      
    }
    
    // este to_string debería ser el tablero
    public String toString(){
        String aux="Tablero de juego: \n";
        for(int i=0; i<nRow; i++){
            for(int j = 0; j<nCol; j++){                
                aux += labyrinth[i][j] + "\t";
            }
             aux += "\n";
        }
        return aux;
    }
    //Y si hacemos que delegue en Game?, asi nos será más cómodo
    public void addMonster(int row, int col, Monster monster){
        if(posOK(row, col) && emptyPos(row, col)){
            monster.setPos(row, col);
            monsters[row][col]=monster;           
            labyrinth[row][col]= MONSTER_CHAR;
        }
      
    }
    
    //colocar jugador
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int[] newPos = this.dir2Pos(oldRow, oldCol, direction);
        Monster monster = this.putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        return monster;
    }
    
    //Añadir barreras
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        int incRow, incCol;
        
        if(orientation==Orientation.VERTICAL){
            incRow=1;
            incCol=0;
            
        }
        else{
            incRow=0;
            incCol=1;
        }
        
        int row=startRow;
        int col=startCol;
        
        
        while(posOK(row, col) && emptyPos(row, col) && length>0){
            //labyrinth[row][col]= BLOCK_CHAR; 
            set(row,col,BLOCK_CHAR);
            length--;
            row+=incRow;
            col+=incCol;
            
        }
    }
    
    private void set(int row,int col,char c){
            if(posOK(row,col))
                labyrinth[row][col]= c;
        
    }
    
    
    
    //Revisar porque salta cosa en rojo
    
    public ArrayList<Directions> validMoves(int row, int col){
      ArrayList<Directions> output = new ArrayList();
       
        if(canStepOn(row+1,col))
           output.add(Directions.DOWN);
       
        if(canStepOn(row-1,col))
           output.add(Directions.UP);
        
        if(canStepOn(row,col+1))
           output.add(Directions.RIGHT);
        
        if(canStepOn(row,col-1))
           output.add(Directions.LEFT);
                   
       
       return output;
       
   
    }
    
    
    //controlo bordes
    private boolean posOK(int row, int col){
        return(row>=0 && row<nRow && col >= 0 && col<nCol);
    }
    
    
    
    private boolean emptyPos(int row, int col){
        return(labyrinth[row][col]==EMPTY_CHAR);
    }
    
    private boolean monsterPos(int row, int col){
        return(labyrinth[row][col]==MONSTER_CHAR);
    }
    
    private boolean exitPos(int row, int col){
        return(labyrinth[row][col]==EXIT_CHAR);
    }
    
    private boolean combatPos(int row, int col){
        return(labyrinth[row][col]==COMBAT_CHAR);
    }
    
    //comprueba si se puede acceder
    private boolean canStepOn(int row, int col){
        return(posOK(row, col) && (emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col)));
    }
    
    //actuañiza la posicion anterior
    private void updateOldPos(int row, int col){
        if(posOK(row, col)){
            if(combatPos(row,col)){
                set(row,col,MONSTER_CHAR);
            }
            else{
                set(row,col,EMPTY_CHAR);
            }
        }
    }
    
    private int[] dir2Pos(int row, int col, Directions direction){
        int[] result={row, col};
        switch(direction){
            case UP: //decremento una fila, misma columna
                result[ROW]--;
            break;
            case LEFT: //decremento una columna, misma fila
                result[COL]--;
            break;
            case DOWN: //incremento una fila, misma columna
                result[ROW]++;
            break;
            case RIGHT: //incremento una columna, misma fila
                result[COL]++;
            break;
        }
        return result;
    }
    
    
    //debe ser publico para poder inicializar el laberinto usando el metodo comfigure labyrinth
    //--------------------VISIBILIDAD CAMBIADA!!----------------------
    //Aun queda ver spread players a ver que tal
    public int[] randomEmptyPos(){
        int[] position={-1,-1};
             position[ROW]=Dice.randomPos(nRow);
            position[COL]=Dice.randomPos(nCol);
            //no compruebes que sea pos ok
        while(!emptyPos(position[ROW], position[COL])){
            position[ROW]=Dice.randomPos(nRow);
            position[COL]=Dice.randomPos(nCol);
        }
        return position;
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        
        Monster output = null;
        
        if(canStepOn(row,col)){
            
            if(posOK(oldRow,oldCol)){
                
                Player p= players[oldRow][oldCol];
                
                if(p.getNumber()==player.getNumber() ){
                    updateOldPos(oldRow,oldCol);                 
                }
            
                players[oldRow][oldCol]=null;
                
            }
         
             boolean monsterPos = monsterPos(row,col);
         
            if(monsterPos){            
                 set(row,col,COMBAT_CHAR);
              output=monsters[row][col];
             }
         
             else{            
                char number = player.getNumber();
               set(row,col,number);    
              }
        
            players[row][col]=player;
            player.setPos(row, col);
        
        }
        
        return output;
    }
}
