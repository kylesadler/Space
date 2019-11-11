import java.awt.Graphics;
import java.awt.Color;

public class Blast{
    
    private double x;
    private double y;
    private double xVelocity;
    private double yVelocity;
    private static double damage;
    private Color color;
    private int size;
    private int speed;

    public Blast(double x_in, double y_in, double angle, double dam, Color color_in, int size_in, int speed_in){
        this.size = size_in;
        this.x = x_in - this.size / 2;
        this.y = y_in - this.size / 2;
        this.speed = speed_in;
        this.xVelocity = Math.cos(angle)*this.speed;
        this.yVelocity = Math.sin(angle)*this.speed;
        this.damage = dam;
        this.color = color_in;
        
    }

    public double getX(){return this.x;}
    public double getY(){return this.y;}
    public int getSize(){return this.size;}
    public void setX(double in){this.x = in;}
    public void setY(double in){this.y = in;}

    public void updateImage(Graphics g){
        g.setColor(this.color);
        g.fillOval((int) this.x, (int) this.y, this.size, this.size);
    }

    public void updateState(){
        this.setX(this.getX() + this.xVelocity);
        this.setY(this.getY() + this.yVelocity);
    }
}