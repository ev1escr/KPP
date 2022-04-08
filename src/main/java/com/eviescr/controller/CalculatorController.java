package com.eviescr.controller;

import com.eviescr.dto.CalculateResultDto;
import com.eviescr.service.CalculateService;
import com.eviescr.service.impl.CalculateServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


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
    public CalculateResultDto getCalculation(@RequestParam("num1") Double num1,
                                             @RequestParam("num2") Double num2) {
        logger.info("Class: Calculator; Method: getCalculation; params: " + num1 + ", " +  num2 + ";");
        return service.getCalculation(num1, num2);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CalculateResultDto saveCalculation(@RequestBody @Valid CalculateResultDto calculateResultDto) {
        logger.info("Class: Calculator; Method: saveCalculation; RequestBody: " + calculateResultDto);
        return service.save(calculateResultDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CalculateResultDto> getAllCalculations() {
        logger.info("Class: Calculator; Method: getAllCalculations;");
        return service.getAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CalculateResultDto updateCalculation(@RequestBody @Valid CalculateResultDto calculateResultDto) {
        logger.info("Class: Calculator; Method: updateCalculation; RequestBody: " + calculateResultDto);
        return service.update(calculateResultDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CalculateResultDto getCalculationById(@PathVariable("id") Long id) {
        logger.info("Class: Calculator; Method: getCalculationByid; PathVariable: " + id);
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
}
