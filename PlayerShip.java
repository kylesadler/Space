import java.awt.Graphics;
import java.awt.Color;


public class PlayerShip extends Spaceship{

    private static double PLAYER_MAX_HEALTH = 100;
    private static double PLAYER_MAX_SPEED = 10;
    private static Color PLAYER_COLOR = Color.red;

    public PlayerShip(double x_in, double y_in, double angle_in){
        super(x_in, y_in, angle_in, PLAYER_MAX_HEALTH, PLAYER_MAX_SPEED, PLAYER_COLOR);
    }

    public void draw(Graphics g){
        super.draw(g);
    }
}