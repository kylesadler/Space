
import java.io.IOException;
import java.awt.Color;

public class Level1 extends Model{

    public Level1(int h, int w) throws IOException {
        super(h,w);
		super.addPlayer(new PlayerShip(100, 100, 10));
        super.addEnemy(new EnemyShip(200, 200, 10));
        super.addEnemy(new EnemyShip(500, 500, 10));
        
        Block b = new Block(Color.red);
        b.addBlock(100,200,300,400);
        super.addBlock(b);
        
    }
}