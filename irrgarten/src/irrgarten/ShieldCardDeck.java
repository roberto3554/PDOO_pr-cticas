/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author pedro
 */
public class ShieldCardDeck extends CardDeck<Shield>  {

    protected void addCards(){
        //Va a añadir de 5 en 5
        
        for(int i = 0; i<5; i++){               
            super.addCard(new Shield(Dice.shieldPower(), Dice.usesLeft()));
        }
      
        
    }
    
}
