package com.example.eyee3.yee_assignment5;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class database {
    @NonNull
    @PrimaryKey
    private String movieId;
    private String movieName;
    private String movieYear;
    private String movieFile;

    public database() {
    }

    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) { this.movieId = movieId; }
    public String getMovieName() { return movieName; }
    public void setMovieName (String movieName) { this.movieName = movieName; }
    public String getMovieYear() { return movieYear; }
    public  void setMovieYear(String movieYear) { this.movieYear = movieYear; }
    public String getMovieFile() { return movieFile; }
    public void setMovieFile(String movieFile) { this.movieFile = movieFile; }
}
