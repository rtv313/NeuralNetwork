package Training;

import java.util.ArrayList;

public class DataSpecs {

    ArrayList<Double> inputs;
    ArrayList<Double> targets;

    public DataSpecs(){

    }

    public ArrayList<Double> getInputs(){
        return  inputs;
    }
    public ArrayList<Double> getTargets(){
        return  targets;
    }

    public void InitDataSpecs(ArrayList<Double> inputs,ArrayList<Double> targets){
        this.inputs = inputs;
        this.targets = targets;
    }
}
