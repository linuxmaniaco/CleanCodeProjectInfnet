package com.acme.cars.controller;

import com.acme.cars.payload.VersionPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/version")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Liberando CORS para todos os métodos deste controller
public class VersionController {

    @GetMapping
    public VersionPayload getVersion() {
        return new VersionPayload("3.0");
    }

}
