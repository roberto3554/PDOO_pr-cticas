/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author roberto
 */
public abstract class LabyrinthCharacter {
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    public LabyrinthCharacter(String name, float intelligence, float strength, float health){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
        row = -1;
        col = -1;
    }
    
    public LabyrinthCharacter(LabyrinthCharacter other){
        name = other.name;
        intelligence = other.getIntelligence();
        strength = other.getStrength();
        health = other.getHealth();
        row = other.getRow();
        col = other.getCol();
    }
    
    boolean dead(){
        return (health <= 0);
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    protected float getIntelligence(){
        return intelligence;
    }
    
    protected float getStrength(){
        return strength;
    }
    
    protected float getHealth(){
        return health;
    }
    
    protected void setHealth(float health){
        this.health = health;
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public String toString(){
        return ( name + ", I: " + intelligence + ", S: " + strength + ", H: " + health +
                ", POS: [" +
                + row + "," + col +"]\n");
    }
    
    protected void gotWounded(){
        health--;
    }
    
    public abstract float attack();
    
    public abstract boolean defend(float attack);
}
