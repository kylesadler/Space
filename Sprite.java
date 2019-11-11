/*
Kyle Sadler
Programming Paradigms Assignment 5
October 18th, 2019
*/
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


class Sprite
{
	private String jpgName;
	private int locationX;
	private int locationY;
	private Image image;
	private int height;
	private int width;

	public Sprite(String jpgName)
	{
		setImage(jpgName);
		locationX = 0;
		locationY = 0;
	}

	public int getX() {	return locationX; }
	public int getY() {	return locationY; }
	public void setX(int x) { locationX = x; }
	public void setY(int y) { locationY = y; }

	public void setImage(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ioe) {
            System.out.println("Unable to load image file.");
        }
	}
	public Image getImage() { return image; }

	public void updateImage(Graphics g) {
		g.drawImage(this.getImage(), this.getX(), this.getY(), 60, 60, null);
	}
	public void updateState(int w, int h){

	}


	public boolean overlaps(Sprite s){
		return (Math.abs(this.getX() - s.getX()) < 60 && Math.abs(this.getY() - s.getY()) < 60);
	}

}
