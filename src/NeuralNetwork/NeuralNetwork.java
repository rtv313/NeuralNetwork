package NeuralNetwork;

import Layers.HiddenLayer;
import Layers.InputLayer;
import Layers.OutpuLayer;
import Neuron.Neuron;

import java.util.ArrayList;

public class NeuralNetwork {
    InputLayer inputLayer;
    ArrayList<HiddenLayer> hiddenLayers;
    OutpuLayer outputLayer;
    int epochs;
    double learningRate;

    public NeuralNetwork(){}

    public void init(int epochs,double learningRate,int realInputsSize,int hiddenLayersSize,int neuronsSize,int outputsSize){

        // Set Epochs and learning rate
        this.epochs = epochs;
        this.learningRate = learningRate;

        //Initialize Input Layer
        inputLayer = new InputLayer();

        // Initialize  Hidden Layers
        hiddenLayers = new ArrayList<HiddenLayer>();
        HiddenLayer firstHiddenLayer = new HiddenLayer();
        firstHiddenLayer.init(neuronsSize,realInputsSize);
        hiddenLayers.add(firstHiddenLayer);
        HiddenLayer helper = firstHiddenLayer;

        for(int i=1; i < hiddenLayersSize; i++){
            HiddenLayer hiddenLayer = new HiddenLayer();
            hiddenLayer.init(neuronsSize,helper.getNeurons().size());
            hiddenLayers.add(hiddenLayer);
            helper = hiddenLayer;
        }

        // Initializae Output layers
        outputLayer = new OutpuLayer();
        outputLayer.init(outputsSize,helper.getNeurons().size());
    }

    public void setInputLayer(ArrayList<Double> Input){
        inputLayer.setInput(Input);
    }

    public void calculate(){

        HiddenLayer firstHiddenLayer = hiddenLayers.get(0);
        firstHiddenLayer.setInputs(inputLayer.getOutput());
        firstHiddenLayer.calculate();
        HiddenLayer temp = firstHiddenLayer;

        for(int i = 1; i < hiddenLayers.size(); i++){
            HiddenLayer hiddenLayer = hiddenLayers.get(i);
            hiddenLayer.setInputs(temp.getOutputs());
            hiddenLayer.calculate();
            temp = hiddenLayer;
        }

        outputLayer.setInputs(temp.getOutputs());
        outputLayer.calculate();
    }

    public ArrayList<Double> getOutput(){
        return  outputLayer.getOutputs();
    }

    public int getEpochs(){
        return  epochs;
    }

    public double getLearningRate(){
        return  learningRate;
    }

    public void backpropagation(ArrayList<Double> targets){

        //Calculate sensibilities for output
        outputLayer.calculateSensitivityErrorForNeurons(targets);

        //Calculate sensibilites for hidden layers
        ArrayList<Neuron> neuronsHelper = outputLayer.getNeurons();
        int hiddenLayerIndex =  hiddenLayers.size()-1;
        for(int i = hiddenLayerIndex; i >= 0; i--){
            HiddenLayer hiddenLayer = hiddenLayers.get(i);
            hiddenLayer.calculateSensibilities(neuronsHelper);
            neuronsHelper = hiddenLayer.getNeurons();
        }

        //Update weights for outputlayer
        outputLayer.updateNeuronsWeights(learningRate);

        //Update weights for hiddenlayer
        for(HiddenLayer hiddenLayer : hiddenLayers){
            hiddenLayer.updateNeuronsWeights(learningRate);
        }
    }
}
