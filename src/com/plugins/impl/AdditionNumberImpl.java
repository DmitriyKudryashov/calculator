package com.plugins.impl;


import com.plugins.Plugin;

public class AdditionNumberImpl implements Plugin {
    @Override
    public double run(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }
}
