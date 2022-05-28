package com.eviescr.service.impl;

import com.eviescr.service.CountingService;
import org.springframework.stereotype.Service;

@Service
public class CountingServiceImpl implements CountingService {

    private int count;

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void increment() {
        count++;
    }
}
