package com.eviescr.entity;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class RequestParams {

    @NotNull
    private Double num1;

    @NotNull
    private Double num2;

    public RequestParams(Double num1, Double num2) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestParams that = (RequestParams) o;
        return num1.equals(that.num1) && num2.equals(that.num2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num1, num2);
    }

    @Override
    public String toString() {
        return "{num1=" + num1 +
                ", num2=" + num2 +
                '}';
    }
}
