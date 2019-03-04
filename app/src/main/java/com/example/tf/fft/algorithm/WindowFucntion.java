package com.example.tf.fft.algorithm;

import com.scichart.core.model.ShortValues;

/**
 * Created by macbook on 3/4/19.
 */

public abstract class WindowFucntion {
    protected int n;
    protected final double TwoPi_N;    // constant to save computational time.  = 2*PI / N

    public WindowFucntion(int n){
        this.n = n;
        TwoPi_N=Math.PI * 2 / n;
    }

    public abstract ShortValues applyWindow(ShortValues input);
}

