package com.eviescr.service;

import com.eviescr.entity.RequestParams;
import com.eviescr.entity.CalculateResult;

import java.util.List;

public interface CalculateService {
    CalculateResult getCalculation(RequestParams requestParams);

    CalculateResult getByRequestParams(RequestParams id);

    void deleteByRequestParams(RequestParams id);

    List<CalculateResult> getAll();
}
