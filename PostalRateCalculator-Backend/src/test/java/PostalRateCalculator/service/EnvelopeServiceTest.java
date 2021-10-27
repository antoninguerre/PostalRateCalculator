package PostalRateCalculator.service;

import PostalRateCalculator.dao.EnvelopeRepository;
import PostalRateCalculator.model.Envelope;
import PostalRateCalculator.model.SizeUnit;
import PostalRateCalculator.model.WeightUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EnvelopeServiceTest {

    @Autowired
    private EnvelopeService envelopeService;

    @MockBean
    private EnvelopeRepository envelopeRepository;

    @BeforeEach
    public void setMockSaveOutput() {
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        when(envelopeRepository.save(any(Envelope.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testEnvelopeTooSmallWidthInMillimeters() {
        // Initialize variables (including a too small width) and create an instance of an envelope
        double width = 50, length = 200, weight = 20;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        assertThrows(ResponseStatusException.class, () ->
                        envelopeService.calculatePostalRate(envelope),
                "The envelope is not wide enough");
    }

    @Test
    public void testEnvelopeTooSmallWidthInInches() {
        // Initialize variables (including a too small width) and create an instance of an envelope
        double width = 2, length = 8, weight = 20;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        assertThrows(ResponseStatusException.class, () ->
                        envelopeService.calculatePostalRate(envelope),
                "The envelope is not wide enough");
    }

    @Test
    public void testEnvelopeTooLargeWidthInMillimeters() {
        // Initialize variables (including a too large width) and create an instance of an envelope
        double width = 300, length = 200, weight = 20;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        assertThrows(ResponseStatusException.class, () ->
                        envelopeService.calculatePostalRate(envelope),
                "The envelope is too wide");
    }

    @Test
    public void testEnvelopeTooLargeWidthInInches() {
        // Initialize variables (including a too large width) and create an instance of an envelope
        double width = 12, length = 8, weight = 20;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        assertThrows(ResponseStatusException.class, () ->
                        envelopeService.calculatePostalRate(envelope),
                "The envelope is too wide");
    }
    @Test
    public void testEnvelopeTooHeavyInGrams() {
        // Initialize variables (including a too heavy weight) and create an instance of an envelope
        double width = 100, length = 200, weight = 600;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        assertThrows(ResponseStatusException.class, () ->
                        envelopeService.calculatePostalRate(envelope),
                "The envelope is too heavy");
    }

    @Test
    public void testEnvelopeTooLightInGrams() {
        // Initialize variables (including a too light weight) and create an instance of an envelope
        double width = 200, length = 250, weight = 2.9;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        assertThrows(ResponseStatusException.class, () ->
                        envelopeService.calculatePostalRate(envelope),
                "The envelope is too light");
    }

    @Test
    public void testEnvelopeTooHeavyInOunces() {
        // Initialize variables (including a too heavy weight) and create an instance of an envelope
        double width = 200.4, length = 300, weight = 19.4;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        assertThrows(ResponseStatusException.class, () ->
                        envelopeService.calculatePostalRate(envelope),
                "The envelope is too heavy");
    }

    @Test
    public void testEnvelopeTooLightInOunces() {
        // Initialize variables (including a too light weight) and create an instance of an envelope
        double width = 12, length = 8, weight = 0.07;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        assertThrows(ResponseStatusException.class, () ->
                        envelopeService.calculatePostalRate(envelope),
                "The envelope is too light");
    }

    @Test
    public void testStandardLowerPostalRateInchesOunces() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the lower weight range
        double width = 4.2, length = 6.7, weight = 0.756;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.49, postalRate);
    }

    @Test
    public void testStandardLowerPostalRateMillimetersOunces() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the lower weight range
        double width = 100, length = 150, weight = 1.04;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.49, postalRate);
    }

    @Test
    public void testStandardLowerPostalRateMillimetersGrams() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the lower weight range
        double width = 100, length = 150, weight = 22.5;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.49, postalRate);
    }

    @Test
    public void testStandardLowerPostalRateInchesGrams() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the lower weight range
        double width = 5.03, length = 9.4, weight = 28;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.49, postalRate);
    }

    @Test
    public void testStandardHigherPostalRateInchesOunces() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the higher weight range
        double width = 4.2, length = 6.7, weight = 1.756;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.80, postalRate);
    }

    @Test
    public void testStandardHigherPostalRateMillimetersOunces() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the higher weight range
        double width = 100, length = 150, weight = 1.34;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.80, postalRate);
    }

    @Test
    public void testStandardHigherPostalRateMillimetersGrams() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the higher weight range
        double width = 100, length = 150, weight = 42.5;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.80, postalRate);
    }

    @Test
    public void testStandardHigherPostalRateInchesGrams() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the higher weight range
        double width = 5.03, length = 9.4, weight = 38;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.80, postalRate);
    }
}
