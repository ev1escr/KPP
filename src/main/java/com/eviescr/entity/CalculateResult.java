package com.eviescr.entity;

public class CalculateResult {
    private Double sum;
    private Double multiply;

    public CalculateResult(Double sum, Double multiply) {
        this.sum = sum;
        this.multiply = multiply;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getMultiply() {
        return multiply;
    }

    public void setMultiply(Double multiply) {
        this.multiply = multiply;
    }
}

