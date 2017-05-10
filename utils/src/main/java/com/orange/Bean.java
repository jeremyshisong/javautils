package com.orange;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shisong on 16/12/12.
 */
public class Bean {

    private Lock lock = new ReentrantLock();
}
