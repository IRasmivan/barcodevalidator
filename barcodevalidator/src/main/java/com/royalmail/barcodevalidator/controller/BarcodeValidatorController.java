package com.royalmail.barcodevalidator.controller;

import com.royalmail.barcodevalidator.service.ValidatorService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

/** The Controller BarcodeValidatorController.**/
@RestController
@CrossOrigin
@RequestMapping("/barcode/api/")
public class BarcodeValidatorController {

    @Autowired
    ValidatorService validatorService;

    private static Logger logger= LoggerFactory.getLogger(BarcodeValidatorController.class);

    @CrossOrigin
    @GetMapping(value="/validate/{barcode}")
    @ApiOperation(value="Validate barcode",notes="This API validates given barcode based on rules and return boolean")
    public boolean isValidBarcode(@PathVariable("barcode") String barcode){

        return validatorService.isValidBarcode(barcode);

    }



}
