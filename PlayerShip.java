import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;


public class PlayerShip extends Spaceship{

    private static double PLAYER_MAX_HEALTH = 100;
    private static double PLAYER_MAX_SPEED = 16;
    private static Color PLAYER_COLOR = Color.red;

    public PlayerShip(double x_in, double y_in, double angle_in){
        super(x_in, y_in, angle_in, PLAYER_MAX_HEALTH, PLAYER_MAX_SPEED, PLAYER_COLOR);
    }

    public void draw(Graphics g, int offsetX, int offsetY){
        super.draw(g, offsetX, offsetY);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("Health: "+this.getHealth(), 20,20);
    }
}