package unit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Stream {
    BufferedReader reader;

    public Stream(String fileNameData) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(fileNameData));
    }
    public void readData()  {
        try {
            String line = reader.readLine();
            while (line != null) {
                System.out.println(parseLine(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected double parseLine(String line) {
        String[] split = line.split(";");
        return Double.parseDouble(split[0]) / Double.parseDouble(split[1]);
    }
    public static void main(String[] args) throws FileNotFoundException {
        Stream s = new Stream(args[0]);
        s.readData();
    }
}
