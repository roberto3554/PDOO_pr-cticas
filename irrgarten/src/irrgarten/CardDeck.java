/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author pedro
 * 
 * 
 */
public abstract class CardDeck <T>{
    
    private ArrayList< T >  cardDeck;
   
    
    //-----------------Métodos--------------
    
    public CardDeck(){
        cardDeck = new ArrayList< T > ();
    }
    
    protected void addCard(T objeto){
        cardDeck.add(objeto);
    }
    
    protected abstract void addCards();
    
    public T nextCard(){
        
        if(cardDeck.isEmpty()){
            addCards();
            Collections.shuffle(cardDeck); //barajado
        }
        
        T objeto = cardDeck.get(0); //cogemos primer elemento
        cardDeck.remove(objeto);
        return objeto;
    }
 
}
