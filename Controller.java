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

class Controller implements MouseListener, KeyListener, MouseMotionListener
{
    Model model;
    View view;

    Controller() throws IOException, Exception {
        model = new Model();
        view = new View(this);
        new Timer(50, view).start();
    }

    public void update(Graphics g) {
        model.updateImage(g);
        model.updateState();
    }

    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            // player.firePrimary();
            model.firePrimary();
            System.out.println("firePrimary");
  		} else if (SwingUtilities.isRightMouseButton(e))  {
              // model.fireSecondary();
              model.fireSecondary();
              System.out.println("fire secondary");
	    }
    }

    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'p') {
            model.pause(); // pause

        } else if (e.getKeyChar() == 'i') {
            // pause and instructions
        }
	}

    public void keyReleased   (KeyEvent e){}
    public void keyPressed    (KeyEvent e){
        if (e.getKeyChar() == 'q') {
            // powerup 1
            System.out.println("powerup 1");
        } else if (e.getKeyChar() == 'e') {
            // powerup 2
            System.out.println("powerup 2");
        } else if (e.getKeyChar() == 'w') {
            // move up
            model.moveUp(); // System.out.println("move up");
        } else if (e.getKeyChar() == 'a') {
            // move left
            model.moveLeft(); // System.out.println("move left");
        } else if (e.getKeyChar() == 's') {
            // move down
            model.moveDown(); // System.out.println("move down");
        } else if (e.getKeyChar() == 'd') {
            // move right
            model.moveRight(); // System.out.println("move right");
        }
    }
    public void mouseReleased (MouseEvent e) {}
    public void mouseEntered  (MouseEvent e) {}
    public void mouseExited   (MouseEvent e) {}
    public void mouseClicked  (MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {model.setAngle(e.getX(), e.getY());}
    public void mouseDragged(MouseEvent e) {model.setAngle(e.getX(), e.getY());}


    public static void main(String[] args) throws Exception {
        //  Use the following line to determine which directory your program
        //  is being executed from, since that is where the image files will
        //  need to be.
        //System.out.println("cwd=" + System.getProperty("user.dir"));
        new Controller();
    }
}
