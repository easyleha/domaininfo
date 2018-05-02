package com.hillel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Main {

    static String url;
    static String input;
    static String output;

    public static void main(String[] args) {
        // parse "settings.txt"
        // clean "urls.txt" and "result.txt"
        downloadFileFromURL(url, input);
        // write "urls.txt"
        // create list only with domains
        // find the most frequent domains and write "result.txt"
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