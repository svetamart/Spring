package com.example.homework2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

@Component
public class MovieFileReader {
    private List<String> movieTitles;

    public MovieFileReader() {
        try {
            this.movieTitles = Files.readAllLines(Paths.get("src/main/resources/data/movies.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRandomMovieTitle() {
        if (movieTitles != null && !movieTitles.isEmpty()) {
            Random random = new Random();
            return movieTitles.get(random.nextInt(movieTitles.size()));
        }
        return null;
    }
}
