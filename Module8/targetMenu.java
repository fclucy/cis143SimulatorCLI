package Module8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class targetMenu {

    public static double getTargetDepthFromUser() {
        Scanner scanner = new Scanner(System.in);

        // Get list of CSV files
        File logDir = new File("./log");
        File[] csvFiles = logDir.listFiles((dir, name) -> name.endsWith(".csv"));

        if (csvFiles == null || csvFiles.length == 0) {
            System.out.println("No CSV files found in ./log folder.");
            scanner.close();
            return -1;
        }

        // Display CSV file options
        System.out.println("Available CSV files:");
        for (int i = 0; i < csvFiles.length; i++) {
            System.out.println((i + 1) + ". " + csvFiles[i].getName());
        }

        // User selects a CSV
        int fileIndex = -1;
        while (fileIndex < 1 || fileIndex > csvFiles.length) {
            System.out.print("Enter the number of the CSV file you want to use: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next();
            }
            fileIndex = scanner.nextInt();
        }
        File selectedFile = csvFiles[fileIndex - 1];

        // Read the Average row
        List<String> lines;
        try {
            lines = Files.readAllLines(selectedFile.toPath());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            scanner.close();
            return -1;
        }

        String averageLine = lines.stream()
                                  .filter(line -> line.startsWith("Average"))
                                  .findFirst()
                                  .orElse(null);

        if (averageLine == null) {
            System.out.println("No 'Average' row found in the selected file.");
            scanner.close();
            return -1;
        }

        String[] values = averageLine.split(",");
        if (values.length < 13) {
            System.out.println("Malformed 'Average' row.");
            scanner.close();
            return -1;
        }

        // Display submarine average depths
        System.out.println("Average depths per submarine:");
        for (int i = 1; i <= 12; i++) {
            System.out.printf("%2d. Submarine %d: %s %n", i, i, values[i]);
        }

        // User selects a submarine
        int subIndex = -1;
        while (subIndex < 1 || subIndex > 12) {
            System.out.print("Enter the number of the target you want to use: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next();
            }
            subIndex = scanner.nextInt();
        }
        
        scanner.close();
        // Return the average depth as a double
        try {
            return Double.parseDouble(values[subIndex]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for average depth.");
            return -1;
        }
    }
}