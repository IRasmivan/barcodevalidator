package com.royalmail.barcodevalidator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The Class ValidatorServiceImpl.
 */
@Service
public class ValidatorServiceImpl implements ValidatorService{

    @Value("${check.digit.calculator.values}")
    private String checkDigitCalculatorValues;

    @Value("${checkdigit.moduloValue}")
    private Integer moduloValue;

    Logger logger= LoggerFactory.getLogger(ValidatorServiceImpl.class);


    /**
     * isValidBarcode - This method validates barcode
     * @param barcode
     * @return
     */
    @Override
    public boolean isValidBarcode(String barcode) {

        try {

            List<Integer> digits = new ArrayList<>();
            List<Integer> checkDigits = new ArrayList<>(getCheckDigitCalculatorValues());

            if (barcode != null && barcode.length() != 13) return false;

            digits=validateBarcode(barcode);

            if (digits!=null && digits.size() != 9) return false;

            if (compuateCheckDigit(calculateSum(digits, checkDigits)) == digits.get(8)) return true;
            return false;

        }catch(Exception e){
            logger.error("Error in processing barcode {}",e.getMessage());
            return false;
        }

    }

    private List<Integer> getCheckDigitCalculatorValues() {
        String[] checkDigitArray = checkDigitCalculatorValues.split("");
        return convertArrayStringtoListInteger(checkDigitArray);
    }


    private int compuateCheckDigit(int sum) {
        int checkDigit;
        int mod=Integer.valueOf(moduloValue);

        checkDigit=mod - (sum % mod);
        checkDigit=(checkDigit==mod-1 || checkDigit==mod)? (checkDigit==10 ? 0 : 1) : checkDigit;

        return checkDigit;
    }

    private int calculateSum(List<Integer> digits, List<Integer> checkDigits) {
        int digitNo=0;
        int sum=0;

        for(int i=0;i<digits.size()-1;i++){
            sum+=digits.get(digitNo)*checkDigits.get(digitNo);
            digitNo++;
        }

        return sum;
    }

    private List<Integer> validateBarcode(String barcode) {

        String regex="(^[A-Z]{2})([0-9]{9})([GB]{2})";
        Pattern p=Pattern.compile(regex);

        Matcher m=p.matcher(barcode);

        if(m.matches()){
            return convertArrayStringtoListInteger(barcode.replaceAll(regex,"$2").split(""));
        }else{
            return Collections.emptyList();
        }

    }

    private List<Integer> convertArrayStringtoListInteger(String[] inputString){
        return Arrays.asList(inputString).stream().mapToInt(i -> Integer.valueOf(i)).boxed().collect(Collectors.toList());
    }
}
