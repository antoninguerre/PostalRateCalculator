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

        // Verify that the envelope's width is not smaller than 140mm if the size unit is in millimeters
        if (envelope.getSizeUnit() == SizeUnit.Millimeters && envelope.getWidth() < 140) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The envelope is not wide enough.");
        }

        return 0;
    }
}
