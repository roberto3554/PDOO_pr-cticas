/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package irrgarten;
import UI.TextUI;


/**
 *
 * @author pedro
 */
public class TestP3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

     
        Game game = new Game (1,true);
        TextUI view = new TextUI();
        boolean end_of_game=false;
        
       for(int i=0;i<20 && !end_of_game;i++){
        view.showGame(game.getGameState());

         end_of_game = game.nextStep(Directions.DOWN)  ; 
       }
       
        
        view.showGame(game.getGameState());
    }
}
