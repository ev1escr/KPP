package com.eviescr.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CalculateResultListDto {

    @NotNull
    private List<CalculateResultDto> calculateResultDtoList;
    private Double avg;
    private CalculateResultDto minSum;
    private CalculateResultDto maxSum;

    public CalculateResultListDto() {
    }
    public CalculateResultListDto(List<CalculateResultDto> calculateResultDtoList) {
        this.calculateResultDtoList = calculateResultDtoList;
    }

    public List<CalculateResultDto> getCalculateResultDtoList() {
        return calculateResultDtoList;
    }

    public void setCalculateResultDtoList(List<CalculateResultDto> calculateResultDtoList) {
        this.calculateResultDtoList = calculateResultDtoList;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public CalculateResultDto getMinSum() {
        return minSum;
    }

    public void setMinSum(CalculateResultDto minSum) {
        this.minSum = minSum;
    }

    public CalculateResultDto getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(CalculateResultDto maxSum) {
        this.maxSum = maxSum;
    }
}
