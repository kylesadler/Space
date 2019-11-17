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

    public void updateImage(Graphics g, int offsetX, int offsetY) {
		g.setColor(this.color);
        
        for (int[] coord : sections){
            g.fillRect (coord[0]-offsetX, coord[1]-offsetY, coord[2]-coord[0], coord[3]-coord[1]);
        }
	}
}