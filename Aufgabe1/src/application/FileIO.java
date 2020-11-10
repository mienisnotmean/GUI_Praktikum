package application;

import java.io.*;

public class FileIO {

    public static void write(String filename, String content) throws IOException {
        FileWriter writer = new FileWriter(filename);

        writer.write(content);

        writer.close();

    }

    public static String read(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;

        BufferedReader br = new BufferedReader(new FileReader(fileName));

        while ((line = br.readLine()) != null) {
            content.append(line).append('\n');
        }
        br.close();

        return content.toString();
    }
}
