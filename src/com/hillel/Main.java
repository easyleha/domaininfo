package com.hillel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {

    static String url;
    static String input;
    static String output;


    public static void main(String[] args) throws IOException {
        readFromFile();
        cleanResultsOldLaunches();
        downloadFileFromURL(url, input);
        readFileIntoList();
        listWithOnlyDomains();
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

    private static void cleanResultsOldLaunches() {
        File file = new File("settings.txt");
        if (file.exists()) {
            try {
                file.delete();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }

    public static void downloadFileFromURL(String path, String file) {
        URL url = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ReadableByteChannel inputStream = Channels.newChannel(url.openStream());) {
            fileOutputStream.getChannel().transferFrom(inputStream, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readFileIntoList() {
        String file = "settings.txt";
        List<String> list = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file))) {
            list = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private  static void listWithOnlyDomains(){
        ArrayList<String> list = new ArrayList<String>();

        for ( int i = 0; i < list.size(); i++) {

            String[] strToArray = list.get(i).split("/");

            if (strToArray[0].startsWith("www.")) {
                strToArray[0].replaceAll("www.", "");
            }
            ArrayList<String> newList = new ArrayList<String>();
            newList.add(strToArray[0]);

        }
    }
}
