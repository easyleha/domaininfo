package com.hillel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    static String url;
    static String input;
    static String output;


    public static void main(String[] args) throws IOException {

        readFromFile();
        downloadFileFromURL(url, input);
        // write "urls.txt"
        // create list only with domains
        // find the most frequent domains and write "result.txt"
    }

    public static void readFromFile() throws IOException {
        File file = new File("settings.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] arrays = line.split("=");
            System.out.println(Arrays.toString(arrays));

            if (arrays[0].equals("input")) {
                input = arrays[1];
            }
            if (arrays[0].equals("output")) {
                output = arrays[1];
            }
            if (arrays[0].equals("url")) {
                url = arrays[1];
            }
        }
        scanner.close();
    }


    public static void downloadFileFromURL(String path, String file) {
        try {
            URL url = new URL(path);
            ReadableByteChannel inputStream = Channels.newChannel(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.getChannel().transferFrom(inputStream, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
