package com.example.homework2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;

    public Movie(
            @JsonProperty("Title") String title,
            @JsonProperty("Year") String year,
            @JsonProperty("Rated") String rated,
            @JsonProperty("Released") String released,
            @JsonProperty("Runtime") String runtime,
            @JsonProperty("Genre") String genre,
            @JsonProperty("Director") String director,
            @JsonProperty("Writer") String writer,
            @JsonProperty("Actors") String actors,
            @JsonProperty("Plot") String plot,
            @JsonProperty("Language") String language,
            @JsonProperty("Country") String country
    ) {
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String showInfo() {
        return "<b>Title:</b> " + getTitle() + "<br>" +
                "<b>Year:</b> " + getYear() + "<br>" +
                "<b>Rated:</b> " + getRated() + "<br>" +
                "<b>Released:</b> " + getReleased() + "<br>" +
                "<b>Runtime:</b> " + getRuntime() + "<br>" +
                "<b>Genre:</b> " + getGenre() + "<br>" +
                "<b>Director:</b> " + getDirector() + "<br>" +
                "<b>Writer:</b> " + getWriter() + "<br>" +
                "<b>Actors:</b> " + getActors() + "<br>" +
                "<b>Plot:</b> " + getPlot()+ "<br>" +
                "<b>Country:</b> " + getCountry();
    }
}
