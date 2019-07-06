package Training;

import NeuralNetwork.NeuralNetwork;

import java.util.ArrayList;

public class NeuralNetworkTraining {

    NeuralNetwork neuralNetwork;
    double learningRate;
    int epochs;
    DataSet dataSet;
    ArrayList<ArrayList<Double>> neuralNetworkOutput;  // Because is a regression we only have 1 Output
    double errorTarget = 0.1;

    public NeuralNetworkTraining(NeuralNetwork neuralNetwork, DataSet dataSet){
        this.neuralNetwork = neuralNetwork;
        this.dataSet = dataSet;
        epochs = neuralNetwork.getEpochs();
        learningRate = neuralNetwork.getLearningRate();
    }

    public void runTraining(){

        int epochCounter = 0;

        double mseError = 1;

        while (epochCounter < epochs && mseError >= errorTarget ){

            neuralNetworkOutput = new ArrayList<ArrayList<Double>>();

            for(DataSpecs dataRow : dataSet.getDataSpecs()){

                neuralNetwork.setInputLayer(dataRow.getInputs());
                neuralNetwork.calculate(); // forward
                ArrayList<Double> result = neuralNetwork.getOutput();
                neuralNetwork.backpropagation(dataRow.getTargets());
                neuralNetworkOutput.add(result);
            }

            mseError = meanSquareError();
            System.out.println("MSE:"+Double.toString(mseError));
            epochCounter++;
        }
    }

    private double meanSquareError(){

        double mse = 0;
        int dataSize = dataSet.getDataSpecs().size();

        for(int i = 0; i < dataSize ;i++){

            ArrayList<Double> outputs = neuralNetworkOutput.get(i);
            ArrayList<Double> targets = dataSet.getDataSpecs().get(i).getTargets();

            double result = 0;

            for(int j = 0; j < outputs.size() ; j++){
                double output = outputs.get(j);
                double target = targets.get(j);
                result += Math.pow(output-target,2);
            }
            mse+=result;
        }

        mse/=dataSize;
        return mse;
    }
}
