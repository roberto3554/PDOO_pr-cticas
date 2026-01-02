/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author pedro
 */
public class Player extends LabyrinthCharacter {

    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;

    private WeaponCardDeck weaponCardDeck;
    private ShieldCardDeck shieldCardDeck;

    
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;

   // private String name = "Player #"; Ahora se usa super, lo unico poner Player #
    private char number;
   
    private int consecutiveHits = 0;

    public Player(char number, float intelligence, float strength) {
       super("Player #"+number,intelligence,strength,INITIAL_HEALTH);
        this.number = number;
        //lo podría haber escrito arriba
        weaponCardDeck = new WeaponCardDeck();
        shieldCardDeck = new ShieldCardDeck();
        weapons = new ArrayList<>();
        shields = new ArrayList<>();
    }
    
    public Player(Player other){
       super("Player #"+other.getNumber(),other.getIntelligence(),other.getStrength(),other.getHealth());
       this.number = other.getNumber();
       this.setPos(other.getRow(), other.getCol());
       weapons=other.weapons;
       shields=other.shields;
       /*Faltaria copiar armas y escudos, pero no tengo ni idea)
       */
       
    }
    
    public void resurrect() {

        shields.clear();
        weapons.clear();
        super.setHealth(INITIAL_HEALTH);
        this.resetHits();
        
    }

    public char getNumber() {
        return number;
    }


    @Override
    public float attack() {
        return super.getStrength() + this.sumWeapons();
    }
    
    @Override
    public boolean defend(float recievedAttack) {
        return manageHit(recievedAttack);
    }

    @Override
    public String toString() {
        String cadena = super.toString();
         cadena += "Armas: "+ weapons.toString()+"\n";
         cadena += "Escudos: "+shields.toString() +"\n";
        return cadena;
    }

    //Creacion de clase
    private Weapon newWeapon() {
        //if(weapons.size()<MAX_WEAPONS){
        // weapons.add(arma);
        //}
       return new Weapon(Dice.weaponPower(), Dice.usesLeft());
     
    }

    private Shield newShield() {
        //if(shields.size()<MAX_SHIELDS){
        //  shields.add(escudo);
        //}

        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }

    //Inteligencia ahora protected
    protected float defensiveEnergy() {
        return super.getIntelligence() + this.sumShields();
    }

    // Faciles
    private void incConsecutiveHits() {
        consecutiveHits = consecutiveHits + 1;
    }

    private void resetHits() {
        consecutiveHits = 0;
    }

    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        int size = validMoves.size();
        Boolean contained = validMoves.contains(direction);
        if (size > 0 && !contained){
            Directions firstElement = validMoves.get(0);
            return firstElement;
        }
        else{
            return direction;
        }
    }

    public void recieveReward() {
        int wReward = Dice.weaponReward();
        int sReward = Dice.shieldsReward();

        for (int i = 0; i < wReward; i++) {

            Weapon wnew = weaponCardDeck.nextCard();
            recieveWeapon(wnew);

        }

        for (int i = 0; i < sReward; i++) {

            Shield snew = shieldCardDeck.nextCard();
            recieveShield(snew);

        }

        int extraHealth = Dice.healthReward();
        super.setHealth(super.getHealth()+extraHealth); 

    }

    private void recieveWeapon(Weapon w) {
        
        //Usando iteradores
        Iterator<Weapon> itr = weapons.iterator();
        while(itr.hasNext()){
            Weapon wi = itr.next();
            boolean discard =wi.discard();
            if(discard)
                //usa el iterador
                itr.remove();
            
        }
        
        int size=weapons.size();
        
        if(size<MAX_WEAPONS)
            weapons.add(w);
 
    }

    private void recieveShield(Shield s) {
        //Usando iteradores
        Iterator<Shield> itr = shields.iterator();
        while(itr.hasNext()){
            Shield si = itr.next();
            boolean discard =si.discard();
            if(discard)
                //no se usa el iterador para borrar aquí
                itr.remove();
        }

        int size = shields.size();
        if (size<MAX_SHIELDS){
            shields.add(s);
        }
    }

    protected float sumWeapons() {

        float sumWeapons = (float) 0.0;
        for (int i = 0; i < weapons.size(); i++) {
            sumWeapons = sumWeapons + weapons.get(i).attack();
        }

        return sumWeapons;
    }

    protected float sumShields() {

        float sumShields = (float) 0.0;
        for (int i = 0; i < shields.size(); i++) {
            sumShields = sumShields + shields.get(i).protect();
        }

        return sumShields;
    }

    private boolean manageHit(float recievedAttack) {
        
        boolean lose;
        float defense = defensiveEnergy();

        if (defense < recievedAttack) {
            gotWounded();
            incConsecutiveHits();
        } else {
            resetHits();
        }

        if ((consecutiveHits == HITS2LOSE) || dead()) {
            resetHits();
            //health=0;//se supone que muere
            lose = true;
        } else {
            lose = false;
        }
        
        return lose;
    }

}
