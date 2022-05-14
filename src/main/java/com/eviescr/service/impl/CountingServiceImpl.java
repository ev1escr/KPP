package com.eviescr.service.impl;

import com.eviescr.service.CountingService;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CountingServiceImpl implements CountingService {

    private Integer count = 0;

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void increment() {
        count++;
    }
}
