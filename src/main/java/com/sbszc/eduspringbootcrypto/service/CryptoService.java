package com.sbszc.eduspringbootcrypto.service;

import com.sbszc.eduspringbootcrypto.service.algorithm.CaesarCypher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {

    @Autowired
    private CaesarCypher caesarCypher;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String encryptUsingCaesarCypher(String alphabet, String plaintext, int shift) {
        logger.info("encryptUsingCaesarCypher() alphabet={} plaintext={} shift={}", alphabet, plaintext, shift);

        return caesarCypher.encrypt(alphabet, plaintext, shift);
    }

    public String decryptUsingCaesarCypher(String alphabet, String cyphertext, int shift) {
        logger.info("decryptUsingCaesarCypher() alphabet={} cyphertext={} shift={}", alphabet, cyphertext, shift);

        return caesarCypher.decrypt(alphabet, cyphertext, shift);
    }

}
