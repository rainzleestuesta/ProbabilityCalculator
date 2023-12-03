import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class inputComputation extends inputHandler {

    public void computeMean() {
        double sum = 0;

        for(int i = 0; i < this.getSize(); i++){
            sum = sum + this.getValues()[i];
        }
        this.setMean(sum / this.getSize());
    }
    public double computeMedian() {
        //Duplicate values list in getting the median
        double[] sortedValues = new double[this.getSize()];
        System.arraycopy(this.getValues(), 0, sortedValues, 0, this.getSize());

        //Sort the duplicate list copy
        Arrays.sort(sortedValues);

        //Get the middle value of the new sorted list
        if(this.getSize() % 2 != 0){
            this.setMedian(this.getValues()[this.getSize() / 2]);
        }
        else{
            int index1, index2;
            index1 = this.getSize() / 2;
            index2 = (this.getSize()/2)+1;
            this.setMedian((this.getValues()[index1] + this.getValues()[index2]) / 2.0);
        }
        return this.getMedian();
    }
    public ArrayList<Double> computeMode() {
        //NOTE: An array of values can have multiple modes. Therefore, we will return a double array that contains the mode/s

        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        ArrayList<Double> modeList = new ArrayList<>();
        int maxFrequency = 0;

        for(double val : this.getValues()) {
            int frequency = frequencyMap.getOrDefault(val, 0) + 1;
            frequencyMap.put(val, frequency);

            if(frequency > maxFrequency) {
                maxFrequency = frequency;
            }
        }

        for (HashMap.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                modeList.add(entry.getKey());
            }
        }

        return modeList;
    }

    public double computeVariance(double mean) {
        //return all instances of (x - mean)^2 into an array
        double variance = 0, sum = 0;
        for(int i=0; i<this.getSize(); i++) {
            sum += Math.pow(this.getValues()[i] - mean, 2);
        }

        //Depending on the sampling method, solve for the value of the variance
        variance = switch (this.getSamplingMethod()) {
            case "Population" -> sum / this.getSize();
            case "Sample" -> sum / (this.getSize() - 1);
            default -> variance;
        };
        this.setVariance(variance);
        return this.getVariance();
    }
    public double computeStandardDev(double variance) {
        double stdDev;
        stdDev = Math.sqrt(variance);
        this.setStdDev(stdDev);
        return this.getStdDev();
    }
}
