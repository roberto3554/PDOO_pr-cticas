/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class FuzzyPlayer extends Player{
    
    public FuzzyPlayer(Player other){        
        super(other);      
    }
    
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        return Dice.nextStep(direction, validMoves, super.getIntelligence());
    }
    
    @Override
    public float attack(){
        return Dice.intensity(super.getStrength()) + super.sumWeapons();

        
    }
    
    @Override
    public String toString(){
        String cadena = "Fuzzy" + super.toString();
        return cadena;       
    }
    
    @Override
    public float  defensiveEnergy(){
    return Dice.intensity(super.getIntelligence()) + super.sumShields();   
    }
    
    
    
}
