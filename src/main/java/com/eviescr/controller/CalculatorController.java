package com.eviescr.controller;

import com.eviescr.dto.CalculateResultDto;
import com.eviescr.service.CalculateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CalculatorController {
    private static Logger logger = LogManager.getLogger(CalculatorController.class);

    private final CalculateService service;

    public CalculatorController(CalculateService service) {
        this.service = service;
    }

    @GetMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public CalculateResultDto getCalculation(@RequestParam("num1") Double num1,
                                             @RequestParam("num2") Double num2) {
        logger.info("Class: Calculator; Method: CalculateSum, CalculateMultiply; params: num1, num2;");
        return service.getCalculation(num1, num2);
    }
}
