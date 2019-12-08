
import java.io.IOException;
import java.awt.Color;
import java.io.*; 

public class Menu extends Model{

    public Menu(int h, int w) {
        super(h,w);
        super.addPlayer(new PlayerShip(100, 100, 10));
        super.addEnemy(new EnemyShip(200, 200, 10));
        
    }
}