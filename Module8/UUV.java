package Module8;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

public class UUV {
    private String name;
    private double depth;
    private NeuralNetwork<?> enoch;
    private double target;

    public UUV(String name, double target) {
        this.name = name;
        this.depth = target;
        this.target = target;

        enoch = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 3, 3, 1);
        enoch.randomizeWeights();
    }
    
    public double simulateStep(double averageSwarmDepth, double target) {
        double normalizedDepth = depth / 100.0;
        double diff = (depth - averageSwarmDepth) / 100.0;
        double targetDiff = (target - averageSwarmDepth) / 100.0;

        enoch.setInput(normalizedDepth, diff, targetDiff);
        enoch.calculate();
        double output = enoch.getOutput()[0];

        double change = (output - 0.5) * 20;
        depth += (int) Math.round(change);
        if (depth < 0) depth = 0;

        return output;
    }
    
    public void train(double avgBefore, double avgAfter, double normalizedDepth, double diff, double targetDiff) {
        double errorBefore = Math.abs(avgBefore - target);
        double errorAfter = Math.abs(avgAfter - target);

        double[] input = { normalizedDepth, diff, targetDiff };
        double[] output = enoch.getOutput(); // what the net just predicted

        // Did the swarm move closer to target?
        boolean helped = errorAfter < errorBefore;

        // This is an example of supervised learning. 
        // If the target matches the neural networks output, reward the model.
        // Flip the value by subtracting from one to punish bad result. 
        double targetOutput = helped ? output[0] : (1.0 - output[0]);

        // This creates a training set and trains the model on it.
        DataSet trainingSet = new DataSet(3, 1);
        DataSetRow row = new DataSetRow(input, new double[] { targetOutput });
        trainingSet.add(row);
        
        enoch.learn(trainingSet);
    }

    public double getDepth() {
        return depth;
    }

    public String getName() {
        return name;
    }

    public NeuralNetwork<?> getBrain() {
        return enoch;
    }
}