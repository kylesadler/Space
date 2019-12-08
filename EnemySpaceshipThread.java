/*
Kyle Sadler
Programming Paradigms Honors Project
December 9th, 2019
*/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.io.*;

public class EnemySpaceshipThread implements Runnable, Serializable{

    ArrayList<EnemyShip> enemies;
    PlayerShip p;
    ArrayList<Blast> blasts;
    PlayerShip player;

    public EnemySpaceshipThread(PlayerShip p_in, ArrayList<Blast> blasts_, PlayerShip player_in){
        this.enemies = new ArrayList<EnemyShip>();
        this.p = p_in;
        this.blasts = blasts_;
        this.player = player_in;
    }

    public void run(){
        while(true){
          try {updateEnemies(); Thread.sleep(200);} catch (Exception e) {}
        }
    }


    private void updateEnemies(){
      synchronized(this.enemies){
        Iterator enemyIter = this.enemies.iterator();
        
        // update all the shots
        while (enemyIter.hasNext()) {

            EnemyShip e = (EnemyShip) enemyIter.next();
            synchronized(this.blasts){
              ListIterator blastIter = this.blasts.listIterator();

              while (blastIter.hasNext()) {
                Blast b = (Blast) blastIter.next();
                if(e.isShot(b)){
                    e.setHealth(e.getHealth()-b.getDamage());
                    blastIter.remove();
                }
              }
            }

            if(e.getHealth() < 0){
                enemyIter.remove();
            }
        }

        // update all the states
        enemyIter = this.enemies.iterator();
        while (enemyIter.hasNext()) {
            EnemyShip e = (EnemyShip) enemyIter.next();
            int shot = e.updateState(player);
            if(shot == 1){
                __primaryShot(e);
            } else if(shot == 2){
                __secondaryShot(e);
            }            
        }
      }
    }

    private void __secondaryShot(Spaceship s){
        int[] coords = s.getFiringCoords(2);
        blasts.add(new SecondaryBlast(coords[0],coords[1],s.getAngle()));
    }
    private void __primaryShot(Spaceship s){
        int[] coords = s.getFiringCoords(1);
        blasts.add(new PrimaryBlast(coords[0],coords[1],s.getAngle()));
    }

    public void add(EnemyShip e){
      this.enemies.add(e);
    }

    public ArrayList<EnemyShip> getEnemies(){
      return this.enemies;
    }
}