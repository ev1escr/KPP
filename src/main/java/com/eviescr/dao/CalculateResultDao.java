package com.eviescr.dao;

import java.util.List;

public interface CalculateResultDao<K, V> {

    V findByKey(K key);

    List<V> findAll();

    V save(K key, V value);

    V deleteByKey(K key);

    boolean existsByKey(K key);
}
