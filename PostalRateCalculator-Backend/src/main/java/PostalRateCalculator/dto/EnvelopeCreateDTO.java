package PostalRateCalculator.dto;

import PostalRateCalculator.model.SizeUnit;
import PostalRateCalculator.model.WeightUnit;

public class EnvelopeCreateDTO {
    private int width;
    private int length;
    private int weight;
    private SizeUnit sizeUnit;
    private WeightUnit weightUnit;


    // Getters
    public int getWidth() { return width; }

    public int getLength() { return length; }

    public int getWeight() { return weight; }

    public SizeUnit getSizeUnit() { return sizeUnit; }

    public WeightUnit getWeightUnit() { return weightUnit; }


    // Setters
    public void setWidth(int width) { this.width = width; }

    public void setLength(int length) { this.length = length; }

    public void setWeight(int weight) { this.weight = weight; }

    public void setSizeUnit(SizeUnit sizeUnit) { this.sizeUnit = sizeUnit; }

    public void setWeightUnit(WeightUnit weightUnit) { this.weightUnit = weightUnit; }
}
