package com.eviescr.controller;

import com.eviescr.dto.CalculateResultDto;
import com.eviescr.service.CalculateService;
import com.eviescr.service.CountingService;
import com.eviescr.service.impl.CalculateServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/calculation")
public class CalculatorController {

    private static final Logger logger = LogManager.getLogger(CalculatorController.class);

    private final CalculateService service;
    private final CountingService countingService;

    public CalculatorController(CalculateServiceImpl service, CountingService countingService) {
        this.service = service;
        this.countingService = countingService;
    }

    @GetMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public CalculateResultDto getCalculation(@RequestParam("num1") Double num1,
                                             @RequestParam("num2") Double num2) {
        logger.info("Class: Calculator; Method: getCalculation; params: " + num1 + ", " + num2 + ";");
        synchronized (countingService) {
            countingService.increment();
        }
        return service.getCalculation(num1, num2);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CalculateResultDto saveCalculation(@RequestBody @Valid CalculateResultDto calculateResultDto) {
        logger.info("Class: Calculator; Method: saveCalculation; RequestBody: " + calculateResultDto);
        synchronized (countingService) {
            countingService.increment();
        }
        return service.save(calculateResultDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CalculateResultDto> getAllCalculations() {
        logger.info("Class: Calculator; Method: getAllCalculations;");
        synchronized (countingService) {
            countingService.increment();
        }
        return service.getAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CalculateResultDto updateCalculation(@RequestBody @Valid CalculateResultDto calculateResultDto) {
        logger.info("Class: Calculator; Method: updateCalculation; RequestBody: " + calculateResultDto);
        synchronized (countingService) {
            countingService.increment();
        }
        return service.update(calculateResultDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CalculateResultDto getCalculationById(@PathVariable("id") Long id) {
        logger.info("Class: Calculator; Method: getCalculationByid; PathVariable: " + id);
        synchronized (countingService) {
            countingService.increment();
        }
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        synchronized (countingService) {
            countingService.increment();
        }
        service.deleteById(id);
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public int getCount() {
        return countingService.getCount();
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CalculateResultDto> saveAllCalculations(@RequestBody List<CalculateResultDto> calculateResultDtos) {
        return service.saveAll(calculateResultDtos);
    }
}
