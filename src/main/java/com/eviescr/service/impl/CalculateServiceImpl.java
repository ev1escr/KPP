package com.eviescr.service.impl;

import com.eviescr.dao.impl.CalculateResultDaoImpl;
import com.eviescr.entity.CalculateResult;
import com.eviescr.entity.RequestParams;
import com.eviescr.exception.NoSuchRecordException;
import com.eviescr.service.CalculateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateServiceImpl implements CalculateService {

    private final CalculateResultDaoImpl calculateResultDao;

    public CalculateServiceImpl(CalculateResultDaoImpl calculateResultDao) {
        this.calculateResultDao = calculateResultDao;
    }

    @Override
    public CalculateResult getCalculation(RequestParams requestParams) {
        if (calculateResultDao.existsByKey(requestParams)) {
            return calculateResultDao.findByKey(requestParams);
        } else {
            Double num1 = requestParams.getNum1();
            Double num2 = requestParams.getNum2();
            CalculateResult result = new CalculateResult(num1 + num2, num1 * num2);
            calculateResultDao.save(requestParams, result);
            return result;
        }
    }

    @Override
    public CalculateResult getByRequestParams(RequestParams requestParams) {
        if (!calculateResultDao.existsByKey(requestParams)) {
            throw new NoSuchRecordException("CalculateResult for request params " + requestParams + " is not found");
        }
        return calculateResultDao.findByKey(requestParams);
    }

    @Override
    public void deleteByRequestParams(RequestParams requestParams) {
        if (!calculateResultDao.existsByKey(requestParams)) {
            throw new NoSuchRecordException("CalculateResult by key " + requestParams + " not found");
        }
        calculateResultDao.deleteByKey(requestParams);
    }

    @Override
    public List<CalculateResult> getAll() {
        return calculateResultDao.findAll();
    }
}
