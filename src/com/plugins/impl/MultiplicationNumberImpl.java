package com.plugins.impl;

import com.plugins.Plugin;

public class MultiplicationNumberImpl implements Plugin {
    @Override
    public double run(double firstNumber, double secondNumber) {
        return firstNumber * secondNumber;
    }
}