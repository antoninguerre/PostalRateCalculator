package PostalRateCalculator.controller;

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

        String message;

        if (envelopeService.calculatePostalRate(envelope) == 0) {
            message = "There is no postal rate associated with this envelope.";
        } else {
            message = "The postal rate for this envelope is: " + envelopeService.calculatePostalRate(envelope) + " dollars.";
        }

        return message;
    }

    private static double syntaxVerification(String inputString, String attribute) {
        // Verify that the input can be converted from a String to a double
        try {
            Double.parseDouble(inputString);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid " + attribute + " syntax.");
        }
        return Double.parseDouble(inputString);
    }

    private static SizeUnit sizeUnitConversion(String inputUnit) {
        // Verify that the input size unit is either "mm", "millimeters", "in" or "inches", and throw an exception otherwise
        if (inputUnit.equalsIgnoreCase("mm") || inputUnit.equalsIgnoreCase("millimeters")) { return SizeUnit.Millimeters; }
        if (inputUnit.equalsIgnoreCase("in") || inputUnit.equalsIgnoreCase("inches")) { return SizeUnit.Inches; }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid or empty size unit.");
    }

    private static WeightUnit weightUnitConversion(String inputUnit) {
        // Verify that the input weight unit is either "g", "grams", "oz" or "ounces", and throw an exception otherwise
        if (inputUnit.equalsIgnoreCase("g") || inputUnit.equalsIgnoreCase("grams")) { return WeightUnit.Grams; }
        if (inputUnit.equalsIgnoreCase("oz") || inputUnit.equalsIgnoreCase("ounces")) { return WeightUnit.Ounces; }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid or empty weight unit.");
    }
}
