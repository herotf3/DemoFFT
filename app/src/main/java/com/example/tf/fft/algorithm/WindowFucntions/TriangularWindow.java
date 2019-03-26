package com.example.tf.fft.algorithm.WindowFucntions;

/**
 * Created by macbook on 3/4/19.
 */

public class TriangularWindow extends WindowFunction {
    public TriangularWindow(int n) {
        super(n);
    }

    @Override
    protected double windowMultipler(int i, int n) {
        return 1 - Math.abs( 2 * (i - 0.5*(n-1)) / n );
    }
}
