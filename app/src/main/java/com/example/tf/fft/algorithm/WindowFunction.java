package com.example.tf.fft.algorithm;

import com.scichart.core.model.ShortValues;

/**
 * Created by macbook on 3/4/19.
 */

public abstract class WindowFunction {
    protected int n;

    public WindowFunction(int n){
        this.n = n;
    }

    public abstract ShortValues applyWindow(ShortValues input);
}

