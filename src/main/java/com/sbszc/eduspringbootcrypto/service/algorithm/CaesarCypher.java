package com.sbszc.eduspringbootcrypto.service.algorithm;

import com.sbszc.eduspringbootcrypto.service.algorithm.validation.CaesarCypherValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CaesarCypher {

    @Autowired
    private CaesarCypherValidator validator;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String encrypt(String alphabet, String plaintext, int shift) {
        logger.info("encrypt() alphabet={} plaintext={} shift={}", alphabet, plaintext, shift);

        validator.validateEncryption(alphabet, plaintext, shift);

        int alphabetLenght = alphabet.length();
        char[] auxChars = plaintext.toCharArray();

        for (int i = 0; i < auxChars.length; i++) {
            int position = alphabet.indexOf(auxChars[i]);

            int shiftedPosition = -1;
            if (position + shift < alphabetLenght) {
                shiftedPosition = position + shift;
            } else {
                shiftedPosition = (position + shift) % alphabetLenght;
            }

            auxChars[i] = alphabet.charAt(shiftedPosition);
        }

        return String.valueOf(auxChars);
    }

    public String decrypt(String alphabet, String cyphertext, int shift) {
        logger.info("decrypt() alphabet={} cyphertext={} shift={}", alphabet, cyphertext, shift);

        validator.validateDecryption(alphabet, cyphertext, shift);

        int alphabetLenght = alphabet.length();
        char[] auxChars = cyphertext.toCharArray();

        for (int i = 0; i < auxChars.length; i++) {
            int position = alphabet.indexOf(auxChars[i]);

            int shiftedPosition = -1;
            if (position - shift >= 0) {
                shiftedPosition = position - shift;
            } else {
                if (position - shift == alphabetLenght) {
                    shiftedPosition = alphabetLenght - 1;
                } else {
                    shiftedPosition = alphabetLenght + ((position - shift) % alphabetLenght);
                }
            }

            auxChars[i] = alphabet.charAt(shiftedPosition);
        }

        return String.valueOf(auxChars);
    }
}
