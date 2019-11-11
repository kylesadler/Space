import java.awt.Color;

public class EnemyShip extends Spaceship{

    private static double ENEMY_MAX_HEALTH = 75;
    private static double ENEMY_MAX_SPEED = 8;
    private static Color ENEMY_COLOR = Color.black;

    public EnemyShip(double x_in, double y_in, double angle_in){
        super(x_in, y_in, angle_in, ENEMY_MAX_HEALTH, ENEMY_MAX_SPEED, ENEMY_COLOR);
    }
}