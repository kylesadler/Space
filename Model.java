/*
Kyle Sadler
Programming Paradigms Assignment 5
October 18th, 2019
*/
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

class Model{
    private PlayerShip player;
    private ArrayList<EnemyShip> enemies;
    private ArrayList<Blast> blasts; // fired shots of type blast
    private boolean isPaused;
    private boolean showInstructions;
    private boolean showPauseMenu;

    private int height;
    private int width;


    Model(int h, int w) throws IOException {
        this.height = h;
        this.width = w;
        this.enemies = new ArrayList<EnemyShip>();
        this.blasts = new ArrayList<Blast>();
        this.isPaused = false;
        this.showInstructions = false;
        this.showPauseMenu = false;
    }

    protected void addPlayer(PlayerShip p){
        this.player = p;
    }

    protected void addBlast(Blast b){
        this.blasts.add(b);
    }

    protected void addEnemy(EnemyShip e){
        this.enemies.add(e);
    }

    public void updateState(){
        if(this.isPaused){
            return;
        }

        updatePlayerShots(); // shots against player

        updateShots(); // shots against enimies

        player.updateState();
        for(Blast b : blasts){
            b.updateState();
        }

        removeOutOfBoundsBlasts();

        for(EnemyShip e : enemies){

            int shot = e.updateState(player);
            if(shot == 1){
                primaryShot(e);
            } else if(shot == 2){
                secondaryShot(e);
            }            
            
        }
    }

    
    public void updateImage(Graphics g) {
        if(this.showInstructions){
            this.drawInstructions(g,10,535);
        }
        if(this.showPauseMenu){
            this.drawPauseMenu(g,490,580);
        }

        player.draw(g);
        for(Blast b : blasts){
            b.updateImage(g);
        }
        for(EnemyShip e : enemies){
            e.draw(g);
        }
    }

    public void setAngle(int x, int y){if(this.isPaused){return;}player.setAngle(x, y);}

    public void firePrimary(){
        if(this.isPaused){return;}
        primaryShot(player);
    }
    public void fireSecondary(){
        if(this.isPaused){return;}
        secondaryShot(player);
    }
    public void secondaryShot(Spaceship s){
        int[] coords = s.getFiringCoords();
        blasts.add(new SecondaryBlast(coords[0],coords[1],s.getAngle()));
    }
    public void primaryShot(Spaceship s){
        int[] coords = s.getFiringCoords();
        blasts.add(new PrimaryBlast(coords[0],coords[1],s.getAngle()));
    }

    public void moveRight(){player.moveRight();}
    public void moveLeft(){player.moveLeft();}
    public void moveUp(){player.moveUp();}
    public void moveDown(){player.moveDown();}

    public void powerup1(){}
    public void powerup2(){}

    public void pause(){
        this.isPaused = true;
        this.showPauseMenu = true;
        // this.showInstructions = true;
    }

    public void instructions(){
        this.showInstructions = !this.showInstructions;
    }

    public void resume(){
        this.isPaused = false;
        this.showPauseMenu = false;
    }

    private void drawInstructions(Graphics g, int x, int y){
        int spacing = 20;
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("Instructions:", x,y);
        g.drawString("P to pause", x,y+spacing);
        g.drawString("WASD to move", x,y+spacing*2);
        g.drawString("Left click to fire lasers", x,y+spacing*3);
        g.drawString("Right click to fire plasma bombs", x,y+spacing*4);
        g.drawString("Q to activate powerup1", x,y+spacing*5);
        g.drawString("E to activate powerup2", x,y+spacing*6);
    }
    
    private void drawPauseMenu(Graphics g, int x, int y){
        int spacing = 20;
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("Game paused:", x,y);
        g.drawString("main menu", x,y+spacing);
        g.drawString("Q to quit", x,y+spacing*2);
        g.drawString("I to show instructions", x,y+spacing*3);
        g.drawString("Esc to resume game", x,y+spacing*4);
    }

    private void gameOver(){
        System.out.println("Game over");
    }

    private void updateShots(){
        synchronized(this.enemies){
            Iterator iter = this.enemies.iterator();

            while (iter.hasNext()) {
                EnemyShip e = (EnemyShip) iter.next();
                updateShipShots(e);
                if(e.getHealth() < 0){
                    iter.remove();
                }
            }
        }
    }

    private void updatePlayerShots(){

        updateShipShots(this.player);
        if(player.getHealth() < 0){
            gameOver();
        }
    }

    private void updateShipShots(Spaceship ship){
        synchronized(this.blasts){
            ListIterator iter = this.blasts.listIterator();

            while (iter.hasNext()) {
                Blast b = (Blast) iter.next();
                if(ship.isShot(b)){
                    ship.setHealth(ship.getHealth()-b.getDamage());
                    iter.remove();
                }
            }
                
        }

    }

    private void removeOutOfBoundsBlasts(){
        synchronized(this.blasts){
            ListIterator iter = this.blasts.listIterator();

            while (iter.hasNext()) {
                Blast b = (Blast) iter.next();
                if(b.getX() > this.width || b.getX() < 0 || b.getY() > this.height || b.getY() < 0){
                    iter.remove();
                    System.out.println("removed " + b);
                }
            }
        }
    }


}
