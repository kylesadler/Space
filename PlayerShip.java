/*
Kyle Sadler
Programming Paradigms Honors Project
December 9th, 2019
*/
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;


public class PlayerShip extends Spaceship{

    private static double PLAYER_MAX_HEALTH = 100;
    private static double PLAYER_MAX_SPEED = 30;
    private static Color PLAYER_COLOR = Color.red;

    public PlayerShip(double x_in, double y_in, double angle_in){
        super(x_in, y_in, angle_in, PLAYER_MAX_HEALTH, PLAYER_MAX_SPEED, PLAYER_COLOR);
    }

    public void draw(Graphics g, int offsetX, int offsetY){
        super.draw(g, offsetX, offsetY);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("Health: "+(int)this.getHealth(), 20,20);
    }

    public void updateState(ArrayList<Block> blks, boolean player) {
        super.updateState(blks, player);
        if(this.getHealth() < PLAYER_MAX_HEALTH){
          this.setHealth(this.getHealth()+0.07);
        }
    }
}