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

    public ShortValues applyWindow(ShortValues input){
        short[] items = input.getItemsArray();
        for(int i=0;i<items.length;i++){
            items[i] *= 0.5*(1 - Math.cos((2 * Math.PI * i) / (this.n-1)));
        }
        return  new ShortValues(items);
    }
}

