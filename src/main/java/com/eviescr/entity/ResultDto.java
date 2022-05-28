package com.eviescr.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultDto {
    @JsonProperty
    private List<CalculateResult> list;
    @JsonProperty
    private Double minSum;
    @JsonProperty
    private Double maxSum;
    @JsonProperty
    private Double avrSum;

    public void setList(List<CalculateResult> list) {
        this.list = list;
    }

    public void setMinSum(Double minSum) {
        this.minSum = minSum;
    }

    public void setMaxSum(Double maxSum) {
        this.maxSum = maxSum;
    }

    public void setAvrSum(Double avrSum) {
        this.avrSum = avrSum;
    }
}