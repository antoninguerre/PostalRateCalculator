package PostalRateCalculator.dto;

import PostalRateCalculator.model.SizeUnit;
import PostalRateCalculator.model.WeightUnit;

public class EnvelopeDTO {
    private double width;
    private double length;
    private double weight;
    private SizeUnit sizeUnit;
    private WeightUnit weightUnit;

    public EnvelopeDTO(double width, double length, double weight, SizeUnit sizeUnit, WeightUnit weightUnit) {
        this.width = width;
        this.length = length;
        this.weight = weight;
        this.sizeUnit = sizeUnit;
        this.weightUnit = weightUnit;
    }

    // Getters
    public double getWidth() { return width; }

    public double getLength() { return length; }

    public double getWeight() { return weight; }

    public SizeUnit getSizeUnit() { return sizeUnit; }

    public WeightUnit getWeightUnit() { return weightUnit; }


    // Setters
    public void setWidth(double width) { this.width = width; }

    public void setLength(double length) { this.length = length; }

    public void setWeight(double weight) { this.weight = weight; }

    public void setSizeUnit(SizeUnit sizeUnit) { this.sizeUnit = sizeUnit; }

    public void setWeightUnit(WeightUnit weightUnit) { this.weightUnit = weightUnit; }
}
