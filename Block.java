import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.io.*;

public class Block implements Serializable{

    private Color color;
    private ArrayList<int[]> sections;
    private int[] bounceMult;

    public Block (Color c){
        this.color = c;
        this.sections = new ArrayList<int[]>();
        this.bounceMult = new int[2];
    }

    public void addBlock(int x1, int y1, int x2, int y2){
        int[] coords = {x1, y1, x2, y2};
        this.sections.add(coords);
    }

    public boolean intersect(double x, double y, int error){
        for (int[] b : this.sections){
            if(x > b[0]-error && x < b[2]+error && y > b[1]-error && y < b[3]+error){ // if inside block
                
                // set this.bounceMult
                this.bounceMult[0] = 1;
                this.bounceMult[1] = 1;
                double x1Dist = Math.abs(x-b[0]+error);
                double y1Dist = Math.abs(y-b[1]+error);
                double x2Dist = Math.abs(x-b[2]-error);
                double y2Dist = Math.abs(y-b[3]-error);
                double minDist = Math.min(Math.min(x1Dist, y1Dist), Math.min(x2Dist, y2Dist));
                if(minDist == x1Dist){
                    this.bounceMult[0] = -1;
                } else if(minDist == x2Dist){
                    this.bounceMult[0] = -1;
                } else if(minDist == y1Dist){
                    this.bounceMult[1] = -1;
                } else if(minDist == y2Dist){
                    this.bounceMult[1] = -1;
                }


                return true;
            }
        }
        return false;
             
    }


    public int[] getBounceMultipliers(double x, double y, int error){
        return this.bounceMult;
    }


    public int[] getBlock(int idx){
        return this.sections.get(idx);
    }

    public int getNumBlocks(){
        return this.sections.size();
    }

    public void updateImage(Graphics g, int offsetX, int offsetY) {
		g.setColor(this.color);
        
        for (int[] coord : sections){
            g.fillRect (coord[0]-offsetX, coord[1]-offsetY, coord[2]-coord[0], coord[3]-coord[1]);
        }
	}
}