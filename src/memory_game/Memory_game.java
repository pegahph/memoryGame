/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory_game;

import javax.swing.JFrame;

/**
 *
 * @author npasa
 */
public class Memory_game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MemoryGame player = new MemoryGame();
        player.setVisible(true);
        player.setSize(750, 700);
        player.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
