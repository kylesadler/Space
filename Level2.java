
/*
Kyle Sadler
Programming Paradigms Honors Project
December 9th, 2019
*/
import java.io.IOException;
import java.awt.Color;

public class Level2 extends Model {

    public Level2(int h, int w) {
        super(h,w, false);
		    super.addPlayer(new PlayerShip(100, 100, 10));

        int wallWidth = 50;

        Block b = new Block(Color.red);
        int num_enemies = 3;

        // hallway
        b.addBlock(250,225,250+wallWidth,500);
        b.addBlock(250,225,1000,225+wallWidth);
        b.addBlock(500,500,1000,500+wallWidth);
        
        int width = 2000;
        // entry room
        b.addBlock(-750,500,250+wallWidth,500+wallWidth);
        b.addBlock(-750,500,-750+wallWidth,1250);
        b.addBlock(-750,1250,100,1250+wallWidth);
        b.addBlock(100,1250,100+wallWidth,1600);
        b.addBlock(100,1600,1500,1600+wallWidth);
        b.addBlock(1500-wallWidth,1000,1500,1600+wallWidth);
        // enemies in big room
        for (int i=0;i<num_enemies;i++){
          int x = (int) (Math.random()*1200-600);
          int y = (int) (Math.random()*600+600);
          super.addEnemy(new EnemyShip(x, y, 10));
        }

        for (int i=0;i<num_enemies;i++){
          int x = (int) (Math.random()*350+600);
          int y = (int) (Math.random()*900+600);
          super.addEnemy(new EnemyShip(x, y, 10));
        }
        


        // big room
        b.addBlock(1000-wallWidth,500,1000,1000);
        b.addBlock(1000-wallWidth,-500,1000,225);
        b.addBlock(1000-wallWidth,-500,width,-500+wallWidth);

        b.addBlock(1000-wallWidth,1000-wallWidth,1250,1000);
        b.addBlock(1500-wallWidth,1000-wallWidth,width,1000);

        b.addBlock(width-wallWidth,-500,width,1000);
        for (int i=0;i<num_enemies;i++){
          int x = (int) (Math.random()*650+1250);
          int y = (int) (Math.random()*1000-250);
          super.addEnemy(new EnemyShip(x, y, 10));
        }

        super.addBlock(b);
        
    }
}