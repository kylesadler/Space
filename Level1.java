
import java.io.IOException;
import java.awt.Color;

public class Level1 extends Model{

    public Level1(int h, int w) throws IOException {
        super(h,w);
		super.addPlayer(new PlayerShip(100, 100, 10));
        super.addEnemy(new EnemyShip(200, 200, 10));
        super.addEnemy(new EnemyShip(500, 500, 10));
        
        Block b = new Block(Color.red);
        b.addBlock(0,-100,h,0);
        b.addBlock(0,w,h,w+100);
        b.addBlock(-100,0,0,w);
        b.addBlock(h,0,h+100,w);
        super.addBlock(b);
    }
}