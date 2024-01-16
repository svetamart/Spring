package com.example.homework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieFileReader movieFileReader;

    @Autowired
    public MovieController(MovieService movieService, MovieFileReader movieFileReader) {
        this.movieService = movieService;
        this.movieFileReader = movieFileReader;
    }

    @GetMapping("/random")
    public String getRandomMovie() {
        int maxAttempts = 5;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            String randomMovieTitle = movieFileReader.getRandomMovieTitle();
            if (randomMovieTitle != null) {
                Movie movie = movieService.getMovieDetails(randomMovieTitle);
                if (movie != null) {
                    return "<h2>Your Random Movie for Today</h2>" + "<br>" + movie.showInfo();
                }
            }
        }
        return "Unable to fetch a random movie. Sorry :(";
    }
}