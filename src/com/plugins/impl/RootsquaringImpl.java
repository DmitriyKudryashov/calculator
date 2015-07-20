package com.plugins.impl;

import com.plugins.Plugin;

public class RootsquaringImpl implements Plugin{

    @Override
    public double run(double firstNumber, double secondNumber) {
        return Math.sqrt(firstNumber);
    }
}
