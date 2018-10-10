package com.example.eyee3.yee_assignment5;

import java.io.Serializable;

public class movie implements Serializable {
    private String name;
    private String year;
    private String filename;

    movie(String n, String y, String f) {
        name = n;
        year = y;
        filename = f;
    }

    String getName() {
        return name;
    }

    String getYear() { return year; }

    String getFilename() { return filename; }
}
