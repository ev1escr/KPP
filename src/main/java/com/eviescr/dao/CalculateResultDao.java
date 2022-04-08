package com.eviescr.dao;

import com.eviescr.dto.CalculateResultDto;
import com.eviescr.entity.CalculateResult;
import com.eviescr.exception.NoSuchRecordException;

import java.util.List;

public interface CalculateResultDao<K, T> {

    K findByKey(T id);

    List<K> findAll();

    K save(K k);

    K deleteByKey(T t);

    boolean existsByKey(T id);
}
