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

class Model{
    private PlayerShip player;
    private ArrayList<EnemyShip> enemies;
    private ArrayList<Blast> blasts; // fired shots of type blast
    private boolean isPaused;
    private boolean showingInstructions;

    Model() throws IOException {
		player = new PlayerShip(100, 100, 10);
        enemies = new ArrayList<EnemyShip>();
        enemies.add(new EnemyShip(200, 200, 10));
        blasts = new ArrayList<Blast>();
        blasts.add(new PrimaryBlast(100,100,20));
        this.isPaused = false;
        this.showingInstructions = false;
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
        if(this.showingInstructions){
            this.drawInstructions(g,10,535);
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

    public void setAngle(int x, int y){if(this.isPaused){return;}player.setAngle(x, y);}

    private int[] getCoords(){
        int[] output = new int[2];
        double error = player.getSize()*Math.sqrt(3)/6;
        output[0] = (int) (player.getX() + Math.cos(player.getAngle())*(error + player.getSize()/5));
        output[1] = (int) (player.getY() + Math.sin(player.getAngle())*(error + player.getSize()/5));
        return output;
    }
    public void firePrimary(){
        if(this.isPaused){
            return;
        }
        int[] coords = this.getCoords();
        blasts.add(new PrimaryBlast(coords[0],coords[1],player.getAngle()));
        
    }
    public void fireSecondary(){
        if(this.isPaused){
            return;
        }
        int[] coords = this.getCoords();
        blasts.add(new SecondaryBlast(coords[0],coords[1],player.getAngle()));
        
    }

    public void moveRight(){player.moveRight();}
    public void moveLeft(){player.moveLeft();}
    public void moveUp(){player.moveUp();}
    public void moveDown(){player.moveDown();}

    public void powerup1(){}
    public void powerup2(){}

    public void pause(){
        this.isPaused = true;
        // this.showingInstructions = true;
    }

    public void instructions(){
        this.showingInstructions = !this.showingInstructions;
    }

    public void resume(){
        this.isPaused = false;
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
}
