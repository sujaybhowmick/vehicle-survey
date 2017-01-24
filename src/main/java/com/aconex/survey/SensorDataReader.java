package com.aconex.survey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by sbhowmick on 1/24/17.
 */
public class SensorDataReader {

    public static final String INPUT_VALIDATION_REGEX = "^[AaBb][0-9]+$";
    public static final Pattern INPUT_VALIDATION_REGEX_PATTERN = Pattern.compile(INPUT_VALIDATION_REGEX);

    private SensorDataReader(){}

    public static List<String> readLines(Reader reader){
        List<String> lines = new ArrayList<>();
        String line;
        try(BufferedReader bufferedReader = new BufferedReader(reader)){
            while((line = bufferedReader.readLine()) != null){
                if(isValid(line)){
                    lines.add(line);
                }else {
                    System.out.println("Invalid line input");
                    Collections.emptyList();
                }
            }

        }catch(IOException e){
            System.out.println("Error reading input");
            return Collections.emptyList();
        }
        return lines;
    }

    private static boolean isValid(String line) {
       return INPUT_VALIDATION_REGEX_PATTERN.matcher(line).matches();
    }
}
