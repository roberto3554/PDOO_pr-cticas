package controller;

import GUI.GuiUI;
import irrgarten.Directions;
import irrgarten.Game;
import UI.TextUI;
import UI.UI;



public class Controller {
    
    private Game game;
    private GuiUI view;
    
    public Controller(Game game, GuiUI view) {
        this.game = game;
        this.view = view;
    }
    
    public void play() {
        boolean endOfGame = false;
        while (!endOfGame) {
            view.showGame(game.getGameState()); 
            Directions direction = view.nextMove(); 
            // Para test P3
            //Directions direction = Directions.DOWN;
            endOfGame = game.nextStep(direction);
        }
      view.showGame(game.getGameState());        
    }
    
}
