package com.sbszc.eduspringbootcrypto.service.algorithm.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CaesarCypherValidator {

    private final String BAD_ALPHABET = "null or empty alphabet";
    private final String BAD_CYPHERTEXT = "null or empty cyphertext";
    private final String BAD_PLAINTEXT = "null or empty plaintext";
    private final String BAD_SHIFT = "negative shift";
    private final String NONEXISTENT_CYPHERTEXT_CHAR = "some cyphertext's char isn't contained by the alphabet";
    private final String NONEXISTENT_PLAINTEXT_CHAR = "some plaintext's char isn't contained by the alphabet";
    private final String REPEATED_ALPHABET_CHAR = "some alphabet's char is repetead";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void validateEncryption(String alphabet, String plaintext, int shift) throws RuntimeException {
        logger.info("validateEncryption() alphabet={} plaintext={} shift={}", alphabet, plaintext, shift);

        if (alphabet == null || alphabet.length() == 0) {
            throw new RuntimeException(BAD_ALPHABET);
        }

        if (plaintext == null || plaintext.length() == 0) {
            throw new RuntimeException(BAD_PLAINTEXT);
        }

        if (shift < 0) {
            throw new RuntimeException(BAD_SHIFT);
        }

        char[] plaintextChars = plaintext.toCharArray();
        for (int i = 0; i < plaintextChars.length; i++) {
            boolean isContained = false;
            for (int j = 0; j < alphabet.length(); j++) {
                if (plaintextChars[i] == alphabet.charAt(j)) {
                    isContained = true;
                }
            }

            if (!isContained) {
                throw new RuntimeException(NONEXISTENT_PLAINTEXT_CHAR);
            }
        }

        char[] alphabetChars = alphabet.toCharArray();
        for (int i = 0; i < alphabetChars.length; i++) {
            boolean isRepeated = false;
            for (int j = 0; j < alphabetChars.length; j++) {
                if (i != j && alphabetChars[i] == alphabetChars[j]) {
                    isRepeated = true;
                }
            }

            if (isRepeated) {
                throw new RuntimeException(REPEATED_ALPHABET_CHAR);
            }
        }

    }

    public void validateDecryption(String alphabet, String cyphertext, int shift) throws RuntimeException {
        logger.info("validateDecryption() alphabet={} cyphertext={} shift={}", alphabet, cyphertext, shift);

        if (alphabet == null || alphabet.length() == 0) {
            throw new RuntimeException(BAD_ALPHABET);
        }

        if (cyphertext == null || cyphertext.length() == 0) {
            throw new RuntimeException(BAD_CYPHERTEXT);
        }

        if (shift < 0) {
            throw new RuntimeException(BAD_SHIFT);
        }

        char[] cyphertextChars = cyphertext.toCharArray();
        for (int i = 0; i < cyphertextChars.length; i++) {
            boolean isContained = false;
            for (int j = 0; j < alphabet.length(); j++) {
                if (cyphertextChars[i] == alphabet.charAt(j)) {
                    isContained = true;
                }
            }

            if (!isContained) {
                throw new RuntimeException(NONEXISTENT_CYPHERTEXT_CHAR);
            }
        }

        char[] alphabetChars = alphabet.toCharArray();
        for (int i = 0; i < alphabetChars.length; i++) {
            boolean isRepeated = false;
            for (int j = 0; j < alphabetChars.length; j++) {
                if (i != j && alphabetChars[i] == alphabetChars[j]) {
                    isRepeated = true;
                }
            }

            if (isRepeated) {
                throw new RuntimeException(REPEATED_ALPHABET_CHAR);
            }
        }
    }
}