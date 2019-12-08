import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

public class EnemyShip extends Spaceship{

    private static double ENEMY_MAX_HEALTH = 75;
    private static double ENEMY_MAX_SPEED = 8;
    private static Color ENEMY_COLOR = Color.black;
    private static int ENEMY_FIRE_RATE = 25; // lower is faster
    private static int ENEMY_ALERT_RADIUS = 500;
    private Random random;

    public EnemyShip(double x_in, double y_in, double angle_in){
        super(x_in, y_in, angle_in, ENEMY_MAX_HEALTH, ENEMY_MAX_SPEED, ENEMY_COLOR);
        this.random = new Random();
    }
    
    public int updateState(PlayerShip p) { // 1 if firing primary, 2 secondary, 0 none
        super.updateState(new ArrayList<Block>(), false);
        double xDist = p.getX()-this.getX();
        double yDist = p.getY()-this.getY();
        int distance = (int) Math.sqrt(xDist*xDist + yDist*yDist);
        


        if(distance >= ENEMY_ALERT_RADIUS){return 0;}

        this.setAngle((int) p.getX(), (int) p.getY());
        
        return this.random.nextInt() % ENEMY_FIRE_RATE;

    }
}