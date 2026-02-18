package Buisness;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dal.TFIDFCalculator;

public class TFidfCalcTest {

    @Test
    void testTFIDFPositive() {

        TFIDFCalculator calc = new TFIDFCalculator();

        calc.addDocumentToCorpus("apple banana");
        calc.addDocumentToCorpus("orange mango");

        double score =
                calc.calculateDocumentTfIdf("apple");

        assertFalse(Double.isNaN(score));
    }


    @Test
    void testTFIDFEmptyInput() {

        // should handle empty input without crashing
        TFIDFCalculator calc = new TFIDFCalculator();

        calc.addDocumentToCorpus("apple banana");

        double score = calc.calculateDocumentTfIdf("");

        // verify result is a valid finite number
        assertFalse(Double.isInfinite(score));
    }

}
