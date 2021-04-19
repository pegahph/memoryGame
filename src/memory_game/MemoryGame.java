/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory_game;

import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author npasa
 */
public class MemoryGame extends JFrame{
    private final SecureRandom generator = new SecureRandom();
    private int random;
    private JLabel name;
    private JLabel name2;
    private JLabel name3;
    private final JLabel num;
    private final JButton more;
    private final JButton less;
    private final JLabel font;
    private final JButton larg;
    private final JButton small;
    private final JLabel move;
    private final JButton reset;
    private final JPanel container;
    private final ArrayList<Card> cards;   
    private final ArrayList<Card> match = new ArrayList<>();
    private int numcard =24;
    private int f =60;
    private int numMoves =0;
    private int tempMove =0;
    private final ButtonHandler handler = new ButtonHandler();
    private final ArrayList<Integer> number = new ArrayList<>();
    private GridLayout layout = new GridLayout(5,5);
    private final JTextField mouse;
    MemoryGame(){
        super("Welcome to Memory Game!");
        setLayout(null);
        mouseHandler m = new mouseHandler();
        getContentPane().setBackground(white);
        name = new JLabel("Mememory");
        name.setFont(new Font("serif" , Font.BOLD + Font.ITALIC, 35));
        name.setBounds(10, 280, 400, 300); 
        name.setForeground(Color.CYAN);
        name2 = new JLabel("Game");
        name2.setFont(new Font("serif" , Font.BOLD + Font.ITALIC, 35));
        name2.setBounds(40, 320, 400, 300);
        name2.setForeground(Color.CYAN);
        add(name);
        add(name2);
        name3 = new JLabel();
        name3.setBounds(5, 368, 185, 170);
        name3.setOpaque(true);
        name3.setBackground(Color.black);
        name3.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue));
        add(name3);
        mouse = new JTextField(" Mouse: ");        
        mouse.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        mouse.setBounds(18, 580, 160, 25);        
        mouse.setEditable(false);
        add(mouse);
        num = new JLabel(" Num Cards = "+numcard);
        num.setBounds(25, 20, 130, 30);
        num.setFont(new Font("Serif" ,Font.BOLD, 15));
        num.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.cyan));
        add(num);
        more = new JButton("More Cards");
        more.setBackground(Color.CYAN);
        more.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        more.setBounds(30, 60, 110, 30);
        less = new JButton("Less Cards");
        less.setBackground(Color.CYAN);
        less.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        less.setBounds(30, 100, 110, 30);
        add(more);
        add(less);
        font = new JLabel(" Font Size = "+f);
        font.setFont(new Font("Serif" ,Font.BOLD, 15));        
        font.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.cyan));
        font.setBounds(25, 140, 130, 30);
        add(font);
        larg = new JButton("Larger Font");
        larg.setBounds(30, 180, 110, 30);
        larg.setBackground(Color.CYAN);
        larg.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        small = new JButton("Smaller Font");
        small.setBounds(30, 220, 110, 30);
        small.setBackground(Color.CYAN);
        small.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        add(larg);
        add(small);
        move = new JLabel(" Moves = "+numMoves);
        move.setFont(new Font("Serif" ,Font.BOLD, 15));
        move.setBounds(25, 260, 130, 30);
        move.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.cyan));
        add(move);
        reset = new JButton("Reset");
        reset.setBackground(Color.CYAN);
        reset.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        reset.setBounds(30, 300, 110, 30);
        add(reset);    
        container = new JPanel();
        container.setOpaque(true);
        container.setBackground(Color.black);
        container.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.blue));
        container.setBounds(200, 20, 506, 621);
        container.setLayout(layout);
        add(container);
        cards = new ArrayList<>();
   
        for(int i=0 ; i<numcard ; i++){
            Card e = new Card();            
            cards.add(e);
            container.add(e);
            e.addMouseListener(m);
            e.addActionListener(handler);
        }   
        shuffle();
        
        less.addActionListener(handler);
        larg.addActionListener(handler);
        small.addActionListener(handler);
        more.addActionListener(handler);
        reset.addActionListener(handler);
    }
    void shuffle(){    
        number.clear();
        random = generator.nextInt(10);        
        for(int i=0 ; i < numcard ; i+=2){           
            number.add(random);
            number.add(random);
            random = generator.nextInt(10);             
        }        
         java.util.Collections.shuffle(number);    
         for(int i=0 ; i<numcard ; i++)
             cards.get(i).setValue(number.get(i));        
    }
    void less(int Num){
        if(numcard<50){
            layout = new GridLayout(5,5);
            container.setLayout(layout);
        }
        for(Card i : cards){
            if(i.getFlag())
                i.ResetMatch();
        }          
        for(int i= Num-1 ; i>Num-3 ; i--){
            cards.remove(cards.get(i));  
            container.remove(i);
        }        
        shuffle();
    }
    void more(){
        if(numcard > 50){
            layout = new GridLayout(8,5);
            container.setLayout(layout);
        }
        for(Card i : cards){
            if(i.getFlag())
                i.ResetMatch();
        }            
        for(int i=0 ; i<2 ; i++){
            Card temp = new Card();
            temp.setFont(new Font("Serif" ,Font.BOLD, f));
            temp.addActionListener(handler);            
            cards.add(temp);
            container.add(temp);
        }       
        shuffle();
    }
    void match(ArrayList<Card> x){
        if(x.get(0).getValue() != x.get(1).getValue()){
            x.get(0).ResetMatch();
            x.get(1).ResetMatch();
        }
        x.clear();
    }
    
    void win(){
        int c=0;
        for(Card i : cards){
            if(i.getFlag())
                c++;
        }
        if(c == numcard)
            JOptionPane.showMessageDialog(null, String.format("Congratulation!\nYou Win in %d Moves!", numMoves));
    }
  
    
    private class ButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == larg){
                if(f<132){
                    f+=8;
                    font.setText(" Font Size = "+f);
                    for(Card i : cards){
                        i.setFont(new Font("Serif" ,Font.BOLD, f));
                    }
                }
            }
            if(e.getSource() == small){
                if(f>6){
                    f-=8;
                    font.setText(" Font Size = "+f);
                    for(Card i : cards){
                        i.setFont(new Font("Serif" ,Font.BOLD, f));
                    }
                }
            }
            if(e.getSource() == reset){
                number.clear();
                mouse.setText(" Mouse :");
                layout = new GridLayout(5,5);
                container.setLayout(layout);
                if(numcard>24){
                    while(numcard!=24){
                        numcard-=2;
                        less(numcard);
                    }
                }
                if(numcard<24){
                    while(numcard!=24){
                        numcard+=2;
                        more();
                    }
                }
                numMoves = 0;
                tempMove =0;
                match.clear();
                move.setText(" Moves = "+numMoves);
                num.setText(" Num Cards = "+numcard);
                for(Card i : cards){               
                    i.Reset();                    
                }
                shuffle();
                f=60;
                font.setText(" Font Size = "+f);
            }
            if(e.getSource() == more){
                match.clear();
                numcard+=2;
                more();
                tempMove =0;
                numMoves =0;
                num.setText(" Num Cards = "+numcard);
            }
            if(e.getSource() == less){
                if(numcard>4){
                    numcard-=2;
                    match.clear();
                    less(numcard);
                    tempMove =0;
                    numMoves =0;
                    num.setText(" Num Cards = "+numcard);
                }
            }
         
            
            else{                 
                for(Card i : cards){                    
                    if(e.getSource()== i){
                        if(!i.getFlag()){
                            tempMove ++;                            
                        }
                        if(tempMove%2 == 0 && !i.getFlag()){
                            numMoves++;
                            move.setText(" Moves = "+numMoves);                             
                        }
                        else{
                            if(tempMove!=1 && !i.getFlag())
                                match(match);                            
                        }
                        if(!i.getFlag())
                            match.add(i);
                        i.setBackground(Color.BLACK);
                        i.setFlag(true);                        
                        i.setText(Integer.toString(i.getValue()));                        
                        win();
                        break;
                    }
                }
                
            }
                
        }      
    }    

    private class mouseHandler implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            mouse.setText(" Mouse : Click "+e.getXOnScreen()+" , "+ e.getYOnScreen());           
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
           
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
           
        }

        
    }
} 
