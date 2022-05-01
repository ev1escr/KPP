package com.eviescr.controller;

import com.eviescr.entity.CalculateResult;
import com.eviescr.entity.RequestParams;
import com.eviescr.service.CalculateService;
import com.eviescr.service.impl.CalculateServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calculation")
public class CalculatorController {

    private static Logger logger = LogManager.getLogger(CalculatorController.class);

    private final CalculateService service;

    public CalculatorController(CalculateServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public CalculateResult getCalculation(@RequestParam("num1") Double num1,
                                          @RequestParam("num2") Double num2) {
        logger.info("Class: Calculator; Method: getCalculation; params: " + num1 + ", " +  num2 + ";");
        return service.getCalculation(new RequestParams(num1, num2));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CalculateResult> getAllCalculations() {
        logger.info("Class: Calculator; Method: getAllCalculations;");
        return service.getAll();
    }
}
