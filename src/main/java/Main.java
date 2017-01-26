import com.aconex.survey.SensorDataParser;
import com.aconex.survey.SensorDataReader;
import com.aconex.survey.VehicleEntry;
import com.aconex.survey.reports.ReportWriter;

import java.io.*;
import java.util.List;

/**
 * Created by sbhowmick on 1/17/17.
 */
public class Main {

    private static final String INPUT_FILE_PATH = "data.txt";

    public static void main(String[] args) throws Exception {

        Reader reader;
        if (args.length == 0) {
            reader = new InputStreamReader(Main.class.getClassLoader().getResourceAsStream(INPUT_FILE_PATH));
        }else {
            reader = getReaderFromFile(args[0]);
        }

        if (reader == null){
            throw new Exception("Error reding input file...");
        }

        int[] intervals = {720, 60, 30, 20, 15};

        FileWriter writer = new FileWriter("report.out");
        List<String> data = SensorDataReader.readLines(reader);
        List<VehicleEntry> entries = new SensorDataParser().parse(data);
        try {
            ReportWriter reportWriter = new ReportWriter(writer, entries, intervals, 5);
            reportWriter.write();

        }catch(IOException e){
            System.out.println(e);
        }finally {
            writer.close();
        }
    }

    public static Reader getReaderFromFile(String path){
        try {
            return new FileReader(path);
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file : " + path);
        }
        return null;
    }
}
