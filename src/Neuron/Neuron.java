package Neuron;

import java.util.ArrayList;
import java.util.Random;

public class Neuron {

    ArrayList<Double> inputs;
    ArrayList<Double> weights;
    double bias;
    double biasWeight;
    double output;
    double backPropagationError;

    public Neuron(){}

    public void init(int weigthsSize){

        weights = new ArrayList<Double>();
        output = 0;
        backPropagationError = 0;
        Random r = new Random();

        for (int i  = 0 ; i < weigthsSize ; i++){
            weights.add(r.nextDouble());
        }

        bias = 1;
        biasWeight = r.nextDouble();
    }

    public void setInputs(ArrayList<Double> inputs){
        this.inputs = inputs;
    }

    public void calculate(){

        double weightSum = getWeightSum();
        output = (1)/(1 + Math.exp(-weightSum));
    }

    public double getOutput(){
        return  output;
    }


    public double getWeightSum(){

        double weightSum = 0;

        for (int i  = 0 ; i < inputs.size() ; i++){
            weightSum += inputs.get(i)*weights.get(i);
        }

        weightSum += bias*biasWeight;
        return weightSum;
    }

    public void setBackPropagationError(double backPropagationError){
        this.backPropagationError = backPropagationError;
    }


    public double getWeight(int index){
        return  weights.get(index);
    }

    public double getBackpropagationError(){
        return  backPropagationError;
    }

    public void updateWeights(double learningRate){

        for(int i = 0; i < weights.size(); i++){
            double newWeight = weights.get(i) - learningRate*backPropagationError*inputs.get(i);
            weights.set(i,newWeight);
        }
        biasWeight = biasWeight - learningRate*backPropagationError*bias;
    }
}
