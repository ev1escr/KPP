package com.eviescr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateController {

    @GetMapping("/sum")
    @ResponseStatus(HttpStatus.OK)
    public double calculateSum(@RequestParam("firstNum") double num1,
                               @RequestParam("secondNum") double num2) {
        return num1 + num2;
    }

    @GetMapping("/multiply")
    @ResponseStatus(HttpStatus.OK)
    public double calculateMultiply(@RequestParam("firstNum") double num1,
                                    @RequestParam("secondNum") double num2) {
        return num1 * num2;
    }
}
