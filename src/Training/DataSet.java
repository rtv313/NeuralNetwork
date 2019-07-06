package Training;

import java.util.ArrayList;
import java.util.function.DoubleBinaryOperator;

public class DataSet {

    ArrayList<DataSpecs> dataSet;

    public DataSet(){

    }

    public void initDataSet(){
        ArrayList<double[]> data = new ArrayList<double[]>();

        double [][] arrayDataSet =  { // Training set
                {1.0,0.73,1.0,0.0},
                {1.0,0.81,1.0,0.0},
                {1.0,0.86,1.0,0.0},
                {0.0,0.65,1.0,0.0},
                {0.0,0.45,1.0,0.0},
                {1.0,0.70,0.0,1.0},
                {0.0,0.51,0.0,1.0},
                {1.0,0.89,0.0,1.0},
                {1.0,0.79,0.0,1.0},
                {0.0,0.54,0.0,1.0}
        };

        dataSet = new ArrayList<DataSpecs>();

        for(int i = 0; i < arrayDataSet.length; i++){

            ArrayList<Double> inputs = new ArrayList<Double>();
            ArrayList<Double> targets = new ArrayList<Double>();

            inputs.add(arrayDataSet[i][0]);
            inputs.add(arrayDataSet[i][1]);
            targets.add(arrayDataSet[i][2]);
            targets.add(arrayDataSet[i][3]);

            DataSpecs dataRow = new DataSpecs();
            dataRow.InitDataSpecs(inputs,targets);
            dataSet.add(dataRow);
        }

    }

    public ArrayList<DataSpecs> getDataSpecs(){
        return  dataSet;
    }

}
