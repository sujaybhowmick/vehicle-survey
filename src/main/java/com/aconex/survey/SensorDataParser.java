package com.aconex.survey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by sbhowmick on 1/24/17.
 */
public class SensorDataParser {

    public static final String INPUT_VALIDATION_REGEX = "^[AaBb][0-9]+$";
    private static final int ENTRIES_FOR_NORTH_DIRECTION = 2;
    private static final int ENTRIES_FOR_SOUTH_DIRECTION = 4;
    private static final int MINIMUM_NUMBER_OF_ENTRIES_NEEDED = 2;

    public static final String SENSOR1_PREFIX = "A";
    public static final String SENSOR2_PREFIX = "B";

    private int currentDay = 0;
    private Date lastEntryTime;


    public List<VehicleEntry> parse(List<String> sensorInput){
        List<VehicleEntry> emptyList = Collections.emptyList();
        List<VehicleEntry> entries = new ArrayList<>();
        lastEntryTime = new Date(0);

        if(isInSufficientEntries(sensorInput, MINIMUM_NUMBER_OF_ENTRIES_NEEDED)){
            return emptyList;
        }

        while(!sensorInput.isEmpty()){
            Direction direction = findDirection(sensorInput.get(0), sensorInput.get(1));
            int numberOfEntriesNeeded = (direction == Direction.SOUTH ? ENTRIES_FOR_SOUTH_DIRECTION : ENTRIES_FOR_NORTH_DIRECTION);
            if(isInSufficientEntries(sensorInput, numberOfEntriesNeeded)){
                return emptyList;
            }

            if(direction == Direction.SOUTH)
                sensorInput = addSouthBoundVehicleEntry(sensorInput, entries);
            else
                sensorInput = addNorthBoundVehicleEntry(sensorInput, entries);
        }
        return entries;
    }

    private List<String> addNorthBoundVehicleEntry(List<String> sensorInput, List<VehicleEntry> entries) {
        String frontAxleInput = sensorInput.get(0);
        String backAxleInput = sensorInput.get(1);

        int frontAxleTime = TimeUtils.parseMillisFromInput(frontAxleInput);
        int rearAxleTime = TimeUtils.parseMillisFromInput(backAxleInput);

        VehicleEntry entry = new VehicleEntry(currentDay, frontAxleTime, rearAxleTime, Direction.NORTH);
        if (!entry.isValid())
            throw new VehicleEntryException("Invalid record : " + entry);
        updateCurrentDay(entry);
        entries.add(entry);
        return sensorInput.subList(2,sensorInput.size());
    }

    private List<String> addSouthBoundVehicleEntry(List<String> sensorInput, List<VehicleEntry> entries) {
        String frontAxleSensor1Input = sensorInput.get(0);
        String frontAxleSensor2Input = sensorInput.get(1);
        String backAxleSensor1Input = sensorInput.get(2);
        String backAxleSensor2Input = sensorInput.get(3);

        if(!isEntriesInOrder(frontAxleSensor1Input, frontAxleSensor2Input, backAxleSensor1Input, backAxleSensor2Input)){
            throw new VehicleEntryException("Invalid data entries");
        }

        int firstAxleSensor1Time = TimeUtils.parseMillisFromInput(frontAxleSensor1Input);
        int firstAxleSensor2Time = TimeUtils.parseMillisFromInput(frontAxleSensor2Input);
        int backAxleSensor1Time = TimeUtils.parseMillisFromInput(backAxleSensor1Input);
        int backAxleSensor2Time = TimeUtils.parseMillisFromInput(backAxleSensor2Input);

        int frontAxleTime = (firstAxleSensor1Time + firstAxleSensor2Time) / 2;
        int backAxleTime = (backAxleSensor1Time + backAxleSensor2Time) / 2;

        VehicleEntry entry = new VehicleEntry(currentDay, frontAxleTime, backAxleTime, Direction.SOUTH);
        if(!entry.isValid()){
            throw new VehicleEntryException("Invalid data entries");
        }
        updateCurrentDay(entry);
        entries.add(entry);
        return sensorInput.subList(4, sensorInput.size());
    }

    private boolean isInSufficientEntries(List<String> entries, int minimumEntries) {

        return entries.size() < minimumEntries;
    }

    private Direction findDirection(String firstEntry, String secondEntry) {
        return firstEntry.startsWith(SENSOR1_PREFIX)
                && secondEntry.startsWith(SENSOR1_PREFIX) ? Direction.NORTH : Direction.SOUTH;
    }

    private boolean isEntriesInOrder(String frontAxleSensor1Entry, String frontAxleSensor2Entry, String rearAxleSensor1Entry, String rearAxleSensor2Entry) {
        return frontAxleSensor1Entry.startsWith(SENSOR1_PREFIX)
                && frontAxleSensor2Entry.startsWith(SENSOR2_PREFIX)
                && rearAxleSensor1Entry.startsWith(SENSOR1_PREFIX)
                && rearAxleSensor2Entry.startsWith(SENSOR2_PREFIX);
    }



    private void updateCurrentDay(VehicleEntry entry) {
        Date entryTime = entry.getEntryTime();
        if(entryTime.compareTo(lastEntryTime) < 0){
            currentDay++;
            entry.setDay(currentDay);
        }
        lastEntryTime = entryTime;
    }
}
