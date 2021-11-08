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
import static org.junit.jupiter.api.Assertions.assertTrue;
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

        //when(envelopeRepository.save(any(Envelope.class))).thenAnswer(returnParameterAsAnswer);
    }



    //------------------------
    // WIDTH TESTS
    //------------------------

    @Test
    public void testEnvelopeTooSmallWidthInMillimeters() {
        // Initialize variables (including a too small width) and create an instance of an envelope
        double width = 89, length = 200, weight = 20;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        Exception thrownException = assertThrows(ResponseStatusException.class, () ->
                envelopeService.calculatePostalRate(envelope));

        assertTrue(thrownException.getMessage().contains("The envelope's width is not within the allowed range."));
    }

    @Test
    public void testEnvelopeTooSmallWidthInInches() {
        // Initialize variables (including a too small width) and create an instance of an envelope
        double width = 3.4, length = 8, weight = 20;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        Exception thrownException = assertThrows(ResponseStatusException.class, () ->
                envelopeService.calculatePostalRate(envelope));

        assertTrue(thrownException.getMessage().contains("The envelope's width is not within the allowed range."));
    }

    @Test
    public void testEnvelopeTooLargeWidthInMillimeters() {
        // Initialize variables (including a too large width) and create an instance of an envelope
        double width = 271, length = 200, weight = 20;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        Exception thrownException = assertThrows(ResponseStatusException.class, () ->
                        envelopeService.calculatePostalRate(envelope));

        assertTrue(thrownException.getMessage().contains("The envelope's width is not within the allowed range."));
    }

    @Test
    public void testEnvelopeTooLargeWidthInInches() {
        // Initialize variables (including a too large width) and create an instance of an envelope
        double width = 10.7, length = 8, weight = 20;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        Exception thrownException = assertThrows(ResponseStatusException.class, () ->
                envelopeService.calculatePostalRate(envelope));

        assertTrue(thrownException.getMessage().contains("The envelope's width is not within the allowed range."));
    }



    //------------------------
    // LENGTH TESTS
    //------------------------

    @Test
    public void testEnvelopeTooSmallLengthInMillimeters() {
        // Initialize variables (including a too small length) and create an instance of an envelope
        double width = 100, length = 139, weight = 20;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        Exception thrownException = assertThrows(ResponseStatusException.class, () ->
                envelopeService.calculatePostalRate(envelope));

        assertTrue(thrownException.getMessage().contains("The envelope's length is not within the allowed range."));
    }

    @Test
    public void testEnvelopeTooSmallLengthInInches() {
        // Initialize variables (including a too small length) and create an instance of an envelope
        double width = 7, length = 5.5, weight = 20;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        Exception thrownException = assertThrows(ResponseStatusException.class, () ->
                envelopeService.calculatePostalRate(envelope));

        assertTrue(thrownException.getMessage().contains("The envelope's length is not within the allowed range."));
    }

    @Test
    public void testEnvelopeTooLargeLengthInMillimeters() {
        // Initialize variables (including a too large width) and create an instance of an envelope
        double width = 100, length = 381, weight = 20;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        Exception thrownException = assertThrows(ResponseStatusException.class, () ->
                envelopeService.calculatePostalRate(envelope));

        assertTrue(thrownException.getMessage().contains("The envelope's length is not within the allowed range."));
    }

    @Test
    public void testEnvelopeTooLargeLengthInInches() {
        // Initialize variables (including a too large width) and create an instance of an envelope
        double width = 7, length = 15, weight = 20;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        Exception thrownException = assertThrows(ResponseStatusException.class, () ->
                envelopeService.calculatePostalRate(envelope));

        assertTrue(thrownException.getMessage().contains("The envelope's length is not within the allowed range."));
    }



    //------------------------
    // WEIGHT TESTS
    //------------------------

    @Test
    public void testEnvelopeTooHeavyInGrams() {
        // Initialize variables (including a too heavy weight) and create an instance of an envelope
        double width = 200, length = 300, weight = 600;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        Exception thrownException = assertThrows(ResponseStatusException.class, () ->
                envelopeService.calculatePostalRate(envelope));

        assertTrue(thrownException.getMessage().contains("The envelope's weight is not within the allowed range."));
    }

    @Test
    public void testEnvelopeTooLightInGrams() {
        // Initialize variables (including a too light weight) and create an instance of an envelope
        double width = 200, length = 300, weight = 2.9;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        Exception thrownException = assertThrows(ResponseStatusException.class, () ->
                envelopeService.calculatePostalRate(envelope));

        assertTrue(thrownException.getMessage().contains("The envelope's weight is not within the allowed range."));
    }

    @Test
    public void testEnvelopeTooHeavyInOunces() {
        // Initialize variables (including a too heavy weight) and create an instance of an envelope
        double width = 200, length = 300, weight = 19.4;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        Exception thrownException = assertThrows(ResponseStatusException.class, () ->
                envelopeService.calculatePostalRate(envelope));

        assertTrue(thrownException.getMessage().contains("The envelope's weight is not within the allowed range."));
    }

    @Test
    public void testEnvelopeTooLightInOunces() {
        // Initialize variables (including a too light weight) and create an instance of an envelope
        double width = 200, length = 300, weight = 0.07;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        Exception thrownException = assertThrows(ResponseStatusException.class, () ->
                envelopeService.calculatePostalRate(envelope));

        assertTrue(thrownException.getMessage().contains("The envelope's weight is not within the allowed range."));
    }

    //------------------------
    // POSTAL RATE TESTS
    //------------------------

    @Test
    public void testStandardLowerPostalRateInchesOunces() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the lower weight range
        double width = 4.0, length = 6.0, weight = 0.800;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.49, postalRate);
    }

    @Test
    public void testStandardLowerPostalRateMillimetersOunces() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the lower weight range
        double width = 100, length = 200, weight = 0.800;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.49, postalRate);
    }

    @Test
    public void testStandardLowerPostalRateMillimetersGrams() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the lower weight range
        double width = 100, length = 200, weight = 20.0;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.49, postalRate);
    }

    @Test
    public void testStandardLowerPostalRateInchesGrams() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the lower weight range
        double width = 4.0, length = 6.0, weight = 20.0;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.49, postalRate);
    }

    @Test
    public void testStandardHigherPostalRateInchesOunces() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the higher weight range
        double width = 4.0, length = 6.0, weight = 1.50;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.80, postalRate);
    }

    @Test
    public void testStandardHigherPostalRateMillimetersOunces() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the higher weight range
        double width = 100, length = 200, weight = 1.50;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.80, postalRate);
    }

    @Test
    public void testStandardHigherPostalRateMillimetersGrams() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the higher weight range
        double width = 100, length = 200, weight = 40.0;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.80, postalRate);
    }

    @Test
    public void testStandardHigherPostalRateInchesGrams() {
        // Check that the outputted postal rate is the correct one for the created standard envelope in the higher weight range
        double width = 4.0, length = 6.0, weight = 40.0;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.80, postalRate);
    }

    @Test
    public void testNonStandardLowerPostalRateInchesOunces() {
        // Check that the outputted postal rate is the correct one for the created non-standard envelope in the lower weight range
        double width = 7.50, length = 12.5, weight = 2.50;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.98, postalRate);
    }

    @Test
    public void testNonStandardLowerPostalRateMillimetersOunces() {
        // Check that the outputted postal rate is the correct one for the created non-standard envelope in the lower weight range
        double width = 200, length = 350, weight = 2.50;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.98, postalRate);
    }

    @Test
    public void testNonStandardLowerPostalRateMillimetersGrams() {
        // Check that the outputted postal rate is the correct one for the created non-standard envelope in the lower weight range
        double width = 200, length = 350, weight = 75.0;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.98, postalRate);
    }

    @Test
    public void testNonStandardLowerPostalRateInchesGrams() {
        // Check that the outputted postal rate is the correct one for the created non-standard envelope in the lower weight range
        double width = 7.5, length = 12.5, weight = 75.0;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(0.98, postalRate);
    }

    @Test
    public void testNonStandardHigherPostalRateInchesOunces() {
        // Check that the outputted postal rate is the correct one for the created non-standard envelope in the higher weight range
        double width = 7.5, length = 12.5, weight = 14.0;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(2.40, postalRate);
    }

    @Test
    public void testNonStandardHigherPostalRateMillimetersOunces() {
        // Check that the outputted postal rate is the correct one for the created non-standard envelope in the higher weight range
        double width = 200, length = 350, weight = 14.0;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Ounces;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(2.40, postalRate);
    }

    @Test
    public void testNonStandardHigherPostalRateMillimetersGrams() {
        // Check that the outputted postal rate is the correct one for the created non-standard envelope in the higher weight range
        double width = 200, length = 350, weight = 400.0;
        SizeUnit sizeUnit = SizeUnit.Millimeters;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(2.40, postalRate);
    }

    @Test
    public void testNonStandardHigherPostalRateInchesGrams() {
        // Check that the outputted postal rate is the correct one for the created non-standard envelope in the higher weight range
        double width = 7.5, length = 12.5, weight = 400.0;
        SizeUnit sizeUnit = SizeUnit.Inches;
        WeightUnit weightUnit = WeightUnit.Grams;
        Envelope envelope = new Envelope(width, length, weight, sizeUnit, weightUnit);

        double postalRate = envelopeService.calculatePostalRate(envelope);

        assertEquals(2.40, postalRate);
    }
}