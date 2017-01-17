package com.aconex.survey;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sbhowmick on 1/17/17.
 */
public class PneumaticSensor {
    private final String sensorName;

    private Set<SensorEntry> entries;

    public PneumaticSensor(String sensorName){
        this.sensorName = sensorName;
        this.entries = new HashSet<>();
    }

    public void addEntry(SensorEntry entry){
        this.entries.add(entry);
    }

    public Set<SensorEntry> getEntries(){
        return this.entries;
    }
}
