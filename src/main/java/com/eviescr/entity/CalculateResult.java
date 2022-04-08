package com.eviescr.entity;

public class CalculateResult {
    private Long id;
    private Double num1;
    private Double num2;

    public CalculateResult(Long id, Double num1, Double num2) {
        this.id = id;
        this.num1 = num1;
        this.num2 = num2;
    }

    public Double getNum1() {
        return num1;
    }

    public void setNum1(Double num1) {
        this.num1 = num1;
    }

    public Double getNum2() {
        return num2;
    }

    public void setNum2(Double num2) {
        this.num2 = num2;
    }

    public Double getSum() {
        return num1 + num2;
    }

    public Double getMultiply() {
        return num1 * num2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CalculateResult{" +
                "id=" + id +
                ", num1=" + num1 +
                ", num2=" + num2 +
                '}';
    }
}

