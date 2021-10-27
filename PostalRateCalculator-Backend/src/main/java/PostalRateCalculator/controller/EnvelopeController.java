package PostalRateCalculator.controller;

import PostalRateCalculator.dto.EnvelopeDTO;
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


    @PostMapping(path="/postalRateCalculator")
    public EnvelopeDTO calculatePostalRate(@RequestParam(name="width") String widthString,
                                           @RequestParam(name="length") String lengthString,
                                           @RequestParam(name="weight") String weightString,
                                           @RequestParam(name="sizeUnit") String sizeUnitString,
                                           @RequestParam(name="weightUnit") String weightUnitString) throws ResponseStatusException {

        double width = syntaxVerification(widthString);
        double length = syntaxVerification(lengthString);
        double weight = syntaxVerification(weightString);

        return null;
    }



    private double syntaxVerification(String inputString) {
        // Verify that the input can be converted from a String to a double
        try {
            Double.parseDouble(inputString);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid syntax");
        }
        double variable = Double.parseDouble(inputString);

        return variable;
    }

}
