/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;

/**
 *
 * @author pedro
 * 
 */

public class Game {
    
    private static final int MAX_ROUNDS=10;
    private int currentPlayerIndex;
    private String log;
    
    private ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Labyrinth labyrinth;
    
   private Player currentPlayer;
    
       public Game(int nplayers, boolean modoDebug) {
        if (!modoDebug) {
            currentPlayerIndex = Dice.whoStarts(nplayers);
            log = "";

            for (int i = 0; i < nplayers; i++) {
                Player aux = new Player((char) (i + '0'), Dice.randomIntelligence(), Dice.randomStrength());
                players.add(i, aux);
            }

            currentPlayer = players.get(currentPlayerIndex);
            labyrinth= new Labyrinth(6,6,Dice.randomPos(5),Dice.randomPos(5));
            this.configureLabyrinth();
            labyrinth.spreadPlayers(players,modoDebug);
        }
        
        else{
            
            currentPlayerIndex= 0;
            log = "";
             Player aux = new Player((char) (0 + '0'), Dice.randomIntelligence(), Dice.randomStrength());
            players.add(0, aux);
            labyrinth= new Labyrinth(3,3,2,2);
            this.configureLabyrinthDEBUG();
            labyrinth.spreadPlayers(players,modoDebug);
            currentPlayer = players.get(currentPlayerIndex);
            
            
        }

    }
    
    
    public boolean finished(){
        
        return labyrinth.haveAWinner();
    }
    
    public GameState getGameState(){
       
      return new GameState (labyrinth.toString(), players.toString(), monsters.toString(), currentPlayerIndex, finished(), log);
    }
    
    public boolean nextStep(Directions preferredDirection){
        log="";
        Boolean dead = currentPlayer.dead();
        if(!dead){
            Directions direction = actualDirection(preferredDirection);
            if(direction != preferredDirection){
                this.logPlayerNoOrders();
            }
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            if(monster == null){
                this.logNoMonster();
            }
            else{
                GameCharacter winner = this.combat(monster);
                this.manageReward(winner);
            }
        }
        else{
            this.manageResurrection();
        }
        Boolean endGame = this.finished();
        if(!endGame){
            this.nextPlayer();
        }
        return endGame;
    }
    
    private void configureLabyrinthDEBUG(){
        
        Monster monster2 = new Monster("Monstruo2",(float)10000000,(float)10000000);
        
        labyrinth.addMonster(1, 0, monster2);
        monster2.setPos(1, 0);
        monsters.add(monster2);
        
        
        
    }
    
    private void configureLabyrinth() {

        int posicionAleatoria[] = labyrinth.randomEmptyPos();

        labyrinth.addBlock(Orientation.HORIZONTAL, posicionAleatoria[0], posicionAleatoria[1], 5);
        posicionAleatoria = labyrinth.randomEmptyPos();
        labyrinth.addBlock(Orientation.VERTICAL, posicionAleatoria[0], posicionAleatoria[1], 5);
        posicionAleatoria = labyrinth.randomEmptyPos();
        labyrinth.addBlock(Orientation.VERTICAL, posicionAleatoria[0], posicionAleatoria[1], 3);

        Monster monster1 = new Monster("Monstruo1", Dice.randomIntelligence(), Dice.randomStrength());
        int posicion[] = labyrinth.randomEmptyPos();
        labyrinth.addMonster(posicion[0], posicion[1], monster1);
        monster1.setPos(posicion[0], posicion[1]);
        monsters.add(monster1);

        Monster monster2 = new Monster("Monstruo2", Dice.randomIntelligence(), Dice.randomStrength());
        int posicion2[] = labyrinth.randomEmptyPos();
        labyrinth.addMonster(posicion2[0], posicion2[1], monster2);
        monster2.setPos(posicion2[0], posicion2[1]);
        monsters.add(monster2);

    }
    
    private void nextPlayer(){
       //miratelo
       
       currentPlayerIndex=(currentPlayerIndex+1)%players.size();
       currentPlayer = players.get(currentPlayerIndex);
       
    }
    
    private void logPlayerWon(){
         log+="El jugador " + currentPlayer+ " ha ganado el combate\n";
    }
    private void logMonsterWon(){
         log+="El monstruo ha ganado el combate\n";
    }
    
    private void logResurrected(){
         log+="El jugador: " + currentPlayer+ " ha resucitado\n";
    }
    
    private void logPlayerSkipTurn(){
         log+="El jugador: " + currentPlayer+ " ha perdido el turno por estar muerto\n";
    }
    
    private void logPlayerNoOrders(){
         log+="El jugador: " + currentPlayer+ " no ha seguido las instrucciones del jugador humano(no fue posible)\n";
    }
    
    private void logNoMonster(){
         log+="El jugador: " + currentPlayer+ " se ha movido a una celda vacía o no le ha sido posible moverse\n";
    }
    
    private void logRounds(int rounds, int max){
         log+=" Se han producido "+ rounds + " de "+ max+  " rondas de combate\n";
    }
    
    
    //LO EXTRA
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
       
        ArrayList<Directions> validMoves = new ArrayList();
        validMoves = labyrinth.validMoves(currentRow, currentCol);
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        return output;
    }
    
    private GameCharacter combat(Monster monster){
        
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        
        while(!lose && rounds < MAX_ROUNDS){
            
            winner = GameCharacter.MONSTER;
            rounds ++;
            float monsterAttack=monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            
            if (!lose){
                playerAttack = currentPlayer.attack();
                 winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
               
            }
        }
        
        logRounds(rounds,MAX_ROUNDS);
        
        if(winner==GameCharacter.MONSTER)
            logMonsterWon();
        else
            logPlayerWon();
        
        return winner;    
            
    }
        
    private void manageReward(GameCharacter winner){
        if(winner==GameCharacter.PLAYER){
            currentPlayer.recieveReward();
            
            
        }
    }
    private void manageResurrection(){
        Boolean resurrect = Dice.resurrectPlayer();
        if(resurrect){
            currentPlayer.resurrect();
            //Aquí se crea el fuzzy player:
            this.currentPlayer = new FuzzyPlayer(this.currentPlayer);
            players.set(currentPlayerIndex, this.currentPlayer);
            
            this.logResurrected();
        }
        else{
            this.logPlayerSkipTurn();
        }
    }
    
    
    
    
    
    
}


