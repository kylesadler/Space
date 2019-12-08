
/*
Kyle Sadler
Programming Paradigms Honors Project
December 9th, 2019
*/
import java.io.IOException;
import java.awt.Color;

public class Level1 extends Model {

    public Level1(int h, int w) {
        super(h,w, false);
		    super.addPlayer(new PlayerShip(100, 100, 10));

        int wallWidth = 50;

        Block b = new Block(Color.red);

        // entry hallway
        b.addBlock(250,225,250+wallWidth,500);
        b.addBlock(250,225,1000,225+wallWidth);
        b.addBlock(250,500,1000,500+wallWidth);

        // big room
        int width = 2000;
        b.addBlock(1000-wallWidth,500,1000,1000);
        b.addBlock(1000-wallWidth,-500,1000,225);
        b.addBlock(1000-wallWidth,-500,width,-500+wallWidth);
        b.addBlock(1000-wallWidth,1000-wallWidth,width,1000);
        b.addBlock(width-wallWidth,-500,width,1000);


        int num_enemies = 4;
        // enemies
        for (int i=0;i<num_enemies;i++){
          int x = (int) (Math.random()*650+1250);
          int y = (int) (Math.random()*1000-250);
          super.addEnemy(new EnemyShip(x, y, 10));
        }

        super.addBlock(b);
        
    }
}