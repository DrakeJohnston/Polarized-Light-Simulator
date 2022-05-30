package com.drake.app;


public class LPCore {
    public double test(double d){
        return Math.floor(Math.sin(Math.toRadians(d)) * 100) / 100;
    }

    public double intensitySolver(Double angle, Double intensity){
        double pA = Math.pow(Math.cos(Math.toRadians(angle)), 2);
        return  Math.floor((pA * intensity)*100) / 100;
    }
}
