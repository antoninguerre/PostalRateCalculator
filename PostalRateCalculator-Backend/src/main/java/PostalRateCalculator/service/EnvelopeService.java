package PostalRateCalculator.service;

import PostalRateCalculator.dao.EnvelopeRepository;
import PostalRateCalculator.model.Envelope;
import PostalRateCalculator.model.SizeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EnvelopeService {

    @Autowired
    private EnvelopeRepository envelopeRepository;

    @Transactional
    public int calculatePostalRate(Envelope envelope) {

        widthRangeCheck(envelope.getWidth(), envelope.getSizeUnit());
        lengthRangeCheck(envelope.getLength(), envelope.getSizeUnit());

        return 0;
    }


    private static void widthRangeCheck(double width, SizeUnit sizeUnit) {
        // Verify that the envelope's width is between 90 and 270 mm if the size unit is in millimeters
        if (sizeUnit == SizeUnit.Millimeters && (width < 90 || 270 < width)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The envelope's width is not within the allowed range.");
        }

        // Verify that the envelope's width is between 3.543 and 10.630 in if the size unit is in inches
        if (sizeUnit == SizeUnit.Inches && (width < 3.543 || 10.630 < width)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The envelope's width is not within the allowed range.");
        }
    }

    private static void lengthRangeCheck(double length, SizeUnit sizeUnit) {
        // Verify that the envelope's length is between 140 and 380 mm if the size unit is in millimeters
        if (sizeUnit == SizeUnit.Millimeters && (length < 140 || 380 < length)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The envelope's length is not within the allowed range.");
        }

        // Verify that the envelope's length is between 5.512 and 14.961 in if the size unit is in inches
        if (sizeUnit == SizeUnit.Inches && (length < 5.512 || 14.961 < length)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The envelope's length is not within the allowed range.");
        }
    }
}
