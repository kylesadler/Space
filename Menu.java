
/*
Kyle Sadler
Programming Paradigms Honors Project
December 9th, 2019
*/

import java.io.IOException;
import java.awt.Color;
import java.io.*; 

public class Menu extends Model{

    public Menu(int h, int w) {
        super(h,w, true);
        super.addPlayer(new PlayerShip(100, 100, 10));
        // super.addEnemy(new EnemyShip(-1000, -1000, 10));
        super.drawMenu();

        int wallWidth = 50;
        Block b = new Block(Color.red);

        // b.addBlock(0, 0, wallWidth, h);
        // b.addBlock(0, 0, w, wallWidth);
        // b.addBlock(0,h-wallWidth,w,h);
        // b.addBlock(w-wallWidth,0,w,h);
        b.addBlock(0, 0, wallWidth, w);
        b.addBlock(0, 0, h, wallWidth);
        b.addBlock(0,w-wallWidth,h,w);
        b.addBlock(h-wallWidth,0,h,w);

        super.addBlock(b);
    }
}