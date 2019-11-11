/*
Kyle Sadler
Programming Paradigms Assignment 5
October 18th, 2019
*/
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

class Model{
    private PlayerShip player;
    private ArrayList<EnemyShip> enemies;
    private ArrayList<Blast> blasts; // fired shots of type blast
    private boolean isPaused;

    Model() throws IOException {
		player = new PlayerShip(100, 100, 10);
        enemies = new ArrayList<EnemyShip>();
        enemies.add(new EnemyShip(200, 200, 10));
        blasts = new ArrayList<Blast>();
        blasts.add(new PrimaryBlast(100,100,20));
        this.isPaused = false;
    }

    public void updateState(){
        if(this.isPaused){
            return;
        }
        // player.updateState();
        // try{for (Spaceship a : enemies){
        //     a.updateState(w,h);
        // }}catch(Exception e){}
        player.updateState();
        for(Blast b : blasts){
            b.updateState();
        }

        for(EnemyShip e : enemies){
            e.updateState();
        }
    }

    
    public void updateImage(Graphics g) {
        if(this.isPaused){
            return;
        }

        player.draw(g);
        for(Blast b : blasts){
            b.updateImage(g);
        }
        for(EnemyShip e : enemies){
            e.draw(g);
        }
        /*
        try{
        synchronized(sprites){
            Iterator iter = sprites.iterator();

            while (iter.hasNext()) {
                Sprite sprite = (Sprite) iter.next();

                if(sprite instanceof RobberAuto){
                    if(((RobberAuto) sprite).hasEscaped()){ System.out.println("I'm free!"); sprites.remove(sprite);}
                    for (Sprite a : sprites){
                        if(a instanceof CopAuto && sprite.overlaps(a) && !((RobberAuto) sprite).isCaptured()){
                            ((RobberAuto) sprite).captured();
                            System.out.println("Gotcha!");
                        }
                    }
                }

                sprite.updateImage(g);
            }
        }
        }catch(Exception e){}
        */
    }

    public void setAngle(int x, int y){player.setAngle(x, y);//System.out.println(player.getAngle());
    }
    public void firePrimary(){
        double error = player.getSize()*Math.sqrt(3)/6;
        int x = (int) (player.getX() + Math.cos(player.getAngle())*(error + player.getSize()/5));
        int y = (int) (player.getY() + Math.sin(player.getAngle())*(error + player.getSize()/5));
        blasts.add(new PrimaryBlast(x,y,player.getAngle()));
        
    }
    public void fireSecondary(){
        
        double error = player.getSize()*Math.sqrt(3)/6;
        int x = (int) (player.getX() + Math.cos(player.getAngle())*(error + player.getSize()/5));
        int y = (int) (player.getY() + Math.sin(player.getAngle())*(error + player.getSize()/5));
        blasts.add(new SecondaryBlast(x,y,player.getAngle()));
        
    }

    public void moveRight(){player.moveRight();}
    public void moveLeft(){player.moveLeft();}
    public void moveUp(){player.moveUp();}
    public void moveDown(){player.moveDown();}

    public void powerup1(){player.powerup1();}
    public void powerup2(){player.powerup2();}

    public void pause(){
        this.isPaused = true;
    }

}
