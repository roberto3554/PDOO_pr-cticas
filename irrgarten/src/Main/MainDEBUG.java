/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;
import GUI.GuiUI;
import irrgarten.Game;
import irrgarten.Directions;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author pedro
 */
public class MainDEBUG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
     //Tal cual viene en el  pdf de la profesora
     
     
        Game game = new Game (1,true);
        GuiUI view = new GuiUI();
        boolean end_of_game=false;
        
       //for(int i=0;i<20 && !end_of_game;i++){
        view.showGame(game.getGameState());

        TimeUnit.SECONDS.sleep(5);
         end_of_game = game.nextStep(Directions.DOWN)  ; 
 //      }
       
        
        view.showGame(game.getGameState());
    
    }
    
}
