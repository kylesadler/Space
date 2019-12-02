import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class Block{

    private Color color;
    private ArrayList<int[]> sections;

    public Block (Color c){
        this.color = c;
        this.sections = new ArrayList<int[]>();
    }

    public void addBlock(int x1, int y1, int x2, int y2){
        int[] coords = {x1, y1, x2, y2};
        this.sections.add(coords);
    }

    public boolean intersect(double x, double y, int error){
        for (int[] b : this.sections){
            if(x > b[0]-error && x < b[2]+error && y > b[1]-error && y < b[3]+error){ // if inside block
                return true;
            }
        }
        return false;
             
    }


    public int[] getBounceMultipliers(double x, double y, int error){
        // x, y are in current block
        
        int[] output = new int[2];
        for (int[] b : this.sections){
        // if bouncing in the x direction
            if(Math.min(x - (b[0]-error), x - (b[2]+error)) < Math.min(y - (b[1]-error), y - (b[3]+error))){ // if inside block
                output[0] = -1;
                output[1] = 1;
            }else{
                output[1] = -1;
                output[0] = 1;
            }
        }

        return output;
             
    }

    public double[] rebound(double x, double y){
        double[] output = new double[2];
        output[0] = x;
        output[1] = y;
        for (int[] b : this.sections){
            if(x > b[0] && x < b[2] && y > b[1] && y < b[3]){ // if inside block
                double x1Dist = Math.abs(x-b[0]);
                double y1Dist = Math.abs(x-b[2]);
                double x2Dist = Math.abs(y-b[1]);
                double y2Dist = Math.abs(y-b[3]);
                double minDist = Math.min(Math.min(x1Dist, y1Dist), Math.min(x2Dist, y2Dist));
                if(minDist == x1Dist){
                    output[0] = b[0];
                    break;
                } else if(minDist == x2Dist){
                    output[0] = b[2];
                    break;
                } else if(minDist == y1Dist){
                    output[1] = b[1];
                    break;
                } else if(minDist == y2Dist){
                    output[1] = b[3];
                    break;
                }
            }
        }

        return output;
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