/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author roberto
 */
public abstract class CombatElement {
    private float effect = (float) 0.0;
    private int uses = 0;
    
    public CombatElement(float effect, int uses){
        if(effect > 0)
            this.effect = effect;
        if(uses > 0)
            this.uses = uses;
    }
    
    protected float produceEffect(){
        float resultado= (float) 0.0;
        if(uses>0){
            uses--;
            resultado=effect;
        }
        return resultado;
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    public String toString(){
        return ("[" + effect + ", " + uses + "]");
    }
    
}
