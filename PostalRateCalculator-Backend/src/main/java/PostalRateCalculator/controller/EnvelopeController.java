package PostalRateCalculator.controller;

import PostalRateCalculator.service.EnvelopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class EnvelopeController {

    @Autowired
    private EnvelopeService envelopeService;



    // The inputs should be RequestParams(int width, int height, int weight, String sizeUnit, String weightUnit)
    // From that we can create an envelope and send it to the service method
}
