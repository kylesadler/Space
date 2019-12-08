/*
Kyle Sadler
Programming Paradigms Honors Project
December 9th, 2019
*/
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

class Model implements Serializable{

    // private instance variables
    private PlayerShip player;
    private ArrayList<EnemyShip> enemies;
    private ArrayList<Blast> blasts; // fired shots of type blast
    private ArrayList<Block> blocks; // fired shots of type blast
    
    // game state
    private boolean isPaused;
    private boolean showInstructions;
    private boolean showPauseMenu;
    private boolean gameIsOver;
    private boolean gameIsWon;
    private boolean menu; // show menu
    private boolean isMenu;

    // window information
    private int height;
    private int width;
    private int offsetX;
    private int offsetY;

    private boolean isLastLevel;

    private EnemySpaceshipThread esht;



    // constructor
    Model(int w, int h, boolean isMenu_) {
        this.height = h;
        this.width = w;
        // this.offsetX = 0;
        // this.offsetY = 0;

        this.blasts = new ArrayList<Blast>();
        this.blocks = new ArrayList<Block>();
        this.player = null;

        this.isPaused = false;
        this.showInstructions = false;
        this.showPauseMenu = false;
        this.gameIsOver = false;
        this.gameIsWon = false;
        this.isMenu = isMenu_;
        this.isLastLevel = false;
    }

    public boolean isGameWon(){return this.gameIsWon;}
    public boolean isGameOver(){return this.gameIsOver;}
    public boolean isGamePaused(){return this.isPaused;}

    // public methods
    public void updateState(){
        if(this.isPaused){return;}
        this.offsetX = (int) (this.player.getX() - this.width/2);
        this.offsetY = (int) (this.player.getY() - this.height/2);
        __updatePlayer();
        __updateBlasts();
        __updateEnemies();
        }
    
    public void lastLevel(){
      this.isLastLevel = true;
    }
    public void updateImage(Graphics g) {
        
        if(isLastLevel){
          this.__drawLastLevel(g);
        }
        
        if(this.gameIsOver && !this.isMenu){
          if(this.gameIsWon){
            this.__drawGameWon(g);
          } else {
            this.__drawGameOver(g);
          }
        }
        
        for(Block b : blocks){
            b.updateImage(g, this.offsetX, this.offsetY);
        }
        player.draw(g, this.offsetX, this.offsetY);
        synchronized(blasts){
        for(Blast b : blasts){
            b.updateImage(g, this.offsetX, this.offsetY);
        }
        }
        synchronized(enemies){
        for(EnemyShip e : enemies){
            e.draw(g, this.offsetX, this.offsetY);
        }}

        this.__drawOptions(g, 10, this.height-100);
        if(this.showInstructions){
            this.__drawInstructions(g,250,this.height-500);
        }
        if(this.showPauseMenu){
            this.__drawPauseMenu(g,600, this.height-500);
        }
        if(this.menu){
            this.__drawMenu(g);
        }
        if(!this.isMenu){
            __drawEnemiesLeft(g);
        }
        
    }


    public void setAngle(int x, int y){
      if(!this.isPaused){
        player.setAngle(x+this.offsetX, y+this.offsetY);
      }
    }
    public void firePrimary(){
        if(!this.isPaused){
        __primaryShot(player);}
    }
    public void fireSecondary(){
        if(!this.isPaused){
        __secondaryShot(player);}
    }

    public void moveRight(){
      player.moveRight();
    }
    public void moveLeft(){
      player.moveLeft();
    }
    public void moveUp(){
      player.moveUp();
    }
    public void moveDown(){
      player.moveDown();
    }

    public void powerup1(){
        }
    public void powerup2(){
        }

    public void pause(){
        this.isPaused = true;
        this.showPauseMenu = true;
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
        this.player.setX(this.width/2);
        this.player.setY(this.height/2);
        this.esht = new EnemySpaceshipThread(this.player, this.blasts, this.player);
        this.enemies = esht.getEnemies();
        
        Thread t =new Thread(this.esht);
        t.start();
    }
    protected void addBlast(Blast b){
        this.blasts.add(b);
    }
    protected void addEnemy(EnemyShip e){
        this.esht.add(e);
    }

    protected void addBlock(Block e){
        this.blocks.add(e);
    }

    public void drawMenu(){
      this.menu = true;
      this.showInstructions = true;
    }
    public void __drawMenu(Graphics g){
      String[] words = {"Space to play"};
        __drawWords(g, words, 600, this.height-500);
    }

    // private methods
    private void __drawEnemiesLeft(Graphics g){
      g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
      g.drawString("Enemies: "+this.enemies.size(), 500, 20);
    }

    private void __drawLastLevel(Graphics g){
      g.setColor(Color.black);
      g.fillRect(0,0,this.width, this.height);
       
    }
    private void __gameOver(){
        this.gameIsOver = true;
        this.isPaused = true;
    }
    private void __drawOptions(Graphics g, int x, int y){
        String[] words = {"P to pause", "I for instructions"};
        __drawWords(g, words, x, y);
    }
    private void __drawInstructions(Graphics g, int x, int y){
        String[] instr = {"Eliminate all enemy ships", "WASD to move", "Left click to fire lasers",
        "Right click to fire plasma bombs", "Pause to pause game", 
        "Save to save current game", "Load to load a game", "Exit to quit", "Instructions for instructions"};
        __drawWords(g, instr, x, y);
    }

    private void __drawWords(Graphics g, String[] words, int x, int y){
      int spacing = 20;
      g.setColor(Color.BLACK);
      g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
      for (int i = 0; i<words.length; i++){
        String s = words[i];
        g.drawString(s, x, y+ (i*spacing));
      }
    }
    private void __drawPauseMenu(Graphics g, int x, int y){
        String[] words = {"Game paused:", "Q for main menu", "Esc to resume game"};
        __drawWords(g, words, x, y);
        
    }
    private void __drawGameOver(Graphics g){
       g.setColor(Color.red);
       g.fillRect(0,0,this.width, this.height);
       String[] words = {"Game OVER", "Q for main menu"};
        __drawWords(g, words, width/2-75, height/2+100);
    }
    private void __drawGameWon(Graphics g){
        g.setColor(Color.green);
        g.fillRect(0,0,this.width, this.height);
        String[] words = {"Level COMPLETE", "Space for next level"};
        __drawWords(g, words, width/2-75, height/2-250);
    }
    private void __updateBlasts(){
        for(Blast b : blasts){
            b.updateState();
        }
        __removeOutOfBoundsBlasts();
    }
    private void __secondaryShot(Spaceship s){
        int[] coords = s.getFiringCoords(2);
        blasts.add(new SecondaryBlast(coords[0],coords[1],s.getAngle()));
    }
    private void __primaryShot(Spaceship s){
        int[] coords = s.getFiringCoords(1);
        blasts.add(new PrimaryBlast(coords[0],coords[1],s.getAngle()));
    }
    private void __updateEnemyShots(){
        // this.esht.updateEnemyShots();
        if(enemies.size() == 0){
          this.gameIsWon = true;
          this.gameIsOver = true;
        }
        
        // synchronized(this.enemies){
        //     Iterator iter = this.enemies.iterator();

        //     while (iter.hasNext()) {
        //         EnemyShip e = (EnemyShip) iter.next();
        //         __updateShipShots(e);
        //         if(e.getHealth() < 0){
        //             iter.remove();
        //         }
        //     }
        //     if(enemies.size() == 0){
        //         this.gameIsWon = true;
        //         this.gameIsOver = true;
        //         // this.showInstructions = false;
        //         // this.showPauseMenu = false;
        //     }
        // }
    }
    private void __updatePlayerShots(){

        __updateShipShots(this.player);
        if(player.getHealth() <= 0){
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
                double blastX = b.getX()-offsetX;
                double blastY = b.getY()-offsetY;
                if(blastX > this.width*2 || blastX < -this.width || blastY > this.height*2 || blastY < -this.width){
                    iter.remove();
                    continue;
                }
                for(Block blk : this.blocks){
                    if(blk.intersect(b.getX(), b.getY(), b.getSize())){
                        iter.remove();
                        continue;
                    }
                }
            }
        }
    }
    private void __updateEnemies(){
        
        __updateEnemyShots();
        synchronized(enemies){
        for(EnemyShip e : enemies){
            int shot = e.updateState(player);
            if(shot == 1){
                __primaryShot(e);
            } else if(shot == 2){
                __secondaryShot(e);
            }            
        }
        }
        
    }

    private void __updatePlayer(){
        __updatePlayerShots(); // shots against player
        player.updateState(this.blocks, true);
    }

    private void __keepPlayerInBounds(double percentX, double percentY){
        
        
        double xmax = this.width*(1-percentX);
        double xmin = this.width*percentX;

        double ymax = this.height*(1-percentY);
        double ymin = this.height*percentY;
        
        
        if(player.getX() > xmax){
            player.setX(xmax);
        } else if(player.getX() < xmin){
            player.setX(xmin);
        }

        if(player.getY() > ymax){
            player.setY(ymax);
        } else if(player.getY() < ymin){
            player.setY(ymin);
        }

    }

}
