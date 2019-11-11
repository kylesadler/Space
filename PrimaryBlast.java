import java.awt.Graphics;
import java.awt.Color;

public class PrimaryBlast extends Blast{

    private static int PRIMARY_BLAST_DAMAGE = 10;
    private static Color PRIMARY_BLAST_COLOR = Color.green;
    private static int PRIMARY_BLAST_SIZE = 10;

    public PrimaryBlast(double x_in, double y_in, double xVel, double yVel){
        super(x_in, y_in, xVel, yVel, PRIMARY_BLAST_DAMAGE, PRIMARY_BLAST_COLOR, PRIMARY_BLAST_SIZE);
    }

    
}