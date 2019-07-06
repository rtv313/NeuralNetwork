package Layers;

import NeuralNetwork.NeuralNetwork;
import Neuron.Neuron;

import java.util.ArrayList;

public class OutpuLayer {

    ArrayList<Double> inputs;
    ArrayList<Neuron> neurons;
    ArrayList<Double> outputs;
    double sensitivityError;

    public OutpuLayer(){}

    public void init(int neuronsSize,int weigthsSize){
        neurons = new ArrayList<Neuron>();

        for(int i = 0; i < neuronsSize; i++){
            Neuron neuron = new Neuron();
            neuron.init(weigthsSize);
            neurons.add(neuron);
        }
    }

    public void setInputs(ArrayList<Double> inputs){
        this.inputs = inputs;
    }

    public void calculate(){
        outputs = new ArrayList<Double>();
        for(Neuron neuron : neurons){
            neuron.setInputs(inputs);
            neuron.calculate();
            outputs.add(neuron.getOutput());
        }
    }

    public ArrayList<Double> getOutputs(){
        return outputs;
    }

    public ArrayList<Neuron> getNeurons(){return  neurons;}

    public void calculateSensitivityErrorForNeurons(ArrayList<Double> targets)
    {
        int  i = 0;
        for(Neuron neuron : neurons){
            double output = neuron.getOutput();
            double sensibilityError = (output-targets.get(i))*(output)*(1-output);
            neuron.setBackPropagationError(sensibilityError);
            i++;
        }
    }
    public void updateNeuronsWeights(double learningRate){
        //Update weights for the output layer
        for(Neuron neuron : neurons){
            neuron.updateWeights(learningRate);
        }
    }
}
