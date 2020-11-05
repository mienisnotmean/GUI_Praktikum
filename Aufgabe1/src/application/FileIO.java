package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
    public static void write(String filename, String content){

        try {
            FileWriter writer = new FileWriter(filename);

            writer.write(content);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(String fileName){
        StringBuilder content = new StringBuilder();
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            while ((line = br.readLine()) != null) {
                content.append(line).append('\n');
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return content.toString();
    }
}
