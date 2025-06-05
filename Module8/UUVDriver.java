package Module8;

public class UUVDriver {
    public static void main(String[] args) {
        Swarm swarm = new Swarm();

        double target = targetMenu.getTargetDepthFromUser();

        swarm.addUUV(new UUV("UUV 1", target));
        swarm.addUUV(new UUV("UUV 2", target));
        swarm.addUUV(new UUV("UUV 3", target));
        swarm.addUUV(new UUV("UUV 4", target));
        swarm.addUUV(new UUV("UUV 5", target));
        swarm.addUUV(new UUV("UUV 6", target));
        swarm.addUUV(new UUV("UUV 7", target));
        swarm.addUUV(new UUV("UUV 8", target));
        swarm.addUUV(new UUV("UUV 9", target));
        swarm.addUUV(new UUV("UUV 10", target));
        swarm.addUUV(new UUV("UUV 11", target));
        swarm.addUUV(new UUV("UUV 12", target));
        
        swarm.simulateSwarm(24, target);
    }
}
