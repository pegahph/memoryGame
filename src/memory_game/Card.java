/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory_game;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;


/**
 *
 * @author npasa
 */
public class Card extends JButton{
    private boolean flag;
    private int value;
    Card(){
        flag = false;
        this.setBackground(Color.LIGHT_GRAY);
        this.setForeground(Color.CYAN);
        this.setFont(new Font("Serif" ,Font.BOLD, 60));           
    }
    void setValue(int r){
        value = r;
    }
    int getValue(){
        return value;
    }
    void setFlag(boolean f1){
        flag = f1;
    }
    boolean getFlag(){
        return flag;
    }
    void Reset(){
        this.setBackground(Color.LIGHT_GRAY);
        flag = false;
        this.setFont(new Font("Serif" ,Font.BOLD, 60));
        this.setText(null);
    }
    void ResetMatch(){
        this.setBackground(Color.LIGHT_GRAY);
        flag = false;        
        this.setText(null);
    }

}
