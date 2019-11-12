
import java.io.IOException;

public class Level1 extends Model{

    public Level1(int h, int w) throws IOException {
        super(h,w);
		super.addPlayer(new PlayerShip(100, 100, 10));
        super.addEnemy(new EnemyShip(200, 200, 10));
        super.addBlast(new PrimaryBlast(100,100,20));
        
    }
}