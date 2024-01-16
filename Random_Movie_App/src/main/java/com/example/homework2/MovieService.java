package com.example.homework2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    @Value("${omdb.apikey}")
    private String apiKey;

    private final String omdbApiUrl = "http://www.omdbapi.com/?t={title}&apikey={apiKey}";

    private final RestTemplate restTemplate;

    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Movie getMovieDetails(String title) {
        String url = omdbApiUrl.replace("{apiKey}", apiKey).replace("{title}", title);
        return restTemplate.getForObject(url, Movie.class);
    }
}


