/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author roberto
 */
public class Dice {
    
    static private final int MAX_USES=5;
    static private final float MAX_INTELLIGENCE=(float) 10.0;
    static private final float MAX_STRENGTH=(float) 10.0;
    static private final float RESURRECT_PROB=(float) 0.3;
    static private final int WEAPONS_REWARD=2;
    static private final int SHIELD_REWARD=3;
    static private final int HEALTH_REWARD=5;
    static private final int MAX_ATTACK=3;
    static private final int MAX_SHIELD=2;
    
    static private Random generator=new Random();
    
    public static int randomPos (int max){
        return generator.nextInt(max);
    }
    
    public static int whoStarts (int nplayers){
        return generator.nextInt(nplayers);
    }
    
    public static float randomIntelligence (){
        return generator.nextFloat()*MAX_INTELLIGENCE;
    }
    
    public static float randomStrength (){
        return generator.nextFloat()*MAX_STRENGTH;
    }
   
    public static boolean resurrectPlayer (){
        if(generator.nextFloat()<RESURRECT_PROB)
            return true;
        else
            return false;     
    }
   
    
    //Sumamos uno para que sea incluido el valor máximo
     public static int weaponReward (){
        return generator.nextInt(WEAPONS_REWARD+1);
    }
      
    public static int shieldsReward (){
        return generator.nextInt(SHIELD_REWARD+1);
    }
    
    public static int healthReward (){
        return generator.nextInt(HEALTH_REWARD+1);
    }
    
    public static float weaponPower (){
        return generator.nextFloat()*MAX_ATTACK;
    }
    
    public static float shieldPower (){
        return generator.nextFloat()*MAX_SHIELD;
    }
    
    //Sumamos uno para que sea incluido el valor máximo
    public static int usesLeft (){
        return generator.nextInt(MAX_USES+1);
    }
    
    public static float intensity (float competence){
        return generator.nextFloat()*competence;
    }
    
    public static boolean discardElement (int usesLeft){
        if(generator.nextFloat()<(float)(usesLeft)/MAX_USES){
            return false;
        }
        else{
            return true;
        }
    }
    
    
    //----------Practica4---------------
    
    
     public static Directions nextStep (Directions preference, ArrayList<Directions> validMoves, float intelligence){
     
         if(generator.nextFloat()<intelligence/10 && validMoves.contains(preference))//el maximo de inteligencia es 10, por eso dividimos entre 10
             return preference;
         else if (validMoves.size() > 0 ){
              
             int pos_aleatoria;
             pos_aleatoria=generator.nextInt(validMoves.size());
             
            //no posiciones nulas
             return validMoves.get(pos_aleatoria);     
         }
         else
             return null;
         
    }
}
