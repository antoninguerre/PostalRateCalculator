package PostalRateCalculator.model;

import PostalRateCalculator.dao.EnvelopeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EnvelopeTest {

    @Autowired
    private EnvelopeRepository envelopeRepository;

    @AfterEach
    public void clearDatabase() {
        envelopeRepository.deleteAll();
    }

    @Test
    public void testEnvelope() {
        // Initialize variables and create an instance of an envelope
        int width = 100, length = 200, weight = 20;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        // Save the envelope to the database
        envelope = envelopeRepository.save(envelope);

        // Test if the object is present and can be retrieved
        assertTrue(envelopeRepository.findEnvelopeById(envelope.getId()).isPresent());
        Envelope savedEnvelope = envelopeRepository.findEnvelopeById(envelope.getId()).get();

        // Test if the retrieved object has the correct attributes
        assertEquals(envelope.getLength(), savedEnvelope.getLength());
        assertEquals(envelope.getWidth(), savedEnvelope.getWidth());
        assertEquals(envelope.getWeight(), savedEnvelope.getWeight());
        assertEquals(envelope.getSizeUnit(), savedEnvelope.getSizeUnit());
        assertEquals(envelope.getWeightUnit(), savedEnvelope.getWeightUnit());
    }
}
