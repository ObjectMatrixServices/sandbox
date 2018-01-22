package com.oms.sandbox.parallelism.strategy;

import com.oms.sandbox.parallelism.model.Generator;

import java.util.List;

/**
 * Super class for the Strategies.
 * @author omsivanesan
 */

public abstract class GeneratorStrategy {

    public abstract void displayFuelLevelsAndTheirAverage(List<Generator> generators);

    protected void displayResults(int numberOfGenerators, List<Double> currentFuelLevels, long duration) {
        System.out.println("Current Fuel Levels: " + currentFuelLevels);
        double asDouble = currentFuelLevels.stream()
                                           .mapToDouble(d -> d)
                                           .average()
                                           .orElse(0.0);
        System.out.println("Current Fuel Levels' Average: " + asDouble);
        System.out.printf("Serviced %d generators in %d millis\n", numberOfGenerators, duration);
        System.out.println("**************************************************************************************");
    }
}