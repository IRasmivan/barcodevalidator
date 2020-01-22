package com.royalmail.barcodevalidator.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ValidatorServiceTest {

    @Autowired
    ValidatorService validatorService;

    @DisplayName("Test barcode validator API for all valid inputs")
    @Test
    public void validBarcodeTest(){
        assertTrue(validatorService.isValidBarcode("AB473124829GB"));
        assertTrue(validatorService.isValidBarcode("AC473124829GB"));
        assertTrue(validatorService.isValidBarcode("ZZ473124829GB"));
        assertTrue(validatorService.isValidBarcode("ZZ000000014GB"));
    }

    @DisplayName("Test barcode validator API for all invalid inputs")
    @Test
    public void invalidBarcodeTest(){
        assertFalse(validatorService.isValidBarcode("AB473124820GR"));
        assertFalse(validatorService.isValidBarcode("AB473124828GR"));
        assertFalse(validatorService.isValidBarcode("AB473124829GR"));
        assertFalse(validatorService.isValidBarcode("AC473124829gb"));
        assertFalse(validatorService.isValidBarcode("ab473124829GB"));
    }

    @DisplayName("Test barcode validator API for null input")
    @Test
    public void nullBarcodeTest(){
        assertFalse(validatorService.isValidBarcode(null));
    }


}
