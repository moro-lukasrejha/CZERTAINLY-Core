package com.czertainly.core.util;

import com.nimbusds.jose.util.Base64URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PublicKey;
import java.security.SecureRandom;

/**
 * This class contains the methods for generating and validating the nonce
 * for all the ACME requests.
 * This class uses SecureRandom class from spring security for Nonce generation
 */
public class AcmeRandomGeneratorAndValidator {

    private static final Logger logger = LoggerFactory.getLogger(AcmeRandomGeneratorAndValidator.class);

    private static final Integer NONCE_SIZE = 32;
    private static final Integer RANDOM_ID_SIZE = 8;
    private static final Integer RANDOM_CHALLANGE_TOKEN_SIZE = 64;

    public static String generateNonce(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] nonceArray = new byte[NONCE_SIZE];
        secureRandom.nextBytes(nonceArray);
        String nonce = Base64URL.encode(nonceArray).toString();
        return nonce;
    }

    public static String generateRandomId(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomArray = new byte[RANDOM_ID_SIZE];
        secureRandom.nextBytes(randomArray);
        String randomId = Base64URL.encode(randomArray).toString();
        return randomId.replace("/", "-");
    }

    public static String generateRandomTokenForValidation(PublicKey publicKey) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomArray = new byte[RANDOM_CHALLANGE_TOKEN_SIZE];
        secureRandom.nextBytes(randomArray);
        String randomId = Base64URL.encode(randomArray).toString();
        return randomId.replace("/","0");
    }
}