package com.example.tf.fft.algorithm.WindowFucntions;

public class HannWindow extends WindowFunction {

    public HannWindow(int n){
        super(n);
    }

    @Override
    protected double windowMultipler(int i, int n) {
        return (0.5*(1 - Math.cos((2 * Math.PI * i) / (this.n-1))));
    }


}
