package com.example.getwellapp;

public class medicin {
    private String name;
    private String amountTaken;
    private String frequency;

    public medicin(String name, String amountTaken, String frequency) {
        this.name = name;
        this.amountTaken = amountTaken;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public String getAmountTaken() {
        return amountTaken;
    }

    public String getFrequency() {
        return frequency;
    }
}