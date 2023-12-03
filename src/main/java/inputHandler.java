public class inputHandler {
    private String inputText, samplingMethod;
    private double[] values;
    private double mean, median, mode, variance, stdDev;
    private int size;

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getSamplingMethod() {
        return samplingMethod;
    }

    public void setSamplingMethod(String samplingMethod) {
        this.samplingMethod = samplingMethod;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public void setMode(double mode) {
        this.mode = mode;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public void setStdDev(double stdDev) {
        this.stdDev = stdDev;
    }

    public double getMean() {
        return mean;
    }

    public double getMedian() {
        return median;
    }

    public double getMode() {
        return mode;
    }

    public double getVariance() {
        return variance;
    }

    public double getStdDev() {
        return stdDev;
    }
}
