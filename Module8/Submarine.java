package Module8;
import java.util.Random;

public class Submarine {
    private String name;
    private int depth;
    Random random = new Random();

    public Submarine(String name) {
        this.name = name;
        this.depth = 60;
    }

    public void simulateTimeStep() {
    	boolean descend = random.nextBoolean();
        if ( descend == random.nextBoolean() ) {
            depth += random.nextInt(11);
        } else {
            depth -= random.nextInt(11);
            if ( depth < 0 ) depth = 0;
        }
    }
    
    public int getDepth() {
        return depth;
    }

    public String getName() {
        return name;
    }
}