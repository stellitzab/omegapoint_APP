import org.junit.Test;

import java.text.Normalizer;

import static org.junit.Assert.*;

public class ValidityCheckTest {

    @Test
    public void testValid10DigitsFormat() {
        assertTrue(Validator.runValidityCheck("9610163587"));
    }

    @Test
    public void testValid10DigitsFormatWithDelimitor() {
        assertTrue(Validator.runValidityCheck("961016-3587"));
    }

    @Test
    public void testValid12DigitsFor matWithDelimitor() {
        assertTrue(Validator.runValidityCheck("19961016-3587"));
    }

    @Test
    public void testValid10DigitsFormatWithDelimitorPlus() {
        assertTrue(Validator.runValidityCheck("961016+3587"));
    }

    @Test
    public void testValid12DigitsFormat() {
        assertTrue(Validator.runValidityCheck("199610163587"));
    }

    @Test
    public void testInvalidDay10DigitsFormat() {
        assertFalse(Validator.runValidityCheck("9610353587"));
    }

    @Test
    public void testInvalidMonth12DigitsFormat() {
        assertFalse(Validator.runValidityCheck("199613163587"));
    }

    @Test
    public void testInvalidYear12DigitsFormat() {
        assertFalse(Validator.runValidityCheck("159610163587"));
    }

    @Test
    public void testInvalidControlNumber() {
        assertTrue(Validator.runValidityCheck("9610163588"));
    }







}
