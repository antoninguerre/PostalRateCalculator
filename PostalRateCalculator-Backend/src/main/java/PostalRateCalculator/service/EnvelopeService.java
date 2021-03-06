package PostalRateCalculator.service;

import PostalRateCalculator.model.Envelope;
import PostalRateCalculator.model.SizeUnit;
import PostalRateCalculator.model.WeightUnit;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EnvelopeService {

    @Transactional
    public double calculatePostalRate(Envelope envelope) {

        widthRangeCheck(envelope.getWidth(), envelope.getSizeUnit());
        lengthRangeCheck(envelope.getLength(), envelope.getSizeUnit());
        weightRangeCheck(envelope.getWeight(), envelope.getWeightUnit());

        SizeUnit envelopeSizeUnit = envelope.getSizeUnit();
        WeightUnit envelopeWeightUnit = envelope.getWeightUnit();

        double envelopeLength = envelope.getLength();
        double envelopeWidth = envelope.getWidth();
        double envelopeWeight = envelope.getWeight();

        // Check if envelope is in the standard format and meets the lower rate requirements
        if (standardLowerCheck(envelopeLength, envelopeWidth, envelopeWeight, envelopeSizeUnit, envelopeWeightUnit)) {
            return 0.49;
        }

        // Check if envelope is in the standard format and meets the higher rate requirements
        if (standardHigherCheck(envelopeLength, envelopeWidth, envelopeWeight, envelopeSizeUnit, envelopeWeightUnit)) {
            return 0.80;
        }

        // Check if envelope is in the non-standard format and meets the lower rate requirements
        if (nonStandardLowerCheck(envelopeLength, envelopeWidth, envelopeWeight, envelopeSizeUnit, envelopeWeightUnit)) {
            return 0.98;
        }

        // Check if envelope is in the non-standard format and meets the higher rate requirements
        if (nonStandardHigherCheck(envelopeLength, envelopeWidth, envelopeWeight, envelopeSizeUnit, envelopeWeightUnit)) {
            return 2.4;
        }

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

    private static void weightRangeCheck(double weight, WeightUnit weightUnit) {
        // Verify that the envelope's weight is between 3 and 500 g if the weight unit is in grams
        if (weightUnit == WeightUnit.Grams && (weight < 3 || 500 < weight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The envelope's weight is not within the allowed range.");
        }

        // Verify that the envelope's weight is between 0.106 and 17.637 oz if the weight unit is in ounces
        if (weightUnit == WeightUnit.Ounces && (weight < 0.106 || 17.637 < weight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The envelope's weight is not within the allowed range.");
        }
    }

    private static boolean standardLowerCheck(double length, double width, double weight, SizeUnit sizeUnit, WeightUnit weightUnit) {
        // Check if the envelope fits the standard lower rate class
        boolean isStandardLower = false ;

        if (sizeUnit == SizeUnit.Inches && weightUnit == WeightUnit.Ounces && (length >= 5.512 && length <= 9.646)
                && (width >= 3.543 && width <= 6.142) && (weight >= 0.106 && weight <= 1.06)) {
            isStandardLower = true;
        }
        if (sizeUnit == SizeUnit.Millimeters && weightUnit == WeightUnit.Ounces && (length >= 140 && length <= 245)
                && (width >= 90 && width <= 156) && (weight >= 0.106 && weight <= 1.06)) {
            isStandardLower = true;
        }
        if (sizeUnit == SizeUnit.Millimeters && weightUnit == WeightUnit.Grams && (length >= 140 && length <= 245)
                && (width >= 90 && width <= 156) && (weight >= 3 && weight <= 30)) {
            isStandardLower = true;
        }
        if (sizeUnit == SizeUnit.Inches && weightUnit == WeightUnit.Grams && (length >= 5.512 && length <= 9.646)
                && (width >= 3.543 && width <= 6.142) && (weight >= 3 && weight <= 30)) {
            isStandardLower = true;
        }
        return isStandardLower;
    }

    private static boolean standardHigherCheck(double length, double width, double weight, SizeUnit sizeUnit, WeightUnit weightUnit) {
        // Check if the envelope fits the standard higher rate class
        boolean isStandardHigher = false ;

        if (sizeUnit == SizeUnit.Inches && weightUnit == WeightUnit.Ounces && (length >= 5.512 && length <= 9.646)
                && (width >= 3.543 && width <= 6.142) && (weight > 1.06 && weight <= 1.764)) {
            isStandardHigher = true;
        }
        if (sizeUnit == SizeUnit.Millimeters && weightUnit == WeightUnit.Ounces && (length >= 140 && length <= 245)
                && (width >= 90 && width <= 156) && (weight > 1.06 && weight <= 1.764)) {
            isStandardHigher = true;
        }
        if (sizeUnit == SizeUnit.Millimeters && weightUnit == WeightUnit.Grams && (length >= 140 && length <= 245)
                && (width >= 90 && width <= 156) && (weight > 30 && weight <= 50)) {
            isStandardHigher = true;
        }
        if (sizeUnit == SizeUnit.Inches && weightUnit == WeightUnit.Grams && (length >= 5.512 && length <= 9.646)
                && (width >= 3.543 && width <= 6.142) && (weight > 30 && weight <= 50)) {
            isStandardHigher = true;
        }
        return isStandardHigher;
    }

    private static boolean nonStandardLowerCheck(double length, double width, double weight, SizeUnit sizeUnit, WeightUnit weightUnit) {
        // Check if the envelope fits the standard lower rate class
        boolean isNonStandardLower = false ;

        if (sizeUnit == SizeUnit.Inches && weightUnit == WeightUnit.Ounces && (length > 9.646 && length <= 14.961)
                && (width > 6.142 && width <= 10.630) && (weight > 1.764 && weight <= 3.527)) {
            isNonStandardLower = true;
        }
        if (sizeUnit == SizeUnit.Millimeters && weightUnit == WeightUnit.Ounces && (length > 245 && length <= 380)
                && (width > 156 && width <= 270) && (weight > 1.764 && weight <= 3.527)) {
            isNonStandardLower = true;
        }
        if (sizeUnit == SizeUnit.Millimeters && weightUnit == WeightUnit.Grams && (length > 245 && length <= 380)
                && (width > 156 && width <= 270) && (weight > 50 && weight <= 100)) {
            isNonStandardLower = true;
        }
        if (sizeUnit == SizeUnit.Inches && weightUnit == WeightUnit.Grams && (length > 9.646 && length <= 14.961)
                && (width > 6.142 && width <= 10.630) && (weight > 50 && weight <= 100)) {
            isNonStandardLower = true;
        }
        return isNonStandardLower;
    }

    private static boolean nonStandardHigherCheck(double length, double width, double weight, SizeUnit sizeUnit, WeightUnit weightUnit) {
        // Check if the envelope fits the standard lower rate class
        boolean isNonStandardHigher = false ;

        if (sizeUnit == SizeUnit.Inches && weightUnit == WeightUnit.Ounces && (length > 9.646 && length <= 14.961)
                && (width > 6.142 && width <= 10.630) && (weight > 3.527 && weight <= 17.637)) {
            isNonStandardHigher = true;
        }
        if (sizeUnit == SizeUnit.Millimeters && weightUnit == WeightUnit.Ounces && (length > 245 && length <= 380)
                && (width > 156 && width <= 270) && (weight > 3.527 && weight <= 17.637)) {
            isNonStandardHigher = true;
        }
        if (sizeUnit == SizeUnit.Millimeters && weightUnit == WeightUnit.Grams && (length > 245 && length <= 380)
                && (width > 156 && width <= 270) && (weight > 100 && weight <= 500)) {
            isNonStandardHigher = true;
        }
        if (sizeUnit == SizeUnit.Inches && weightUnit == WeightUnit.Grams && (length > 9.646 && length <= 14.961)
                && (width > 6.142 && width <= 10.630) && (weight > 100 && weight <= 500)) {
            isNonStandardHigher = true;
        }
        return isNonStandardHigher;
    }
}
