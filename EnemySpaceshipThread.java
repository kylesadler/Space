/*
Kyle Sadler
Programming Paradigms Honors Project
December 9th, 2019
*/
import java.util.ArrayList;
import java.io.*;

public class EnemySpaceshipThread implements Runnable, Serializable{

    ArrayList<EnemyShip> s;
    PlayerShip p;

    public EnemySpaceshipThread(PlayerShip p_in){
        this.s = new ArrayList<EnemyShip>();
        this.p = p_in;
        // System.out.println(this.p);
    }

    public void run(){
        while(true){
          // System.out.println("running with "+this.s.size());
          
          // synchronized(this.s){
          //   // __updateEnemyShots();
          //   for(EnemyShip e : this.s){
          //     //int shot = e.updateState(this.p);
          //     // if(shot == 1){
          //     //     __primaryShot(e);
          //     // } else if(shot == 2){
          //     //     __secondaryShot(e);
          //     // }   
          //   }
          // }
            
          try {Thread.sleep(20);} catch (InterruptedException e) {}
        }
    }

    // private void __updateEnemyShots(){
    //     synchronized(this.enemies){
    //         Iterator iter = this.enemies.iterator();

    //         while (iter.hasNext()) {
    //             EnemyShip e = (EnemyShip) iter.next();
    //             __updateShipShots(e);
    //             if(e.getHealth() < 0){
    //                 iter.remove();
    //             }
    //         }
    //         if(enemies.size() == 0){
    //             this.gameIsWon = true;
    //             this.gameIsOver = true;
    //         }
    //     }
    // }

    // private void __secondaryShot(Spaceship s){
    //     int[] coords = s.getFiringCoords();
    //     blasts.add(new SecondaryBlast(coords[0],coords[1],s.getAngle()));
    // }
    // private void __primaryShot(Spaceship s){
    //     int[] coords = s.getFiringCoords();
    //     blasts.add(new PrimaryBlast(coords[0],coords[1],s.getAngle()));
    // }

    public void add(EnemyShip e){
      this.s.add(e);
      // System.out.println("added");
    }

    public ArrayList<EnemyShip> getEnemies(){
      return this.s;
    }
}