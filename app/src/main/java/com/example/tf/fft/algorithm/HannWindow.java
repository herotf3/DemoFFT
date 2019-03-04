package com.example.tf.fft.algorithm;

import com.scichart.core.model.ShortValues;

import static java.lang.Math.cos;

public class HannWindow extends WindowFucntion {

    public HannWindow(int n){
        super(n);
    }

    @Override
    public ShortValues applyWindow(ShortValues x) {
        double w = 0.5*(1-cos(this.TwoPi_N / (n-1)));
        short[] items = x.getItemsArray();
        for(int i=0;i<items.length;i++){
            items[i] *= w;
        }
        return  new ShortValues(items);
    }
}
