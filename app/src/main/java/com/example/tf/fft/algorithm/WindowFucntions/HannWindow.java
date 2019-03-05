package com.example.tf.fft.algorithm;

import com.scichart.core.model.ShortValues;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

public class HannWindow extends WindowFunction {

    public HannWindow(int n){
        super(n);
    }

    @Override
    public ShortValues applyWindow(ShortValues x) {

        short[] items = x.getItemsArray();
        for(int i=0;i<items.length;i++){
            items[i] *= 0.5*(1 - Math.cos((2 * Math.PI * i) / (this.n-1)));
        }
        return  new ShortValues(items);
    }
}
