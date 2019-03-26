package com.example.tf.fft.algorithm.WindowFucntions;

import com.scichart.core.model.ShortValues;

/**
 * Created by macbook on 3/4/19.
 */

public abstract class WindowFunction {
    protected int n;

    public WindowFunction(int n){
        this.n = n;
    }

    final public ShortValues applyWindow(ShortValues input){
        short[] items = input.getItemsArray();
        for(int i=0;i<items.length;i++){
            items[i] *= windowMultipler(i,n);
        }
        return  new ShortValues(items);
    }

    protected abstract double windowMultipler(int i, int n);
}

