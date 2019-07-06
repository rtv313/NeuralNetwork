package Layers;

import java.util.ArrayList;

public class InputLayer {

    ArrayList<Double> values;

    public InputLayer(){}

    public void setInput(ArrayList<Double> values){
        this.values = values;
    }

    public ArrayList<Double> getOutput(){
        return values;
    }
}
