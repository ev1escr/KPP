package com.eviescr.service;

import java.util.concurrent.atomic.AtomicInteger;

public interface CountingService {

    int getCount();

    void increment();
}
