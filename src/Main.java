import NeuralNetwork.NeuralNetwork;
import Training.DataSet;
import Training.NeuralNetworkTraining;

import java.util.ArrayList;


public class Main {

    public static void main(String args[]){

        // Get data set
        DataSet data = new DataSet();
        data.initDataSet();

        // Create neural network
        int epochs = 500000;
        double learningRate = 0.1;
        int inputSize = data.getDataSpecs().get(0).getInputs().size();
        int hiddenLayerSize = 1;
        int neuronsSize = 5;
        int outputSize = 2;

        NeuralNetwork neuralNetwork = new NeuralNetwork();
        neuralNetwork.init(epochs,learningRate,inputSize,hiddenLayerSize,neuronsSize,outputSize);

        NeuralNetworkTraining neuralNetworkTraining = new NeuralNetworkTraining(neuralNetwork,data);
        neuralNetworkTraining.runTraining();


        //1.0 ,0.73 , 1.0 ,0.0 for test

        ArrayList<Double> input = new ArrayList<Double>();
        input.add(1.0);
        input.add(0.73);

        neuralNetwork.setInputLayer(input);
        neuralNetwork.calculate();
        System.out.println(Double.toString(neuralNetwork.getOutput().get(0)) + " " + Double.toString(neuralNetwork.getOutput().get(1)));
    }
}
