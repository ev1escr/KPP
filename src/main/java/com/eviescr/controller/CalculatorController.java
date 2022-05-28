package com.eviescr.controller;

import com.eviescr.entity.CalculateResult;
import com.eviescr.entity.RequestParams;
import com.eviescr.entity.ResultDto;
import com.eviescr.exception.NoSuchRecordException;
import com.eviescr.service.CalculateService;
import com.eviescr.service.CountingService;
import com.eviescr.service.impl.CalculateServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
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
    public CalculateResult getCalculation(@RequestParam("num1") Double num1,
                                          @RequestParam("num2") Double num2) {
        logger.info("Class: Calculator; Method: getCalculation; params: " + num1 + ", " + num2 + ";");
        synchronized (countingService) {
            countingService.increment();
        }
        return service.getCalculation(new RequestParams(num1, num2));
    }

    @PostMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> binaryBulkParams(@Valid @RequestBody List<RequestParams> bodyList) {
        ResultDto dto = new ResultDto();
        if (bodyList.isEmpty()) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        List<CalculateResult> resultList = new LinkedList<>();
        bodyList.forEach((currentElement) -> {
            resultList.add(service.getCalculation(currentElement));
        });
        dto.setList(resultList);
        logger.info("Successfully postMapping");
        dto.setMinSum(resultList.stream().mapToDouble(CalculateResult::getSum).min()
                .orElseThrow(() -> new NoSuchRecordException("There is nothing to compare")));
        dto.setMaxSum(resultList.stream().mapToDouble(CalculateResult::getSum).max()
                .orElseThrow(() -> new NoSuchRecordException("There is nothing to compare")));
        dto.setAvrSum(resultList.stream().mapToDouble(CalculateResult::getSum).average()
                .orElseThrow(() -> new NoSuchRecordException("There is nothing to compare")));

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/cache")
    @ResponseStatus(HttpStatus.OK)
    public List<CalculateResult> getAllCalculations() {
        logger.info("Class: Calculator; Method: getAllCalculations;");
        return service.getAll();
    }

    @GetMapping(value = "/counter")
    public String getCounter() {
        synchronized (countingService) {
            return countingService.getCount() + " requests were sent";
        }
    }
}
