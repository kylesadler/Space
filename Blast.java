/*
Kyle Sadler
Programming Paradigms Honors Project
December 9th, 2019
*/
import java.awt.Graphics;
import java.awt.Color;
import java.io.*;

public class Blast implements Serializable{
    
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
    public double getDamage(){return this.damage;}

    public void updateImage(Graphics g, int offsetX, int offsetY){
        double newX = this.x-offsetX;
        double newY = this.y-offsetY;
        g.setColor(this.color);
        g.fillOval((int) newX, (int) newY, this.size, this.size);
    }

    public void updateState(){
        this.setX(this.getX() + this.xVelocity);
        this.setY(this.getY() + this.yVelocity);
    }
}