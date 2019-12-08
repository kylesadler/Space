/*
Kyle Sadler
Programming Paradigms Honors Project
December 9th, 2019
*/


import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.io.*; 

public class View extends JFrame implements ActionListener, Serializable {
    private class MyPanel extends JPanel {
        Controller controller;

        MyPanel(Controller c) {
            controller = c;
            addMouseListener(c);
            addMouseMotionListener(c);
        }

        public void paintComponent(Graphics g) {
            controller.update(g);
            revalidate();
        }
    }

    private class MyMenu extends JMenu 
      implements ActionListener {
      public MyMenu(String text) {
         super(text);
         addActionListener(this);
      }
      public void actionPerformed(ActionEvent e) {
         System.out.println("Menu clicked: ");
      }
   }

    private class MyMenuItem extends JMenuItem
      implements ActionListener {
        Controller controller;
      public MyMenuItem(String text, Controller c) {
         super(text);
         addActionListener(this);
         controller = c;

      }
      public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().compareTo("Pause") == 0){
            controller.pause();
        }else if(e.getActionCommand().compareTo("Save") == 0){
            controller.save();
        }else if(e.getActionCommand().compareTo("Load") == 0){
            controller.load();
        }else if(e.getActionCommand().compareTo("Exit") == 0){
            controller.exit();
        }else if(e.getActionCommand().compareTo("Instructions") == 0){
            controller.instructions();
        }

        //  System.out.println("Menu clicked: "+e.getActionCommand());
      }
   }


    public View(Controller c, int windowWidth, int windowheight) throws Exception{
        setTitle("Space");
        setSize(windowWidth, windowheight);
        getContentPane().add(new MyPanel(c));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        // create a menubar 
        JMenuBar mb = new JMenuBar(); 
  
        // create a menu 
        JMenu Pause = new MyMenu("Pause"); 
        MyMenuItem Pause1 = new MyMenuItem("Pause", c); 
        Pause.add(Pause1);
        JMenu Save = new MyMenu("Save");
        MyMenuItem Save1 = new MyMenuItem("Save", c); 
        Save.add(Save1); 
        JMenu Load = new MyMenu("Load"); 
        MyMenuItem Load1 = new MyMenuItem("Load", c); 
        Load.add(Load1);
        JMenu Exit = new MyMenu("Exit"); 
        MyMenuItem Exit1 = new MyMenuItem("Exit", c); 
        Exit.add(Exit1);
        JMenu Instructions = new MyMenu("Instructions"); 
        MyMenuItem Instructions1 = new MyMenuItem("Instructions", c); 
        Instructions.add(Instructions1);

        mb.add(Pause); 
        mb.add(Save); 
        mb.add(Load); 
        mb.add(Exit); 
        mb.add(Instructions); 
        setJMenuBar(mb); 

        addKeyListener(c);
    }

    public void actionPerformed(ActionEvent evt) {
        repaint();
    }
}
