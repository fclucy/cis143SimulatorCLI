package Module8;

import java.util.ArrayList;
import java.util.List;

public class Swarm {
    private List<UUV> uuvs;

    public Swarm() {
        uuvs = new ArrayList<>();
    }

    public void addUUV(UUV uuv) {
        uuvs.add(uuv);
    }

    public double getAverageDepth() {
        int total = 0;
        for (UUV uuv : uuvs) {
            total += uuv.getDepth();
        }
        return total / (double) uuvs.size();
    }

    public void simulateSwarm(int hours, double target) {

        for (int hour = 1; hour <= hours; hour++) {
            double avgBefore = getAverageDepth();
            System.out.println("Hour " + hour + " (Avg Depth: " + avgBefore + ") ===");

            for (UUV uuv : uuvs) {
                uuv.simulateStep(avgBefore, target);
            }

            double avgAfter = getAverageDepth();

            for (UUV uuv : uuvs) {
                double normalizedDepth = uuv.getDepth() / 100.0;
                double diff = (uuv.getDepth() - avgBefore) / 100.0;
                double targetDiff = (target - avgBefore) / 100.0;
                uuv.train(avgBefore, avgAfter, normalizedDepth, diff, targetDiff);
            }

            for (UUV uuv : uuvs) {
                System.out.println(uuv.getName() + " Depth: " + uuv.getDepth());
            }

            System.out.println();
        }
    }
}
