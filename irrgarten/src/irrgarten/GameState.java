/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author roberto
 */
public class GameState {
    
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;
    
    GameState(String labS, String playS, String monsS, int cP, boolean w, String l){
        labyrinth=labS;
        players=playS;
        monsters=monsS;
        currentPlayer=cP;
        winner=w;
        log=l;
    }
    
    public String getPlayers(){
        return players;
    }
   
    public String getMonsters(){
        return monsters;
    }
   
    public String getLabyrinth(){
        return labyrinth;
    }
   
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    public boolean getWinner(){
        return winner;
    }
    public String getLog(){
        return log;
    }
    
}

