package PostalRateCalculator.controller;

import PostalRateCalculator.dto.EnvelopeDTO;
import PostalRateCalculator.model.Envelope;
import PostalRateCalculator.model.SizeUnit;
import PostalRateCalculator.model.WeightUnit;
import PostalRateCalculator.service.EnvelopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*")
@RestController
public class EnvelopeController {

    @Autowired
    private EnvelopeService envelopeService;


    @PostMapping(path="/postal_rate_calculator")
    public String calculatePostalRate(@RequestParam(name="width") String widthString,
                                           @RequestParam(name="length") String lengthString,
                                           @RequestParam(name="weight") String weightString,
                                           @RequestParam(name="sizeUnit") String sizeUnitString,
                                           @RequestParam(name="weightUnit") String weightUnitString) throws ResponseStatusException {

        double width = syntaxVerification(widthString, "width");
        double length = syntaxVerification(lengthString, "length");
        double weight = syntaxVerification(weightString, "weight");
        SizeUnit sizeUnit = sizeUnitConversion(sizeUnitString);
        WeightUnit weightUnit = weightUnitConversion(weightUnitString);

        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        return String.valueOf(envelopeService.calculatePostalRate(envelope));
    }

    private double syntaxVerification(String inputString, String attribute) {
        // Verify that the input can be converted from a String to a double
        try {
            Double.parseDouble(inputString);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid " + attribute + " syntax");
        }
        return Double.parseDouble(inputString);
    }

    private static SizeUnit sizeUnitConversion(String inputUnit) {
        SizeUnit sizeUnit = null;
        if (inputUnit.equals("mm")) { sizeUnit = SizeUnit.Millimeters; }
        if (inputUnit.equals("in")) { sizeUnit = SizeUnit.Inches; }

        return sizeUnit;
    }

    private static WeightUnit weightUnitConversion(String inputUnit) {
        WeightUnit weightUnit = null;
        if (inputUnit.equals("g")) { weightUnit = WeightUnit.Grams; }
        if (inputUnit.equals("oz")) { weightUnit = WeightUnit.Ounces; }

        return weightUnit;
    }


//    @PostMapping(path = "/envelope")
//    public EnvelopeDTO createEnvelope(@RequestParam(name="width") String widthString,
//                                      @RequestParam(name="length") String lengthString,
//                                      @RequestParam(name="weight") String weightString,
//                                      @RequestParam(name="sizeUnit") String sizeUnitString,
//                                      @RequestParam(name="weightUnit") String weightUnitString) throws ResponseStatusException {
//
//        Envelope envelope = envelopeService.createEnvelope(widthString, lengthString, weightString, sizeUnitString, weightUnitString);
//
//        return convertToDTO(envelope);
//    }
//
//    public static EnvelopeDTO convertToDTO(Envelope envelope) {
//        return new EnvelopeDTO(envelope.getWidth(), envelope.getLength(), envelope.getWeight(), envelope.getSizeUnit(), envelope.getWeightUnit());
//    }

}
