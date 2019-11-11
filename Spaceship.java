import java.awt.Color;
import java.awt.Graphics;

public class Spaceship{

    private double x;
    private double y;
    private double angle; // -pi to pi
    private double xVelocity;
    private double yVelocity;
    private double maxAccel = 7; // amount of thrust per move
    private double maxSpeed;
    private double health;
    private double maxHealth;
    private Color color;
    private int SIZE = 100;
    

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
    public double getSize(){return this.SIZE;}

    // public void setX(double x_in){this.x=x_in;}
    // public void setY(double y_in){this.y=y_in;}
    public void setAngle(int x, int y){ // angle from top horizontal
        double xDif = x - this.x;
        double yDif = y - this.y;
        double angle = Math.atan((double)yDif/xDif); // -pi/2 to pi/2

        if(xDif < 0){
            angle+=Math.PI;
        }
        // System.out.println();
        // System.out.println(this.y);
        // System.out.println(this.x);
        // System.out.println(this.y);
        this.angle = angle; 
    }
    public void setHealth(double health_in){this.health=health_in;}
    public void setMaxHealth(double maxHealth_in){this.maxHealth=maxHealth_in;}
    public void setMaxSpeed(double maxSpeed_in){this.maxSpeed=maxSpeed_in;}
    public void setXVelocity(double xvel_in){this.xVelocity=xvel_in;}
    public void setYVelocity(double yvel_in){this.yVelocity=yvel_in;}

    public void moveRight(){if(this.xVelocity <= this.maxSpeed-this.maxAccel){this.xVelocity += this.maxAccel;} else if(this.xVelocity > 0){this.xVelocity = this.maxSpeed;}}
    public void moveLeft(){if(this.xVelocity >= -this.maxSpeed+this.maxAccel){this.xVelocity -= this.maxAccel;} else if(this.xVelocity < 0){
            this.xVelocity = -this.maxSpeed;
        }}
    public void moveUp(){if(this.yVelocity >= -this.maxSpeed+this.maxAccel){this.yVelocity -= this.maxAccel;} else if(this.yVelocity < 0){
            this.yVelocity = -this.maxSpeed;
        }}
    public void moveDown(){if(this.yVelocity <= this.maxSpeed-this.maxAccel){this.yVelocity += this.maxAccel;} else if(this.yVelocity > 0){
            this.yVelocity = this.maxSpeed;
        }}

    public void powerup1(){}
    public void powerup2(){}

    public void draw(Graphics g) {
		g.setColor(this.color);
        double error = this.SIZE*Math.sqrt(3)/6;

        int[] xPoints = new int[3];
        xPoints[0] = (int) (this.x + Math.cos(this.angle)*(error + this.SIZE/5)); // front
        xPoints[1] = (int) (this.x + Math.cos(this.angle+2*Math.PI/3)*error); 
        xPoints[2] = (int) (this.x + Math.cos(this.angle-2*Math.PI/3)*error);

        int[] yPoints = new int[3];
        yPoints[0] = (int) (this.y + Math.sin(this.angle)*(error + this.SIZE/5));
        yPoints[1] = (int) (this.y + Math.sin(this.angle+2*Math.PI/3)*error); 
        yPoints[2] = (int) (this.y + Math.sin(this.angle-2*Math.PI/3)*error);

        g.fillPolygon(xPoints, yPoints, 3);
	}

	public void updateState() {
        this.x += this.xVelocity;
        this.y += this.yVelocity;
        //System.out.println(this.xVelocity);
        this.yVelocity *= 49.0/50;
        this.xVelocity *= 49.0/50;
    }
}