/*
Kyle Sadler
Programming Paradigms Assignment 5
October 18th, 2019
*/
import java.awt.Graphics;
import java.io.IOException;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import java.io.*; 


// need JMenuBar
class Controller implements MouseListener, KeyListener, MouseMotionListener, Serializable{
    private Model model; // current game screen
    private View view;
    private int windowWidth = 1000;
    private int windowHeight = 700;
    private int currentLevel = 0;


    Controller() throws IOException, Exception {

        model = (Model) (new Menu(windowWidth, windowHeight));
        // model = (Model) (new Level1(windowWidth, windowHeight));
        view = new View(this, windowWidth, windowHeight);
        new Timer(50, view).start();
    }

    public void update(Graphics g) {
        model.updateImage(g);
        model.updateState();
    }

    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            model.firePrimary();
  		} else if (SwingUtilities.isRightMouseButton(e))  {
            model.fireSecondary();
	    }
    }

    public void mouseMoved(MouseEvent e) {model.setAngle(e.getX(), e.getY());}
    public void mouseDragged(MouseEvent e) {model.setAngle(e.getX(), e.getY());}

    public void keyPressed    (KeyEvent e){
        if (e.getKeyChar() == 'w') {
            model.moveUp(); 
        } else if (e.getKeyChar() == 'a') {
            model.moveLeft();
        } else if (e.getKeyChar() == 's') {
            model.moveDown();
        } else if (e.getKeyChar() == 'd') {
            model.moveRight();
        } else if (e.getKeyChar() == 'p') {
            pause();
        } else if (e.getKeyChar() == 'i') {
            instructions();
        } else if (e.getKeyCode() == 27) { // escape
            model.resume();
        } else if (e.getKeyCode() == 32) { // space to coninue
            if(this.model.isGameWon()){
              this.currentLevel++;
              changeLevel(this.currentLevel);
            }
        } else if (e.getKeyChar() == 'q') { // space
            if(this.model.isGameOver() || this.model.isGamePaused()){
              changeLevel(0);
            }
        }
        
       
    }
    public void instructions(){
        model.instructions();
    }
    public void exit(){
        System.exit(0);
    }
    public void load(){
      try {
          FileInputStream file = new FileInputStream("saved_game.ser");
          ObjectInputStream inStream = new ObjectInputStream(file);
          Controller c = (Controller) inStream.readObject();
          this.model = c.model;
          this.view = c.view;
          
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
    public void save(){
        // save to saved_game.ser
        String filename = "saved_game.ser"; 
        try
        {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(this); 
              
            out.close(); 
            file.close(); 
              
            // System.out.println("Object has been serialized"); 
  
        } 
          
        catch(IOException e) 
        { 
            System.out.println("failed to save"); 
            e.printStackTrace();
        } 
    }
    public void pause(){
        model.pause();
    }
    public void changeLevel(int lvl){
      if(lvl == 1){
        model = (Model) (new Level1(this.windowWidth, this.windowHeight));
      } else if(lvl == 2){
        model = (Model) (new Level2(this.windowWidth, this.windowHeight));
      } else if(lvl == 3){
        model = (Model) (new Level3(this.windowWidth, this.windowHeight));
      } else {
        model = (Model) (new Menu(this.windowWidth, this.windowHeight));
      }
    }


    public void keyTyped(KeyEvent e) {}
    public void keyReleased   (KeyEvent e){}
    public void mouseReleased (MouseEvent e) {}
    public void mouseEntered  (MouseEvent e) {}
    public void mouseExited   (MouseEvent e) {}
    public void mouseClicked  (MouseEvent e) {}
    


    public static void main(String[] args) throws Exception {
        //  Use the following line to determine which directory your program
        //  is being executed from, since that is where the image files will
        //  need to be.
        //System.out.println("cwd=" + System.getProperty("user.dir"));
        new Controller();
    }
}
