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
// need JMenuBar
class Controller implements MouseListener, KeyListener, MouseMotionListener
{
    Level1 model;
    View view;

    Controller() throws IOException, Exception {

        int windowWidth = 1000;
        int windowHeight = 700;

        model = new Level1(windowWidth, windowHeight);
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
        if (e.getKeyChar() == 'q') {
            // powerup 1
            System.out.println("powerup 1");
        } else if (e.getKeyChar() == 'e') {
            // powerup 2
            System.out.println("powerup 2");
        } else if (e.getKeyChar() == 'w') {
            model.moveUp(); 
        } else if (e.getKeyChar() == 'a') {
            model.moveLeft();
        } else if (e.getKeyChar() == 's') {
            model.moveDown();
        } else if (e.getKeyChar() == 'd') {
            model.moveRight();
        } else if (e.getKeyChar() == 'p') {
            model.pause();
        } else if (e.getKeyChar() == 'i') {
            model.instructions();
        }else if (e.getKeyCode() == 27) { // escape
            model.resume();
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
