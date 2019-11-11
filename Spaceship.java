import java.awt.Color;
import java.awt.Graphics;

public class Spaceship{

    private double x;
    private double y;
    private double angle; // -pi to pi
    private double xVelocity;
    private double yVelocity;
    private double xAcceleration;
    private double yAcceleration;
    private double maxSpeed;
    private double health;
    private double maxHealth;
    private Color color;
    

    public Spaceship(double x_in, double y_in, double angle_in, double maxHealth_in, double maxSpeed_in, Color color_in){
        this.x = x_in;
        this.y = y_in;
        this.angle = angle_in;
        this.health = maxHealth_in;
        this.maxHealth = maxHealth_in;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.maxSpeed = maxSpeed_in;
        this.color = color_in;
    }

    public double getX(){return this.x;}
    public double getY(){return this.y;}
    public double getAngle(){return this.angle;}
    public double getXVelocity(){return this.xVelocity;}
    public double getYVelocity(){return this.yVelocity;}
    public double getMaxSpeed(){return this.maxSpeed;}
    public double getMaxHealth(){return this.maxHealth;}
    public double getHealth(){return this.health;}

    public void setX(double x_in){this.x=x_in;}
    public void setY(double y_in){this.y=y_in;}
    public void setAngle(int x, int y){ // angle from top horizontal
        double xDif = x - this.getX();
        double yDif = y - this.getX();
        double angle = Math.atan((double)yDif/xDif); // -pi/2 to pi/2
        System.out.println("angle " + angle);
        if(x<0){
            angle = Math.PI-angle;
        }
        
        this.angle = angle; // %(2*Math.PI);
        // if(angle > 0){
        //     this.angle=angle-Math.PI;
        // }else{
        //     this.angle=angle+Math.PI;
        // }
    }
    public void setHealth(double health_in){this.health=health_in;}
    public void setMaxHealth(double maxHealth_in){this.maxHealth=maxHealth_in;}
    public void setMaxSpeed(double maxSpeed_in){this.maxSpeed=maxSpeed_in;}
    public void setXVelocity(double xvel_in){this.xVelocity=xvel_in;}
    public void setYVelocity(double yvel_in){this.yVelocity=yvel_in;}

    public void moveRight(){

    }
    public void moveLeft(){

    }
    public void moveUp(){this.setY(this.getY()-yVelocity);}
    public void moveDown(){this.setY(this.getY()-xVelocity);}

    public void powerup1(){}
    public void powerup2(){}


    public void draw(Graphics g) {
		g.setColor(this.color);
        
        g.fillPolygon({1, 2, 3}, {1, 2, 3};, 3);
	}

	public void updateState(int w, int h) {

    }
}