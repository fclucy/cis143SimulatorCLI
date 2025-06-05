package Module8;

import java.time.LocalDate;

public class SubmarineDriver {
    public static void main(String[] args) {
        
        Fleet fleet = new Fleet();

        Submarine sub1 = new Submarine("Submarine 1");
        Submarine sub2 = new Submarine("Submarine 2");
        Submarine sub3 = new Submarine("Submarine 3");
        Submarine sub4 = new Submarine("Submarine 4");
        Submarine sub5 = new Submarine("Submarine 5");
        Submarine sub6 = new Submarine("Submarine 6");
        Submarine sub7 = new Submarine("Submarine 7");
        Submarine sub8 = new Submarine("Submarine 8");
        Submarine sub9 = new Submarine("Submarine 9");
        Submarine sub10 = new Submarine("Submarine 10");
        Submarine sub11 = new Submarine("Submarine 11");
        Submarine sub12 = new Submarine("Submarine 12");

        fleet.addSubmarine(sub1);
        fleet.addSubmarine(sub2);
        fleet.addSubmarine(sub3);
        fleet.addSubmarine(sub4);
        fleet.addSubmarine(sub5);
        fleet.addSubmarine(sub6);
        fleet.addSubmarine(sub7);
        fleet.addSubmarine(sub8);
        fleet.addSubmarine(sub9);
        fleet.addSubmarine(sub10);
        fleet.addSubmarine(sub11);
        fleet.addSubmarine(sub12);

        LocalDate today = LocalDate.now();
        fleet.simulateFleetCSV("log/sonar-log_" + today + ".csv");
    }
}