package com.oms.sandbox.parallelism.strategy;

import com.oms.sandbox.parallelism.model.Generator;

import java.util.List;

/**
 * Context for the Strategy Pattern.
 * @author omsivanesan
 */

public class GeneratorStrategyContext {

    private GeneratorStrategy generatorStrategy;

    public void setGeneratorStrategy(GeneratorStrategy generatorStrategy) {
        this.generatorStrategy = generatorStrategy;
    }

    public void displayGeneratorCurrentFuelLevelsAndTheirAverage(List<Generator> generators) {
        if (generatorStrategy != null) {
            generatorStrategy.displayFuelLevelsAndTheirAverage(generators);
        }
    }
}
