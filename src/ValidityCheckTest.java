import org.junit.Test;

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
    public void testValid12DigitsFormatWithDelimitor() {
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
    public void testInvalidPersonalNumber() {
        assertFalse(Validator.runValidityCheck("201701272394"));
    }

    @Test
    public void testInvalidYear12DigitsFormat() {
        assertFalse(Validator.runValidityCheck("159610163587"));
    }

    @Test
    public void testInvalidControlNumber() {
        assertFalse(Validator.runValidityCheck("9610163588"));
    }

    @Test
    public void testValidSamordningsNumber() {
        assertTrue(Validator.runValidityCheck("190910799824"));
    }

    @Test
    public void testValidOrganisationsNumber() {
        assertTrue(Validator.runValidityCheck("16556601-6399"));
    }

    @Test
    public void testAnotherValidOrganisationsNumber() {
        assertTrue(Validator.runValidityCheck("556614-3185"));
    }







}
