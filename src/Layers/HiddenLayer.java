package Layers;

import Neuron.Neuron;

import java.util.ArrayList;

public class HiddenLayer {

    ArrayList<Double> inputs;
    ArrayList<Neuron> neurons;
    ArrayList<Double> outputs;

    public HiddenLayer(){}

    public void init(int neuronsSize, int weigthsSize){

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

    public ArrayList<Neuron> getNeurons(){
        return  neurons;
    }


    public void calculateSensibilities(ArrayList<Neuron> nextLayerNeurons){
        for(int i = 0 ; i < neurons.size() ; i++){

            Neuron neuron = neurons.get(i);
            double backPropagationError = 0.0;

            for(Neuron neuronNextLayer : nextLayerNeurons){
                double derivateActivationFunction = neuron.getOutput() * (1 - neuron.getOutput());
                backPropagationError += neuronNextLayer.getBackpropagationError()* neuronNextLayer.getWeight(i)*derivateActivationFunction;
            }
            neuron.setBackPropagationError(backPropagationError);
        }
    }

    public void updateNeuronsWeights(double learningRate){
        //Update weights for the hidden layer
        for(Neuron neuron : neurons){
            neuron.updateWeights(learningRate);
        }
    }
}
