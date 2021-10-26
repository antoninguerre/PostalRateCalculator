package PostalRateCalculator.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "envelope")
public class Envelope {


    //------------------------
    // MEMBER ATTRIBUTES
    //------------------------

    @Id
    @GeneratedValue
    private Long id;

    private int width;
    private int length;
    private int weight;

    @Enumerated(EnumType.ORDINAL)
    private SizeUnit sizeUnit;

    @Enumerated(EnumType.ORDINAL)
    private WeightUnit weightUnit;


    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Envelope() {
        width = 0;
        length = 0;
        weight = 0;
        sizeUnit = SizeUnit.Millimeters;
        weightUnit = WeightUnit.Grams;
    }

    public Envelope(int envelopeWidth, int envelopeLength, int envelopeWeight, SizeUnit aSizeUnit, WeightUnit aWeightUnit) {
        width = envelopeWidth;
        length = envelopeLength;
        weight = envelopeWeight;
        sizeUnit = aSizeUnit;
        weightUnit = aWeightUnit;
    }


    //------------------------
    // INTERFACE
    //------------------------

    // Getters
    public Long getId() { return id; }

    public int getWidth() { return width; }

    public int getLength() { return length; }

    public int getWeight() { return weight; }

    public SizeUnit getSizeUnit() { return sizeUnit; }

    public WeightUnit getWeightUnit() { return weightUnit; }


    // Setters
    public void setId(Long id) { this.id = id; }

    public void setWidth(int width) { this.width = width; }

    public void setLength(int length) { this.length = length; }

    public void setWeight(int weight) { this.weight = weight; }

    public void setSizeUnit(SizeUnit sizeUnit) { this.sizeUnit = sizeUnit; }

    public void setWeightUnit(WeightUnit weightUnit) { this.weightUnit = weightUnit; }
}
