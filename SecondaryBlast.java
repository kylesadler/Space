import java.awt.Graphics;
import java.awt.Color;

public class SecondaryBlast extends Blast{

    private static int SECONDARY_BLAST_DAMAGE = 20;
    private static Color SECONDARY_BLAST_COLOR = Color.PINK;
    public static int SECONDARY_BLAST_SIZE = 17;
    private static int SECONDARY_BLAST_SPEED = 20;

    public SecondaryBlast(double x_in, double y_in, double angle){
        super(x_in, y_in, angle, SECONDARY_BLAST_DAMAGE, SECONDARY_BLAST_COLOR, SECONDARY_BLAST_SIZE,SECONDARY_BLAST_SPEED);
    }


}