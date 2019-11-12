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

    // private instance variables
    private PlayerShip player;
    private ArrayList<EnemyShip> enemies;
    private ArrayList<Blast> blasts; // fired shots of type blast
    
    // game state
    private boolean isPaused;
    private boolean showInstructions;
    private boolean showPauseMenu;
    private boolean gameIsOver;
    private boolean gameIsWon;

    // window information
    private int height;
    private int width;
    private int absoluteX;
    private int absoluteY;


    // constructor
    Model(int h, int w) throws IOException {
        this.height = h;
        this.width = w;
        this.absoluteX = 0;
        this.absoluteY = 0;

        this.enemies = new ArrayList<EnemyShip>();
        this.blasts = new ArrayList<Blast>();
        this.player = null;

        this.isPaused = false;
        this.showInstructions = false;
        this.showPauseMenu = false;
        this.gameIsOver = false;
        this.gameIsWon = false;
    }

    
    // public methods
    public void updateState(){
        if(this.isPaused){return;}
        __updatePlayer();
        __updateBlasts();
        __updateEnemies();
        
    }
    
    public void updateImage(Graphics g) {
        if(this.gameIsOver){
            if(this.gameIsWon){
                this.__drawGameWon(g);
            } else {
                this.__drawGameOver(g);
            }
        }
        
        if(this.showInstructions){
            this.__drawInstructions(g,10,535);
        }
        if(this.showPauseMenu){
            this.__drawPauseMenu(g,490,580);
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
        __primaryShot(player);
    }
    public void fireSecondary(){
        if(this.isPaused){return;}
        __secondaryShot(player);
    }

    public void moveRight(){player.moveRight();}
    public void moveLeft(){player.moveLeft();}
    public void moveUp(){player.moveUp();}
    public void moveDown(){player.moveDown();}

    public void powerup1(){
    }
    public void powerup2(){
    }

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


    // protected methods
    protected void addPlayer(PlayerShip p){
        this.player = p;
    }
    protected void addBlast(Blast b){
        this.blasts.add(b);
    }
    protected void addEnemy(EnemyShip e){
        this.enemies.add(e);
    }


    // private methods
    private void __gameOver(){
        this.gameIsOver = true;
        this.isPaused = true;
    }
    private void __drawInstructions(Graphics g, int x, int y){
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
    private void __drawPauseMenu(Graphics g, int x, int y){
        int spacing = 20;
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("Game paused:", x,y);
        g.drawString("main menu", x,y+spacing);
        g.drawString("Q to quit", x,y+spacing*2);
        g.drawString("I to show instructions", x,y+spacing*3);
        g.drawString("Esc to resume game", x,y+spacing*4);
    }
    private void __drawGameOver(Graphics g){
        int spacing = 20;
        g.setColor(Color.red);
        g.fillRect(0,0,height, width);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("Game OVER", height/2,width/2);
        g.drawString("main menu", height/2,width/2+spacing*2);
        g.drawString("play again", height/2,width/2+spacing*2);
    }
    private void __drawGameWon(Graphics g){
        g.setColor(Color.green);
        g.fillRect(0,0,height, width);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("Level COMPLETE", height/2,width/2);
    }
    private void __updateBlasts(){
        for(Blast b : blasts){
            b.updateState();
        }
        __removeOutOfBoundsBlasts();
    }
    private void __secondaryShot(Spaceship s){
        int[] coords = s.getFiringCoords();
        blasts.add(new SecondaryBlast(coords[0],coords[1],s.getAngle()));
    }
    private void __primaryShot(Spaceship s){
        int[] coords = s.getFiringCoords();
        blasts.add(new PrimaryBlast(coords[0],coords[1],s.getAngle()));
    }
    private void __updateEnemyShots(){
        synchronized(this.enemies){
            Iterator iter = this.enemies.iterator();

            while (iter.hasNext()) {
                EnemyShip e = (EnemyShip) iter.next();
                __updateShipShots(e);
                if(e.getHealth() < 0){
                    iter.remove();
                }
            }
            if(enemies.size() == 0){
                this.gameIsWon = true;
                this.gameIsOver = true;
            }
        }
    }
    private void __updatePlayerShots(){

        __updateShipShots(this.player);
        if(player.getHealth() < 0){
            __gameOver();
        }
    }
    private void __updateShipShots(Spaceship ship){
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
    private void __removeOutOfBoundsBlasts(){
        synchronized(this.blasts){
            ListIterator iter = this.blasts.listIterator();

            while (iter.hasNext()) {
                Blast b = (Blast) iter.next();
                if(b.getX() > this.width || b.getX() < 0 || b.getY() > this.height || b.getY() < 0){
                    iter.remove();
                }
            }
        }
    }
    private void __updateEnemies(){
        
        __updateEnemyShots();
        
        for(EnemyShip e : enemies){
            int shot = e.updateState(player);
            if(shot == 1){
                __primaryShot(e);
            } else if(shot == 2){
                __secondaryShot(e);
            }            
        }
        
    }
    private void __updatePlayer(){
        __updatePlayerShots(); // shots against player
        player.updateState();
    }


}
