**********************************************************************

## 1.1 Task : 

Build a RESTful API service that will validate an S10 barcode. The expected deliverable should be
Java-based with one endpoint (/validate).
The endpoint must take as input a single 1D barcode value (e.g. AA473124829GB) and return a
boolean response.
Your service should validate the four parts of the barcode:
1. Prefix in the range AA to ZZ (only uppercase alpha characters).
2. Eight digit serial number in the range 00 000 000 to 99 999 999 (only digits (0-9), but values
could be left padded with zero’s, e.g. 00000001 = 1, etc.)
3. Check digit as per the check digit validation described above.
4. Country code is GB. We only validating locally produced barcodes, anything is not
recognised.
valid barcode example:   AA473124829GB
invalid barcode example: AA473124828GB – incorrect check digit
Use your own discretion to decide how best to build and test the service. Document how to run the
service locally and optionally any assumptions you made.

**********************************************************************


## 1.2 Pre-requisites:

Java

maven

## 1.3 Solution & Assumptions:

1.Service will be available in localhost:8080/barcode/api/validate/{barcode}. I have assumed the input barcode 
to be provided as PathVariable but there are other options as well.
2.I have added an Swagger URL for interacting with API using UI.
3.I believe the values which is used for assigning weights and multiplying against each position value needs to be maintained as an property (so that it gives a flexibility to store it in a secret file and later retrieve it).
Hence I have not hardcoded the weights in application.properties file in the variable check.digit.calculator.values. It can be maintained as check.digit.calculator.values={CHECK.DIGIT.CALCULATOR.VALUE} if this needs to be maintained in a secret file
4. Similarly have also retrieved modulo value in property file so that it can be maintained in a secret file if required
5.Exception handling: In case of invalid barcode being passed and due to which if API fails, it would return a false value. But if required this can be changed to throw an appropriate exception as well.For now I have handled in a way to return false in case of invalid input 

## 1.4 Instructions to run and Interact with API:

Since check digit value and modulo is provided as property , this needs to be passed as input for the app to start

1. mvn spring-boot:run -Dspring-boot.run.arguments="--checkdigit.moduloValue={MODULOVALUE},--check.digit.calculator.values={WEIGHTSVALUE}"

for eg is 1 is modulovalue and weights to be multiplied is 12345678

then

mvn spring-boot:run -Dspring-boot.run.arguments="--checkdigit.moduloValue=1,--check.digit.calculator.values=12345678"

Please note that {{VALUE}} needs to be provided as INTEGER for both property

2. In order to interact with API, we can use

http://localhost:8080/swagger-ui.html#/





**********************************************************************
