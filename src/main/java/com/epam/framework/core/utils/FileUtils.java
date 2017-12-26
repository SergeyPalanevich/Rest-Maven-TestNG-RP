package com.epam.framework.core.utils;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class FileUtils {

    static public String getResourcePath(String fileName) {
        ClassLoader classLoader = (new FileUtils()).getClass().getClassLoader();
        URL url = classLoader.getResource(fileName);
        return url.getPath();
    }

    static public InputStream getResourcePathAsImputStream(String fileName) {
        ClassLoader classLoader = (new FileUtils()).getClass().getClassLoader();
        InputStream stream = classLoader.getResourceAsStream(fileName);
        return stream;
    }
    static public String getGsonString(String filePath) {
        JSONParser parser = new JSONParser();
        String gson = null;
        try {
            gson = parser.parse(new FileReader(filePath)).toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return gson;
    }
}
