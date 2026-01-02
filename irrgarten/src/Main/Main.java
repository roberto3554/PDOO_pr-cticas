/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import GUI.GuiUI;
import irrgarten.Game;
import controller.Controller;
import java.util.Scanner;
/**
 *
 * @author pedro
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        System.out.println("Bienvenido al divertidísimo juego Irrgarten: \n");
        System.out.println("Selecciona el número de jugadores: \n");
        
        
        //Introduce un numero
         int nplayers=in.nextInt();
         System.out.println("Elegiste jugar con: " + nplayers + " jugadores. Ahora toca jugar. \n");
       
     
        Game game = new Game (nplayers,false);
        
        //Aquí habría que cambiarlo??
        //Preguntarselo a la teacher
        GuiUI view = new GuiUI();
        
        Controller Controlador = new Controller(game, view);
        
        Controlador.play();
        
        
       
        
    }
    
}
