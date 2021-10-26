package PostalRateCalculator.service;

import PostalRateCalculator.dao.EnvelopeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvelopeService {

    @Autowired
    private EnvelopeRepository envelopeRepository;
}
