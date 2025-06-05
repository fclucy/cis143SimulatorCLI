package Module8;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Fleet {
    private List<Submarine> submarines;

    public Fleet() {
        submarines = new ArrayList<>();
    }

    public void addSubmarine(Submarine submarine) {
        submarines.add(submarine);
    }

    public void simulateFleetCSV(String filename) {
        int totalHours = 24;
        int numSubs = submarines.size();
        int[] cumulativeDepths = new int[numSubs];

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Write CSV header
            writer.print("Hour");
            for (Submarine sub : submarines) {
                writer.print("," + sub.getName());
            }
            writer.println();

            // Simulate and write each hour's data
            for (int hour = 1; hour <= totalHours; hour++) {
                writer.print(hour);
                System.out.println("=== Hour " + hour + " ===");
                for (int i = 0; i < numSubs; i++) {
                    Submarine sub = submarines.get(i);
                    sub.simulateTimeStep();
                    int depth = sub.getDepth();
                    cumulativeDepths[i] += depth;
                    writer.print("," + depth);
                    System.out.println(sub.getName() + ": Depth = " + depth);
                }
                writer.println();
                System.out.println();
            }

            // Write average depths
            writer.print("Average");
            for (int totalDepth : cumulativeDepths) {
                double avg = (double) totalDepth / totalHours;
                writer.printf(",%.2f", avg);
            }
            writer.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
