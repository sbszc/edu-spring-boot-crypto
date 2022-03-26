package com.sbszc.eduspringbootcrypto.controller;

import com.sbszc.eduspringbootcrypto.service.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CryptoController {

    @Autowired
    private CryptoService service;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/encryptUsingCaesarCypher")
    public String encryptUsingCaesarCypher(
            @RequestParam(required = true) String alphabet,
            @RequestParam(required = true) String plaintext,
            @RequestParam(required = true) int shift) {
        logger.info("encryptUsingCaesarCypher() alphabet={} plaintext={} shift={}", alphabet, plaintext,
                shift);

        return service.encryptUsingCaesarCypher(alphabet, plaintext, shift);
    }

    @GetMapping("/decryptUsingCaesarCypher")
    public String decryptUsingCaesarCypher(
            @RequestParam(required = true) String alphabet,
            @RequestParam(required = true) String cyphertext,
            @RequestParam(required = true) int shift) {
        logger.info("decryptUsingCaesarCypher() alphabet={} cyphertext={} shift={}", alphabet, cyphertext,
                shift);

        return service.decryptUsingCaesarCypher(alphabet, cyphertext, shift);
    }

    @GetMapping("/ping")
    public String ping() {
        return "ping";
    }

}
