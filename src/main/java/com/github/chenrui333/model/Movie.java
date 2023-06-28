package com.github.chenrui333.model;

import java.util.Objects;

public class Movie {
  private String title;
  private Integer releaseYear;

  public Movie() {
  }

  public Movie(String title, Integer releaseYear) {
    this.title = title;
    this.releaseYear = releaseYear;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getReleaseYear() {
    return releaseYear;
  }

  public void setReleaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Movie movie = (Movie) o;
    return Objects.equals(title, movie.title) && Objects.equals(releaseYear, movie.releaseYear);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, releaseYear);
  }
}
